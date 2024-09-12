package io.github.thecguygithub.cloudnet_dcbot_module

import eu.cloudnetservice.driver.document.Document
import eu.cloudnetservice.driver.document.DocumentFactory
import eu.cloudnetservice.driver.event.EventManager
import eu.cloudnetservice.driver.module.ModuleLifeCycle
import eu.cloudnetservice.driver.module.ModuleTask
import eu.cloudnetservice.driver.module.driver.DriverModule
import eu.cloudnetservice.node.cluster.sync.DataSyncHandler
import eu.cloudnetservice.node.cluster.sync.DataSyncRegistry
import io.github.thecguygithub.cloudnet_dcbot_module.commands.cloudnet
import io.github.thecguygithub.cloudnet_dcbot_module.config.CloudNetDCBotModuleConfig
import jakarta.inject.Singleton
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder
import net.dv8tion.jda.api.sharding.ShardManager


@Singleton
class CloudNetDCBotModule : DriverModule() {
    private var bot: ShardManager? = null

    @Volatile
    private lateinit var config: CloudNetDCBotModuleConfig

    @ModuleTask(lifecycle = ModuleLifeCycle.LOADED)
    fun handleInit(dataSyncRegistry: DataSyncRegistry) {
        this.config = this.readConfig(
            CloudNetDCBotModuleConfig::class.java,
            {
                CloudNetDCBotModuleConfig(
                    "token"
                )
            },
            DocumentFactory.json()
        )
        // register the cluster sync handler
        dataSyncRegistry.registerHandler(
            DataSyncHandler.builder<CloudNetDCBotModuleConfig>()
                .key("dcbot-module-config")
                .nameExtractor { "CloudNet Discord Bot Module Config" }
                .convertObject(CloudNetDCBotModuleConfig::class.java)
                .writer(this::writeConfig)
                .singletonCollector { this.config }
                .currentGetter { this.config }
                .build()
        )
    }

    @ModuleTask(lifecycle = ModuleLifeCycle.STARTED)
    fun started() {

        val builder =
            DefaultShardManagerBuilder.createDefault(config.token)
        builder.setStatus(OnlineStatus.ONLINE)
        builder.setShardsTotal(1)
        builder.setShards(0, 0)
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
        builder.addEventListeners(Commands(), cloudnet)

        bot = builder.build()
    }

    @ModuleTask(order = 125, lifecycle = ModuleLifeCycle.STARTED)
    fun finishStartup(eventManager: EventManager) {
        // eventManager.registerListener(CloudflareServiceStateListener::class.java)
    }

    fun writeConfig(config: CloudNetDCBotModuleConfig) {
        this.config = config
        this.writeConfig(Document.newJsonDocument().appendTree(config))
    }

    fun config(): CloudNetDCBotModuleConfig {
        return this.config
    }

}
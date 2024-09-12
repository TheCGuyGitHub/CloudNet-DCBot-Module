package io.github.thecguygithub.cloudnet_dcbot_module

import eu.cloudnetservice.driver.document.Document
import eu.cloudnetservice.driver.document.DocumentFactory
import eu.cloudnetservice.driver.event.EventListener
import eu.cloudnetservice.driver.event.events.service.CloudServiceLifecycleChangeEvent
import eu.cloudnetservice.driver.module.ModuleLifeCycle
import eu.cloudnetservice.driver.module.ModuleTask
import eu.cloudnetservice.driver.module.driver.DriverModule
import eu.cloudnetservice.driver.service.ServiceLifeCycle
import eu.cloudnetservice.node.event.service.CloudServicePostLifecycleEvent
import io.github.thecguygithub.cloudnet_dcbot_module.commands.cloudnet
import io.github.thecguygithub.cloudnet_dcbot_module.config.Configuration
import jakarta.inject.Singleton
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder
import net.dv8tion.jda.api.sharding.ShardManager
import java.awt.Color


@Singleton
class CloudNet_DCBot_Module : DriverModule() {
    private var bot: ShardManager? = null

    @Volatile
    private var configuration: Configuration? = null

    @ModuleTask(order = 127, lifecycle = ModuleLifeCycle.LOADED)
    fun convertConfig() {
        val config = this.readConfig(DocumentFactory.json())
        this.writeConfig(
             Document.newJsonDocument().appendTree(
                Configuration(
                    config.getString("token")
                )
            )
        )
    }

    @ModuleTask(order = 125, lifecycle = ModuleLifeCycle.LOADED)
    fun load() {
        configuration = this.readConfig(
            Configuration::class.java,
            {
                Configuration(
                    "dcbot-token"
                )
            },
            DocumentFactory.json()
        )
    }

    @ModuleTask(lifecycle = ModuleLifeCycle.STARTED)
    fun started() {

        val builder =
            DefaultShardManagerBuilder.createDefault(configuration?.token)
        builder.setStatus(OnlineStatus.ONLINE)
        builder.setShardsTotal(1)
        builder.setShards(0, 0)
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
        builder.addEventListeners(Commands(), cloudnet)

        bot = builder.build()


    }


    @EventListener
    fun cloudServiceLifecycleChangeEvent(event: CloudServiceLifecycleChangeEvent) {
        val channel: TextChannel? = bot!!.getTextChannelById(1195860170681622608)


        val id = event.serviceInfo().serviceId()
        val replacements = arrayOf(id.uniqueId(), id.taskName(), id.name(), id.nodeUniqueId())
        val embed = EmbedBuilder()
        embed.setTitle("CloudNet - LifeCycle Update!")
        embed.setAuthor("CloudNet - Logger")

        if (event.newLifeCycle() == ServiceLifeCycle.RUNNING) {
            embed.setColor(Color.GREEN)
            embed.addField("Service", id.name(), true)
            embed.addField("Node", id.nodeUniqueId(), true)
            embed.addField("LifeCycle", event.newLifeCycle().name, true)

            channel!!.sendMessageEmbeds(embed.build()).queue()

        } else if (event.newLifeCycle() == ServiceLifeCycle.STOPPED) {
            embed.setColor(Color.RED)
            embed.addField("Service", id.name(), true)
            embed.addField("Node", id.nodeUniqueId(), true)
            embed.addField("LifeCycle", event.newLifeCycle().name, true)

            channel!!.sendMessageEmbeds(embed.build()).queue()
        } else if (event.newLifeCycle() == ServiceLifeCycle.PREPARED) {
            embed.setColor(Color.YELLOW)
            embed.addField("Service", id.name(), true)
            embed.addField("Node", id.nodeUniqueId(), true)
            embed.addField("LifeCycle", event.newLifeCycle().name, true)

            channel!!.sendMessageEmbeds(embed.build()).queue()
        } else if (event.newLifeCycle() == ServiceLifeCycle.DELETED) {
            embed.setColor(Color.RED)
            embed.addField("Service", id.name(), true)
            embed.addField("Node", id.nodeUniqueId(), true)
            embed.addField("LifeCycle", event.newLifeCycle().name, true)

            channel!!.sendMessageEmbeds(embed.build()).queue()
        }
    }

    @EventListener
    fun cloudServicePostLifeCycleEvent(event: CloudServicePostLifecycleEvent) {
        val channel: TextChannel? = bot!!.getTextChannelById(1195860170681622608)


        val id = event.serviceInfo().serviceId()
        val replacements = arrayOf(id.uniqueId(), id.taskName(), id.name(), id.nodeUniqueId())
        val embed = EmbedBuilder()
        embed.setTitle("CloudNet - LifeCycle Update!")
        embed.setAuthor("CloudNet - Logger")

        if (event.newLifeCycle() == ServiceLifeCycle.RUNNING) {
            embed.setColor(Color.GREEN)
            embed.addField("Service", id.name(), true)
            embed.addField("Node", id.nodeUniqueId(), true)
            embed.addField("LifeCycle", event.newLifeCycle().name, true)

            channel!!.sendMessageEmbeds(embed.build()).queue()

        } else if (event.newLifeCycle() == ServiceLifeCycle.STOPPED) {
            embed.setColor(Color.RED)
            embed.addField("Service", id.name(), true)
            embed.addField("Node", id.nodeUniqueId(), true)
            embed.addField("LifeCycle", event.newLifeCycle().name, true)

            channel!!.sendMessageEmbeds(embed.build()).queue()
        } else if (event.newLifeCycle() == ServiceLifeCycle.PREPARED) {
            embed.setColor(Color.YELLOW)
            embed.addField("Service", id.name(), true)
            embed.addField("Node", id.nodeUniqueId(), true)
            embed.addField("LifeCycle", event.newLifeCycle().name, true)

            channel!!.sendMessageEmbeds(embed.build()).queue()
        } else if (event.newLifeCycle() == ServiceLifeCycle.DELETED) {
            embed.setColor(Color.RED)
            embed.addField("Service", id.name(), true)
            embed.addField("Node", id.nodeUniqueId(), true)
            embed.addField("LifeCycle", event.newLifeCycle().name, true)

            channel!!.sendMessageEmbeds(embed.build()).queue()
        }
    }


}
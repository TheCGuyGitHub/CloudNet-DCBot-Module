package io.github.thecguygithub.cloudnet_dcbot_module.commands

import eu.cloudnetservice.driver.inject.InjectionLayer
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import eu.cloudnetservice.driver.provider.CloudServiceProvider
import eu.cloudnetservice.driver.provider.ServiceTaskProvider
import eu.cloudnetservice.driver.service.ServiceConfiguration

object cloudnet: ListenerAdapter() {

    private val cloudServiceProvider: CloudServiceProvider = InjectionLayer.ext().instance(CloudServiceProvider::class.java)
    private val serviceTaskProvider: ServiceTaskProvider = InjectionLayer.ext().instance(ServiceTaskProvider::class.java)

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        when (event.subcommandName) {
            "service" -> {
                when (event.getOption("action")!!.asString) {
                    "stop" -> {
                        cloudServiceProvider.serviceProviderByName(event.getOption("service")!!.asString).stop()
                        event.reply("Stopping ${event.getOption("service")!!.asString}!")
                    }
                    "start" -> {
                        cloudServiceProvider.serviceProviderByName(event.getOption("service")!!.asString).start()
                        event.reply("Starting ${event.getOption("service")!!.asString}!")
                    }
                    "restart" -> {
                        cloudServiceProvider.serviceProviderByName(event.getOption("service")!!.asString).restart()
                        event.reply("Restarting ${event.getOption("service")!!.asString}!")
                    }
                }
            }
            "create" -> {
                event.reply("nope")
            }
        }
    }
}
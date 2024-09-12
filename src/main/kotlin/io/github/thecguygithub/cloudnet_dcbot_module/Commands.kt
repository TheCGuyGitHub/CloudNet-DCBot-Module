package io.github.thecguygithub.cloudnet_dcbot_module

import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData

class Commands: ListenerAdapter() {

    override fun onGuildReady(event: GuildReadyEvent) {
        val guild = event.guild
        if (guild.idLong != 1163458597905252422) return

        guild.updateCommands().addCommands(
            Commands.slash("cloudnet", "Manage the cloudnet services.")
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                .addSubcommands(
                    SubcommandData("service", "Manage cloudnet Services.")
                        .addOption(OptionType.STRING, "service", "The Name of the service", true)
                        .addOptions(
                            OptionData(OptionType.STRING, "action", "The Action that will be executed", true)
                                .addChoice("Stop", "stop")
                                .addChoice("Start", "start")
                                .addChoice("Restart", "restart")
                        )
                )
                .addSubcommands(
                    SubcommandData("create", "Manage cloudnet Services.")
                        .addOption(OptionType.STRING, "task", "The name of Task, that the service will be created on.", true)
                        .addOption(OptionType.NUMBER, "count", "How many services to be created", true)
                        .addOption(OptionType.STRING, "node", "The Node that the services will be created on.")
                        .addOption(OptionType.NUMBER, "id", "The ID that the Service will have.")
                        .addOption(OptionType.BOOLEAN, "started", "If the services should be started automaticly")
                )
        ).queue()
    }
}
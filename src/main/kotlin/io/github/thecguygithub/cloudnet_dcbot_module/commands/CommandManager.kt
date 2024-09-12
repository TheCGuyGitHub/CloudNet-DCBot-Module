package io.github.thecguygithub.cloudnet_dcbot_module.commands

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.channel.ChannelType
import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.DiscordLocale
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData

class CommandManager : ListenerAdapter() {

    override fun onGuildReady(event: GuildReadyEvent) {
        if (event.guild.idLong != 1242535083399708732) return
        event.guild.updateCommands().addCommands(
            Commands.slash("whitelist", "Whitelist commands")
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                .addSubcommands(
                    SubcommandData("sendembed", "Send the Embed that players can use to whitelist themselves")
                        .setDescriptionLocalizations(
                            mapOf(
                                DiscordLocale.ENGLISH_UK to "Send the Embed that players can use to whitelist themselves",
                                DiscordLocale.GERMAN to "Sende das Embed, das Spieler verwenden können, um sich selbst zu whitelisten"
                            )
                        )
                        .addOption(OptionType.CHANNEL, "channel", "The channel to send the embed to", true),
                    SubcommandData("list", "List all whitelisted players")
                        .setDescriptionLocalizations(
                            mapOf(
                                DiscordLocale.ENGLISH_UK to "List all whitelisted players",
                                DiscordLocale.GERMAN to "Liste alle whitelisted Spieler auf"
                            )
                        ),
                    SubcommandData("add", "Add a player to the whitelist")
                        .setDescriptionLocalizations(
                            mapOf(
                                DiscordLocale.ENGLISH_UK to "Add a player to the whitelist",
                                DiscordLocale.GERMAN to "Füge einen Spieler zur Whitelist hinzu"
                            )
                        )
                        .addOption(OptionType.USER, "user", "The user to add to the whitelist", true)
                        .addOption(OptionType.STRING, "ign", "The in-game name of the user", true),
                    SubcommandData("remove", "Remove a player from the whitelist")
                        .setDescriptionLocalizations(
                            mapOf(
                                DiscordLocale.ENGLISH_UK to "Remove a player from the whitelist",
                                DiscordLocale.GERMAN to "Entferne einen Spieler von der Whitelist"
                            )
                        )
                        .addOption(OptionType.USER, "user", "The user to remove from the whitelist", true),
                    SubcommandData("clear", "Clear the whitelist")
                        .setDescriptionLocalizations(
                            mapOf(
                                DiscordLocale.ENGLISH_UK to "Clear the whitelist",
                                DiscordLocale.GERMAN to "Leere die Whitelist"
                            )
                        ),
                    SubcommandData("sync", "Sync the whitelist")
                        .setDescriptionLocalizations(
                            mapOf(
                                DiscordLocale.ENGLISH_UK to "Sync the whitelist",
                                DiscordLocale.GERMAN to "Synchronisiere die Whitelist"
                            )
                        )
                ),
            Commands.slash("ip", "Get the IP of the server")
                .setDescriptionLocalizations(
                    mapOf(
                        DiscordLocale.ENGLISH_UK to "Get the IP of the server",
                        DiscordLocale.GERMAN to "Erhalte die IP des Servers"
                    )
                ),
            Commands.slash("embed", "Manage your embeds. Create new embeds or edit existing ones")
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(listOf(Permission.MESSAGE_MANAGE)))
                .setDescriptionLocalizations(
                    mapOf(
                        DiscordLocale.ENGLISH_UK to "Manage your embeds. Create new embeds or edit existing ones",
                        DiscordLocale.GERMAN to "Verwalte deine Embeds. Erstelle neue Embeds oder bearbeite vorhandene"
                    )
                ),
            Commands.slash("echo", "Make the bot repeat your input")
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                .setDescriptionLocalizations(
                    mapOf(
                        DiscordLocale.ENGLISH_UK to "Make the bot repeat your input",
                        DiscordLocale.GERMAN to "Lass den Bot deinen Input wiederholen"
                    )
                )
                .addOption(OptionType.STRING, "message", "The message to repeat", true)
                .addOption(OptionType.CHANNEL, "channel", "The channel to send the message to", false),
            Commands.slash("bot", "Bot Commands")
                .addSubcommands(
                    SubcommandData("info", "Get information about the bot")
                        .setDescriptionLocalizations(
                            mapOf(
                                DiscordLocale.ENGLISH_UK to "Get information about the bot",
                                DiscordLocale.GERMAN to "Erhalte Informationen über den Bot"
                            )
                        )
                ),
            Commands.slash("info", "Info Commands")
                .addSubcommands(
                    SubcommandData("guild", "Get information about the guild")
                        .setDescriptionLocalizations(
                            mapOf(
                                DiscordLocale.ENGLISH_UK to "Get information about the guild",
                                DiscordLocale.GERMAN to "Erhalte Informationen über den Server"
                            )
                        ),
                    SubcommandData("user", "Get information about a user")
                        .setDescriptionLocalizations(
                            mapOf(
                                DiscordLocale.ENGLISH_UK to "Get information about a user",
                                DiscordLocale.GERMAN to "Erhalte Informationen über einen Benutzer"
                            )
                        )
                        .addOption(OptionType.USER, "user", "The user to get information about", false)
                ),
            Commands.slash("moderation", "Moderation Commands")
                .addSubcommands(
                    SubcommandData("slowmode", "Set the slowmode of a channel")
                        .setDescriptionLocalizations(
                            mapOf(
                                DiscordLocale.ENGLISH_UK to "Set the slowmode of a channel",
                                DiscordLocale.GERMAN to "Setze den Slowmode eines Kanals"
                            )
                        )
                        .addOptions(
                            OptionData(OptionType.STRING, "time", "The time to set the slowmode to", true),
                            OptionData(OptionType.CHANNEL, "channel", "The channel to set the slowmode of", false).setChannelTypes(ChannelType.TEXT)
                        )
                ),
            Commands.slash("iq", "Get the IQ of a user")
                .setDescriptionLocalizations(
                    mapOf(
                        DiscordLocale.ENGLISH_UK to "Get the IQ of a user",
                        DiscordLocale.GERMAN to "Erhalte den IQ eines Benutzers"
                    )
                )
                .addOption(OptionType.USER, "user", "The user to get the IQ of", true)

        ).queue()
    }

}
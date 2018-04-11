package net.pyrix.mc.factions.storage;

import net.pyrix.mc.factions.Factions;
import net.pyrix.mc.factions.utils.MessageManager;
import net.pyrix.mc.factions.utils.MessageManager.Action;
import net.pyrix.mc.factions.utils.TextConvert;

public class ConfigurationStorage {

	private TextConvert[] CMD_HELP_MESSAGE = {
			new TextConvert("\n\n&7&m>=&8&m-------------------------------------------------&7&m=<"),
			new TextConvert("\n                           %title").set("%title", "&9&lPyrix &f&lFactions", new MessageManager(Action.SHOW_TEXT, "&9&oVersion: " + Factions
					.getInstance().getDescription().getVersion())),
			new TextConvert("                     &7&oAn Original Kind of Factions"),
			new TextConvert("\n                 &8&m-----=&7&l< &9Guidance Menu &7&l>&8&m=-----\n"),
			new TextConvert("          &7> %p").set("%p", "&9Story", new MessageManager(Action.SHOW_TEXT, "&8[Click] &7&oRead about what makes Pyrix factions unique..."),
					new MessageManager(Action.RUN_COMMAND, "/f h story")),
			new TextConvert("          &7> %p").set("%p", "&9General Information", new MessageManager(Action.SHOW_TEXT,
					"&8[Click] &7&oLearn about the rules, things you can do, etc..."), new MessageManager(Action.RUN_COMMAND, "/f h general")),
			new TextConvert("          &7> %p").set("%p", "&9Commands", new MessageManager(Action.SHOW_TEXT, "&8[Click] &7&oLearn about all the commands that you can use..."),
					new MessageManager(Action.RUN_COMMAND, "/f h commands")),
			new TextConvert("          &7> %p").set("%p", "&9Features", new MessageManager(Action.SHOW_TEXT, "&8[Click] &7&oLearn all that Pyrix factions has to offer..."),
					new MessageManager(Action.RUN_COMMAND, "/f h features")),
			new TextConvert("\n&7&m>=&8&m-------------------------------------------------&7&m=<\n\n") };

	private TextConvert[] CMD_HELP_MESSAGE_STORY = {
			new TextConvert("\n\n&7&m>=&8&m-------------------------------------------------&7&m=<"),
			new TextConvert("\n                 &8&m-----=&7&l< &9About/Story &7&l>&8&m=-----\n"),
			new TextConvert(
					" &7Several centuries ago, a single faction ruled this very island. They were well known as the Pyrilians, a powerful faction that no one dared to challenge. Story to be continued..."),
			new TextConvert("\n&7&m>=&8&m-------------------------------------------------&7&m=<\n\n") };

	private TextConvert[] CMD_HELP_MESSAGE_INFORMATION = {
			new TextConvert("\n&7&m>=&8&m-------------------------------------------------&7&m=<\n\n"),
			new TextConvert("\n              &8&m-----=&7&l< &9General Information &7&l>&8&m=-----\n"),
			new TextConvert(
					" &7Pyrix strives to offer an immersive experience of hardcore pvp gameplay on our Minecraft server. With the implementation of a conquest-themed system, " + "players do not need to focus on building, but focus on completeing tasks and quests around the specially designed map and get into conflict with the " + "opposing faction that amy be deemed as a risk to their conquest.\n"),
			new TextConvert(
					" &7Pyrix's specially designed gamemode consists of two factions, Red and Blue. These warring factions have been in constant conflict for decades and " + "continue their battles to take control of the island. Within a designated zone, there are many key locations in which both factions attempt to conquer and " + "claim as their own. These two factions will compete to claim the distinguished and notable territories located around the island, along with smaller villages " + "and mines that can provide resources to their advantage."),
			new TextConvert("\n&7&m>=&8&m-------------------------------------------------&7&m=<\n\n") };

	private TextConvert[] CMD_HELP_MESSAGE_COMMANDS = {
			new TextConvert("\n&7&m>=&8&m-------------------------------------------------&7&m=<\n\n"),
			new TextConvert("\n                  &8&m-----=&7&l< &9Commands &7&l>&8&m=-----\n"),
			new TextConvert("      %c &7[arguments] &8- &9Opens help menu").set("%c", "&7/factions help", new MessageManager(Action.SHOW_TEXT,
					"&7&oAliases: /[f, fac, factions] [ , ?, h, help])"), new MessageManager(Action.SUGGEST_COMMAND, "/factions help")),
			new TextConvert("\n&7&m>=&8&m-------------------------------------------------&7&m=<\n\n") };

	private TextConvert[] CMD_HELP_MESSAGE_FEATURES = {
			new TextConvert("\n&7&m>=&8&m-------------------------------------------------&7&m=<\n\n"),
			new TextConvert("\n                  &8&m-----=&7&l< &9Features &7&l>&8&m=-----\n"),
			new TextConvert(
					" &7This factions plugin will consist of the ability to creative, manage, and remove factions. You can join factions, or leave. You can also setup areas for factions to claim for resources. There will be a lot more features added soon!"),
			new TextConvert("\n&7&m>=&8&m-------------------------------------------------&7&m=<\n\n") };

	public void setCommandHelpMessage(String... messages) {
		TextConvert[] text = new TextConvert[messages.length];
		for (int e = 0; e < text.length; e++) {
			text[e] = new TextConvert(messages[e]);
		}
	}

	public void setCommandHelpMessage(TextConvert... text) {
		CMD_HELP_MESSAGE = text;
	}

	public TextConvert[] getCommandHelpFeaturesMessage() {
		return CMD_HELP_MESSAGE_FEATURES;
	}

	public TextConvert[] getCommandHelpInformationMessage() {
		return CMD_HELP_MESSAGE_INFORMATION;
	}

	public TextConvert[] getCommandHelpCommandsMessage() {
		return CMD_HELP_MESSAGE_COMMANDS;
	}

	public TextConvert[] getCommandHelpStoryMessage() {
		return CMD_HELP_MESSAGE_STORY;
	}

	public TextConvert[] getCommandHelpMessage() {
		return CMD_HELP_MESSAGE;
	}

}

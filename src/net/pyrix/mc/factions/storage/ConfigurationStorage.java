package net.pyrix.mc.factions.storage;

import net.pyrix.mc.factions.utils.TextConvert;

public class ConfigurationStorage {

	private TextConvert[] CMD_HELP_MESSAGE = { new TextConvert("&a&lPyrix &2&lFactions"), new TextConvert("&7&oAn Original Kind of Factions"), new TextConvert(
			"&aWhat do you need help with today?"), new TextConvert(" &7> &aStory", "&7&oRead about what makes Pyrix factions unique..."), new TextConvert(
					" &7> &aGeneral Information", "&7&oLearn about the rules, things you can do, etc..."), new TextConvert(" &7> &aCommands",
							"&7&oLearn about all the commands that you can use..."), new TextConvert(" &7> &aFeatures", "&7&oLearn all that Pyrix factions has to offer...") };

	public void setCommandHelpMessage(String... messages) {
		TextConvert[] text = new TextConvert[messages.length];
		for (int e = 0; e < text.length; e++) {
			text[e] = new TextConvert(messages[e]);
		}
	}

	public void setCommandHelpMessage(TextConvert... text) {
		CMD_HELP_MESSAGE = text;
	}

	public TextConvert[] getCommandHelpMessage() {
		return CMD_HELP_MESSAGE;
	}

}

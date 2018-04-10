package net.pyrix.mc.factions.storage;

import java.util.ArrayList;
import java.util.List;

import net.pyrix.mc.factions.utils.MessageManager;
import net.pyrix.mc.factions.utils.MessageManager.Action;
import net.pyrix.mc.factions.utils.TextConvert;

public class ConfigurationStorage {

	private TextConvert[] CMD_HELP_MESSAGE = {
			new TextConvert("\n\n&7&m>=&8&m-------------------------------------------&7&m=<"),
			new TextConvert("\n                  %title").set("%title", "&9&lPyrix &f&lFactions", new MessageManager(Action.SHOW_TEXT,
					"&9&oCustomly developed for your entertainment by the Pyrix development team.")),
			new TextConvert("           &7&oAn Original Kind of Factions"),
			new TextConvert("\n         &8&m-----=&7&l< &9Help Menu &7&l>&8&m=-----\n"),
			new TextConvert("     &7> %p").set("%p", "&9Story", new MessageManager(Action.SHOW_TEXT, "&8[Click] &7&oRead about what makes Pyrix factions unique..."),
					new MessageManager(Action.RUN_COMMAND, "/f h story")),
			new TextConvert("     &7> %p").set("%p", "&9General Information", new MessageManager(Action.SHOW_TEXT,
					"&8[Click] &7&oLearn about the rules, things you can do, etc..."), new MessageManager(Action.RUN_COMMAND, "/f h general")),
			new TextConvert("     &7> %p").set("%p", "&9Commands", new MessageManager(Action.SHOW_TEXT, "&8[Click] &7&oLearn about all the commands that you can use..."),
					new MessageManager(Action.RUN_COMMAND, "/f h commands")),
			new TextConvert("     &7> %p").set("%p", "&9Features", new MessageManager(Action.SHOW_TEXT, "&8[Click] &7&oLearn all that Pyrix factions has to offer..."),
					new MessageManager(Action.RUN_COMMAND, "/f h features")),
			new TextConvert("\n&7&m>=&8&m-------------------------------------------&7&m=<\n\n") };

	public void setCommandHelpMessage(String... messages) {
		TextConvert[] text = new TextConvert[messages.length];
		for (int e = 0; e < text.length; e++) {
			text[e] = new TextConvert(messages[e]);
		}
	}

	public void setCommandHelpMessage(TextConvert... text) {
		CMD_HELP_MESSAGE = text;
	}

	public String[] getCommandHelpMessageToString() {
		List<String> text = new ArrayList<String>();
		for (TextConvert t : CMD_HELP_MESSAGE) {
			text.add(t.toString());
		}
		return text.toArray(new String[text.size()]);
	}

	public TextConvert[] getCommandHelpMessage() {
		return CMD_HELP_MESSAGE;
	}

}

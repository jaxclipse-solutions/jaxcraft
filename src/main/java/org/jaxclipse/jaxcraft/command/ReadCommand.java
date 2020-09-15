package org.jaxclipse.jaxcraft.command;

import org.jaxclipse.jaxcraft.game.Game;

import java.util.Arrays;
import java.util.List;

/**
 * Command prints writing on object if any available, else prints
 * "Nothing written."
 * 
 * @author dbimko
 * 
 */
public final class ReadCommand extends JaxcraftCommand {

	public static final int ITEM_INDEX = 0;

	private static final List<String> commands = Arrays
			.asList(new String[] { "read" });

	@Override
	public List<String> getCommands() {
		return commands;
	}

	@Override
	public boolean execute(UserCommand command, Game game)
	{
		return game.readItem(command.getArg(ITEM_INDEX));
	}

}

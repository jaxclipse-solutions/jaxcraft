package org.jaxclipse.jaxcraft.command;

import org.jaxclipse.jaxcraft.game.Game;

import java.util.Arrays;
import java.util.List;

/**
 * Command prints contents of container in format
 * "(container) contains (item), (item), ..." and makes those items available to
 * pick up.
 * 
 * @author dbimko
 * 
 */
public final class OpenCommand extends JaxcraftCommand {

	public static final int ITEM_INDEX = 0;

	private static final List<String> commands = Arrays
			.asList(new String[] { "open" });

	@Override
	public List<String> getCommands() {
		return commands;
	}

	@Override
	public boolean execute(UserCommand command, Game game)
	{
		String arg = command.getArg(ITEM_INDEX);
		return game.openContainer(arg);
	}
}

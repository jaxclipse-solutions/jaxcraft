package org.jaxclipse.core.command;

import java.util.Arrays;
import java.util.List;

import org.jaxclipse.core.UserCommand;
import org.jaxclipse.jaxcraft.core.game.Game;

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

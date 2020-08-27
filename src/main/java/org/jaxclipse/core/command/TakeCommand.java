package org.jaxclipse.core.command;

import java.util.Arrays;
import java.util.List;

import org.jaxclipse.base.Game;
import org.jaxclipse.core.UserCommand;

/**
 * Command changes item ownership from room or container to inventory.
 * 
 * @author dbimko
 * 
 */
public final class TakeCommand extends AbstractCommand {

	public static final int ITEM_INDEX = 0;

	private static final List<String> commands = Arrays
			.asList(new String[] { "take" });

	@Override
	public List<String> getCommands() {
		return commands;
	}

	@Override
	public boolean execute(UserCommand command, Game game)
	{
		return game.addToInventory(command.getArg(ITEM_INDEX));
	}

}

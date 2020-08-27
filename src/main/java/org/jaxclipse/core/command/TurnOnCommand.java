package org.jaxclipse.core.command;

import java.util.Arrays;
import java.util.List;

import org.jaxclipse.base.Game;
import org.jaxclipse.core.UserCommand;

/**
 * Command activates item, if in inventory printing "You activate the (item)."
 * and executing commands in "turnon" element.
 * 
 * @author dbimko
 * 
 */
public final class TurnOnCommand extends AbstractCommand {

	public static final int ITEM_INDEX = 0;
	
	private static final List<String> commands = Arrays
			.asList(new String[] { "turnon" });

	@Override
	public List<String> getCommands() {
		return commands;
	}

	@Override
	public boolean execute(UserCommand command, Game game)
	{
		return game.turnOnItem(command.getArg(ITEM_INDEX));
	}
}

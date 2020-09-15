package org.jaxclipse.jaxcraft.command;

import org.jaxclipse.jaxcraft.game.Game;

import java.util.Arrays;
import java.util.List;

/**
 * Command changes item ownership from room or container to inventory.
 * 
 * @author dbimko
 * 
 */
public final class TakeCommand extends JaxcraftCommand {

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

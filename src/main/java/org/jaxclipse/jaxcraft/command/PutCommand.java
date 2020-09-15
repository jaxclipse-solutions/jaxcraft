package org.jaxclipse.jaxcraft.command;

import org.jaxclipse.jaxcraft.game.Game;

import java.util.Arrays;
import java.util.List;

/**
 * Command changes item ownership from inventory to declared container if
 * container is open and prints "Item (item) added to (container)."
 * 
 * @author dbimko
 * 
 */
public final class PutCommand extends JaxcraftCommand {

	private static final int CONTAINER_INDEX = 1;
	private static final int ITEM_INDEX = 0;

	private static final List<String> commands = Arrays
			.asList(new String[] { "put" });

	@Override
	public List<String> getCommands() {
		return commands;
	}

	@Override
	public boolean execute(UserCommand command, Game game)
	{
//		if (command.argCount() == 3) {
			String containerStr = command.getArg(CONTAINER_INDEX);
			String itemStr = command.getArg(ITEM_INDEX);
			return game.putItemToContainer(itemStr, containerStr);
//		}
		//return false;
	}

}

package org.jaxclipse.jaxcraft.command;

import org.jaxclipse.jaxcraft.game.Game;

import java.util.Arrays;
import java.util.List;

/**
 * Inventory command prints all items in the player's inventory
 * 
 * @author dbimko
 * 
 */
public final class InventoryCommand extends JaxcraftCommand {

	private static final List<String> commands = Arrays
			.asList(new String[] { "i" });

	@Override
	public List<String> getCommands() {
		return commands;
	}

	@Override
	public boolean execute(UserCommand command, Game game)
	{
		game.printInventory();
		return true;
	}

}

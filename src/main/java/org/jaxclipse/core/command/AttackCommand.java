package org.jaxclipse.core.command;

import java.util.Arrays;
import java.util.List;

import org.jaxclipse.core.UserCommand;
import org.jaxclipse.jaxcraft.core.game.Game;

/**
 * Command prints "You assault the (creature) with the (item)." and executes
 * "attack" elements if item matches creature's "vulnerability" and existing
 * conditions are met.
 * 
 * @author dbimko
 * 
 */
public class AttackCommand extends JaxcraftCommand {

	private static final int CREATURE_INDEX = 0;
	private static final int ITEM_INDEX = 2;
	private static final int ARG_COUNT = 3;

	private static final List<String> commands = Arrays
			.asList(new String[] { "attack" });

	@Override
	public List<String> getCommands() {
		return commands;
	}

	@Override
	public boolean execute(UserCommand command, Game game)
	{
		if (command.argCount() == ARG_COUNT) {
			String creatureName = command.getArg(CREATURE_INDEX);
			String itemName = command.getArg(ITEM_INDEX);
			return game.attackCreatureWithItem(creatureName, itemName);
		}
		return false;
	}

}

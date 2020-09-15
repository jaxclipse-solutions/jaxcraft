package org.jaxclipse.jaxcraft.command;

import java.util.Arrays;
import java.util.List;

import org.jaxclipse.jaxcraft.game.Game;
import org.jaxclipse.jaxcraft.model.enums.DirectionType;

/**
 * n, s, e, w - movement commands to put the player in a different room.
 * 
 * @author dbimko
 * 
 */
public final class NavigationCommand extends JaxcraftCommand {

	private static final List<String> commands = Arrays.asList(new String[] {
			"n", "s", "e", "w" });

	@Override
	public List<String> getCommands() {
		return commands;
	}

	@Override
	public boolean execute(UserCommand command, Game game)
	{
		DirectionType direction = DirectionType
				.getByShortName(command.getCommand());
		try {
			game.goInDirection(direction);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}

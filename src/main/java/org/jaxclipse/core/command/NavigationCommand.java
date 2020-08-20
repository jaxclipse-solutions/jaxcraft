package org.jaxclipse.core.command;

import java.util.Arrays;
import java.util.List;

import org.jaxclipse.core.UserCommand;
import org.jaxclipse.core.model.DirectionType;

/**
 * n, s, e, w - movement commands to put the player in a different room.
 * 
 * @author dbimko
 * 
 */
public final class NavigationCommand extends AbstractCommand {

	private static final List<String> commands = Arrays.asList(new String[] {
			"n", "s", "e", "w" });

	@Override
	public List<String> getCommands() {
		return commands;
	}

	@Override
	public boolean execute(UserCommand command) {
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

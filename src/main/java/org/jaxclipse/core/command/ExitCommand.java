package org.jaxclipse.core.command;

import java.util.Arrays;
import java.util.List;

import org.jaxclipse.core.UserCommand;
import org.jaxclipse.jaxcraft.core.game.Game;

public class ExitCommand extends JaxcraftCommand {

	private static final List<String> commands = Arrays.asList(new String[] {
			"quit", "exit" });

	@Override
	public List<String> getCommands() {
		return commands;
	}

	@Override
	public boolean execute(UserCommand command, Game game)
	{
		return game.exitGame();
	}

}

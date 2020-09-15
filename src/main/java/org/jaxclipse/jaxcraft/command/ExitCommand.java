package org.jaxclipse.jaxcraft.command;

import org.jaxclipse.jaxcraft.game.Game;

import java.util.Arrays;
import java.util.List;

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

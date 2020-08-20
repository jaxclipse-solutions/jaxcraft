package org.jaxclipse.core.command;

import java.util.Arrays;
import java.util.List;

import org.jaxclipse.core.UserCommand;

public class ExitCommand extends AbstractCommand {

	private static final List<String> commands = Arrays.asList(new String[] {
			"quit", "exit" });

	@Override
	public List<String> getCommands() {
		return commands;
	}

	@Override
	public boolean execute(UserCommand command) {
		return game.exitGame();
	}

}

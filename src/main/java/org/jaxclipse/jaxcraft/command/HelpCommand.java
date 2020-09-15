package org.jaxclipse.jaxcraft.command;

import org.jaxclipse.jaxcraft.game.Game;

import java.util.Arrays;
import java.util.List;

public class HelpCommand extends JaxcraftCommand
{

	private static final List<String> commands = Arrays.asList(new String[] { "h", "help" });

	@Override
	public List<String> getCommands()
	{
		return commands;
	}

	@Override
	public boolean execute(UserCommand command, Game game)
	{
		return game.helpMessage();
	}

}

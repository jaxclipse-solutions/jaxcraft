package org.jaxclipse;

import com.google.inject.Inject;

import org.beryx.textio.TerminalProperties;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.jaxclipse.base.Game;
import org.jaxclipse.core.UserCommand;
import org.jaxclipse.core.UserCommandParser;
import org.jaxclipse.core.command.AbstractCommand;

import java.util.Collection;

public class ConsoleWrapper extends Thread
{

	private final Game game;
	private final TextIO textIO;

	@Inject
	public ConsoleWrapper(Game game)
	{
		this.game = game;
		// This needs to be handled by CDI...
		// SystemTextTerminal sysTerminal = new SystemTextTerminal();

		TextTerminal textTerminal = TextIoFactory.getTextTerminal();
		this.textIO = new TextIO(textTerminal);
		
		
		//@formatter:off
		/*
		 * 
		 * 		
		 * SystemTextTerminal sysTerminal = new SystemTextTerminal();
		 * TextIO sysTextIO = new TextIO(sysTerminal);
		 * printBanner(sysTextIO);
		 * 
		 * 
		 * 
		 */
		//@formatter:on

	}

	@Override
	public void run()
	{
		String line = null;

		try
		{
			TextTerminal<?> terminal = textIO.getTextTerminal();
			TerminalProperties<?> props = terminal.getProperties();

			props.setPromptBold(true);
			// props.setPromptUnderline(true);
			props.setPromptColor("blue");
			while (true) // ummm ?
			{
				// TODO: argument tab/auto completion with textIO...?
				line = textIO.newStringInputReader().read("jaxcraft_> ");
				if (!handleInput(line))
				{
					consolePrint(Game.ERROR_MESSAGE);
				}

			}

		}
		catch (Exception ex)
		{
			consolePrint(Game.ERROR_MESSAGE + ex.toString());
		}
		finally
		{
			System.exit(1);
		}
	}

	protected boolean handleInput(String line)
	{
		boolean handled = false;
		UserCommand command = UserCommandParser.resolve(line);
		if (command != null)
		{
			for (AbstractCommand executor : game.getCommandExecutors())
			{
				if (executor.getCommands().contains(command.getCommand()))
				{
					consolePrint();
					handled = game.processCommand(command);
					break;
				}
			}
		}
		return handled;
	}

	public void consolePrint(Collection<String> messages)
	{
		consolePrint(messages.toArray(new String[] {}));
	}

	public void consolePrint(String... messages)
	{
		for (String s : messages)
		{
			textIO.getTextTerminal().println(s);
		}
	}
}
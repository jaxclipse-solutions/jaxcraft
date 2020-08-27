package org.jaxclipse;

import org.beryx.textio.TerminalProperties;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;
import org.beryx.textio.web.RunnerData;
import org.jaxclipse.base.Game;
import org.jaxclipse.core.CommandProvider;
import org.jaxclipse.core.GameFileParserImpl;
import org.jaxclipse.core.InventoryContainer;
import org.jaxclipse.core.InventoryContainerImpl;
import org.jaxclipse.core.UserCommand;
import org.jaxclipse.core.UserCommandParser;
import org.jaxclipse.core.command.AbstractCommand;

import java.util.Collection;
import java.util.function.BiConsumer;

public class TextIOApp implements BiConsumer<TextIO, RunnerData>
{
	private Game game;
	private boolean showPrompt = true;
	public TextIO textIO;

	@Override
	public void accept(TextIO textIO, RunnerData arg1)
	{
		this.textIO = textIO;
		// Injector injector = Guice.createInjector(new MainModule());
		//
		GameFileParserImpl gfpi = new GameFileParserImpl();
		InventoryContainer ic = new InventoryContainerImpl();
		CommandProvider cp = new CommandProvider();
		Game game = new GameImpl(cp, ic);

		GameExecutor gameExecutor = new GameExecutor(game, gfpi);

		gameExecutor.setUpREPL(this);
		gameExecutor.init();
		gameExecutor.play();

		String line = null;

		try
		{
			TextTerminal<?> terminal = textIO.getTextTerminal();
			TerminalProperties<?> props = terminal.getProperties();

			while (true) // ummm ?
			{
				if (showPrompt)
				{
					props.setPromptBold(true);
					// props.setPromptUnderline(true);
					props.setPromptColor("blue");

					line = textIO.newStringInputReader().read("jaxcraft_> ");
					props.setPromptBold(false);
					props.setPromptColor("grey");
					if (!handleInput(line))
					{
						consolePrint(Game.ERROR_MESSAGE);
					}
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

	public TextIO getTextIO()
	{
		return textIO;
	}

	public void setTextIO(TextIO textIO)
	{
		this.textIO = textIO;
	}

	public Game getGame()
	{
		return game;
	}

	public void setGame(Game game)
	{
		this.game = game;
	}

	public boolean isShowPrompt()
	{
		return showPrompt;
	}

	public void setShowPrompt(boolean showPrompt)
	{
		this.showPrompt = showPrompt;
	}
}

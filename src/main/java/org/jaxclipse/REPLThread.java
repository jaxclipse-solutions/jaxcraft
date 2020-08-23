package org.jaxclipse;

import com.google.inject.Inject;

import org.beryx.textio.TerminalProperties;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.beryx.textio.demo.WebTextIoExecutor;
import org.beryx.textio.web.TextIoApp;
import org.beryx.textio.web.WebTextTerminal;
import org.jaxclipse.base.Game;
import org.jaxclipse.core.UserCommand;
import org.jaxclipse.core.UserCommandParser;
import org.jaxclipse.core.command.AbstractCommand;

import java.util.Collection;

public class REPLThread extends Thread
{

	private final Game game;
	private final TextIO textIO;

	@Inject
	public REPLThread(Game game)
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
		WebTextTerminal webTextTerminal = new WebTextTerminal();
		webTextTerminal.init();
		TextIO webTextIO = new TextIO(webTextTerminal);

		WebTextTerminal webTextTerm = (WebTextTerminal) webTextIO.getTextTerminal();

		TextIoApp<?> textIoApp = createTextIoApp(webTextIO, app, webTextTerm);
		WebTextIoExecutor webTextIoExecutor = new WebTextIoExecutor();
		configurePort(textIO, webTextIoExecutor, 8080);
		webTextIoExecutor.execute(textIoApp);
	}

	private static void configurePort(TextIO textIO, WebTextIoExecutor webTextIoExecutor, int defaultPort)
	{
		int port = textIO.newIntInputReader().withDefaultValue(defaultPort).read("Server port number");
		webTextIoExecutor.withPort(port);
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

	// TODO - This violates single purpose for ConsoleWrapper
	// we don't need to keep reference to game just to
	// have handleInput here called from the game... sigh.
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
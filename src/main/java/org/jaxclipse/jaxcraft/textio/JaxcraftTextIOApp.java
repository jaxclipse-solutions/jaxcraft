package org.jaxclipse.jaxcraft.textio;

import org.beryx.textio.TerminalProperties;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;
import org.beryx.textio.web.RunnerData;
import org.jaxclipse.core.JaxcraftConsole;
import org.jaxclipse.jaxcraft.core.game.GameExecutor;

import java.util.Collection;
import java.util.function.BiConsumer;

/**
 * This class implements the interface needed to be consumable by the Ratpack web app server to use with textio.
 * 
 * 
 * @author jeffery.caldwell
 *
 */
public class JaxcraftTextIOApp implements BiConsumer<TextIO, RunnerData>, JaxcraftConsole
{
	// private Game game;
	private static final String DEFAULT_PROMPT = "jaxcraft>";
	private boolean showPrompt = true;
	public TextIO textIO;
	private GameExecutor gameExecutor;

	@Override
	public void accept(TextIO textIO, RunnerData arg1)
	{
		this.textIO = textIO;

		gameExecutor = new GameExecutor(this);
		gameExecutor.init();
		gameExecutor.play();
		System.exit(1);
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

	public void setShowPrompt(boolean showPrompt)
	{
		this.showPrompt = showPrompt;
	}

	@Override
	public String getLineInput()
	{
		return getLineInput(DEFAULT_PROMPT);
	}

	public String getLineInput(String promptTextOverride)
	{
		if (!showPrompt)
		{
			promptTextOverride = "";
		}

		TextTerminal<?> terminal = textIO.getTextTerminal();
		TerminalProperties<?> props = terminal.getProperties();
		props.setPromptBold(true);
		// props.setPromptUnderline(true);
		props.setPromptColor("blue");

		String line = textIO.newStringInputReader().read(promptTextOverride);
		props.setPromptBold(false);
		props.setPromptColor("grey");

		return line;
	}

}

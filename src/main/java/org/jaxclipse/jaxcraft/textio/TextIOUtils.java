package org.jaxclipse.jaxcraft.textio;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.beryx.textio.TextTerminalProvider;
import org.beryx.textio.console.ConsoleTextTerminalProvider;
import org.beryx.textio.jline.JLineTextTerminalProvider;
import org.beryx.textio.swing.SwingTextTerminalProvider;
import org.beryx.textio.system.SystemTextTerminal;
import org.beryx.textio.system.SystemTextTerminalProvider;
import org.beryx.textio.web.WebTextTerminal;

import java.util.function.Supplier;

public class TextIOUtils
{
	private static class NamedProvider implements TextTerminalProvider
	{
		final String name;
		final Supplier<TextTerminal<?>> supplier;

		NamedProvider(String name, Supplier<TextTerminal<?>> supplier)
		{
			this.name = name;
			this.supplier = supplier;
		}

		@Override
		public TextTerminal<?> getTextTerminal()
		{
			return supplier.get();
		}

		@Override
		public String toString()
		{
			return name;
		}
	}

	public static TextIO chooseTextIO()
	{
		SystemTextTerminal terminal = new SystemTextTerminal();
		TextIO textIO = new TextIO(terminal);
		while (true)
		{
			TextTerminalProvider terminalProvider = textIO.<TextTerminalProvider> newGenericInputReader(null)
					.withNumberedPossibleValues(new NamedProvider("Default terminal (provided by TextIoFactory)", TextIoFactory::getTextTerminal),
							new SystemTextTerminalProvider(), new ConsoleTextTerminalProvider(), new JLineTextTerminalProvider(),
							new SwingTextTerminalProvider(), new NamedProvider("Web terminal", WebTextTerminal::new))
					.read("\nChoose the terminal to be used for running jaxcraft.");

			TextTerminal<?> chosenTerminal = null;
			String errMsg = null;
			try
			{
				chosenTerminal = terminalProvider.getTextTerminal();
			}
			catch (Exception e)
			{
				errMsg = e.getMessage();
			}
			if (chosenTerminal == null)
			{
				terminal.printf("\nCannot create a %s%s\n\n", terminalProvider, ((errMsg != null) ? (": " + errMsg) : "."));
				continue;
			}
			chosenTerminal.init();
			return new TextIO(chosenTerminal);
		}
	}
}

package org.jaxclipse;

import org.beryx.textio.AbstractTextTerminal;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.beryx.textio.TextTerminalProvider;
import org.beryx.textio.console.ConsoleTextTerminalProvider;
import org.beryx.textio.jline.JLineTextTerminalProvider;
import org.beryx.textio.swing.SwingTextTerminalProvider;
import org.beryx.textio.system.SystemTextTerminal;
import org.beryx.textio.system.SystemTextTerminalProvider;
import org.beryx.textio.web.RatpackTextIoApp;
import org.beryx.textio.web.RunnerData;
import org.beryx.textio.web.SparkTextIoApp;
import org.beryx.textio.web.TextIoApp;
import org.beryx.textio.web.WebTextTerminal;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class Main
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

	public static void main(String[] args)
	{

		SystemTextTerminal sysTerminal = new SystemTextTerminal();
		TextIO sysTextIO = new TextIO(sysTerminal);

		BiConsumer<TextIO, RunnerData> app = chooseApp(sysTextIO);
		TextIO textIO = chooseTextIO();

		// Uncomment the line below to ignore user interrupts.
		// textIO.getTextTerminal().registerUserInterruptHandler(term -> System.out.println("\n\t### User interrupt ignored."), false);

		if (textIO.getTextTerminal() instanceof WebTextTerminal)
		{
			WebTextTerminal webTextTerm = (WebTextTerminal) textIO.getTextTerminal();
			TextIoApp<?> textIoApp = createTextIoApp(sysTextIO, app, webTextTerm);
			WebTextIoExecutor webTextIoExecutor = new WebTextIoExecutor();
			configurePort(sysTextIO, webTextIoExecutor, 8080);
			webTextIoExecutor.execute(textIoApp);
		}
		else
		{
			app.accept(textIO, null);
		}

	}

	private static TextIoApp<?> createTextIoApp(TextIO textIO, BiConsumer<TextIO, RunnerData> app, WebTextTerminal webTextTerm)
	{
		class Provider
		{
			private final String name;
			private final Supplier<TextIoApp<?>> supplier;

			private Provider(String name, Supplier<TextIoApp<?>> supplier)
			{
				this.name = name;
				this.supplier = supplier;
			}

			@Override
			public String toString()
			{
				return name;
			}
		}
		Provider textIoAppProvider = textIO.<Provider> newGenericInputReader(null)
				.withNumberedPossibleValues(new Provider("Ratpack", () -> new RatpackTextIoApp(app, webTextTerm)),
						new Provider("Spark", () -> new SparkTextIoApp(app, webTextTerm)))
				.read("\nChoose the web framework to be used");

		return textIoAppProvider.supplier.get();
	}

	private static void configurePort(TextIO textIO, WebTextIoExecutor webTextIoExecutor, int defaultPort)
	{
		int port = textIO.newIntInputReader().withDefaultValue(defaultPort).read("Server port number");
		webTextIoExecutor.withPort(port);
	}

	private static BiConsumer<TextIO, RunnerData> chooseApp(TextIO textIO)
	{
		List<BiConsumer<TextIO, RunnerData>> apps = Arrays.asList(new TextIOApp(), new DebugTextIOApp());
		BiConsumer<TextIO, RunnerData> app = textIO.<BiConsumer<TextIO, RunnerData>> newGenericInputReader(null).withNumberedPossibleValues(apps)
				.read("Choose the application to be run");
		String propsFileName = app.getClass().getSimpleName() + ".properties";
		System.setProperty(AbstractTextTerminal.SYSPROP_PROPERTIES_FILE_LOCATION, propsFileName);

		return app;
	}

	private static TextIO chooseTextIO()
	{
		SystemTextTerminal terminal = new SystemTextTerminal();
		TextIO textIO = new TextIO(terminal);
		while (true)
		{
			TextTerminalProvider terminalProvider = textIO.<TextTerminalProvider> newGenericInputReader(null)
					.withNumberedPossibleValues(new NamedProvider("Default terminal (provided by TextIoFactory)", TextIoFactory::getTextTerminal),
							new SystemTextTerminalProvider(), new ConsoleTextTerminalProvider(), new JLineTextTerminalProvider(),
							new SwingTextTerminalProvider(), new NamedProvider("Web terminal", WebTextTerminal::new))
					.read("\nChoose the terminal to be used for running the demo");

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

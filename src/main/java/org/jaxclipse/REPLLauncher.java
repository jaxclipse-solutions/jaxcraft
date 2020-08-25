package org.jaxclipse;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.beryx.textio.web.RatpackTextIoApp;
import org.beryx.textio.web.RunnerData;
import org.beryx.textio.web.SparkTextIoApp;
import org.beryx.textio.web.TextIoApp;
import org.beryx.textio.web.WebTextTerminal;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class REPLLauncher
{
	private TextIO textIO;
	private final REPLThread replThread;

	public REPLLauncher(REPLThread replThread)
	{
		this.replThread = replThread;

		TextTerminal textTerminal = TextIoFactory.getTextTerminal();
		textIO = new TextIO(textTerminal);
		replThread.setTextIO(textIO);

	}

	public void start()
	{

		WebTextTerminal webTextTerminal = new WebTextTerminal();
		webTextTerminal.init();
		TextIO webTextIO = new TextIO(webTextTerminal);

		WebTextTerminal webTextTerm = (WebTextTerminal) webTextIO.getTextTerminal();

		BiConsumer<TextIO, RunnerData> app = new BiConsumer<TextIO, RunnerData>()
		{

			@Override
			public void accept(TextIO arg0, RunnerData arg1)
			{
				replThread.run();
			}
		};
		TextIoApp<?> textIoApp = createTextIoApp(webTextIO, app, webTextTerm);

		WebTextIoExecutor webTextIoExecutor = new WebTextIoExecutor();
		configurePort(textIO, webTextIoExecutor, 8080);
		webTextIoExecutor.execute(textIoApp);
		replThread.setTextIO(webTextIO);

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

	public TextIO getTextIO()
	{
		return textIO;
	}

	public void setTextIO(TextIO textIO)
	{
		this.textIO = textIO;
	}

}

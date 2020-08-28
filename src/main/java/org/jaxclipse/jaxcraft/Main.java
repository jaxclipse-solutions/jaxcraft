package org.jaxclipse.jaxcraft;

import org.beryx.textio.TextIO;
import org.beryx.textio.web.RatpackTextIoApp;
import org.beryx.textio.web.RunnerData;
import org.beryx.textio.web.TextIoApp;
import org.beryx.textio.web.WebTextTerminal;
import org.jaxclipse.jaxcraft.textio.JaxcraftTextIOApp;
import org.jaxclipse.jaxcraft.textio.TextIOUtils;
import org.jaxclipse.jaxcraft.textio.WebTextIoExecutor;

import java.util.function.BiConsumer;

public class Main
{
	private static final String SYSPROP_PROPERTIES_FILE_LOCATION = "jaxcraft.properties.location";

	public static void main(String[] args) throws Exception
	{

		BiConsumer<TextIO, RunnerData> app = new JaxcraftTextIOApp();
		String propsFileName = app.getClass().getSimpleName() + ".properties";
		System.setProperty(Main.SYSPROP_PROPERTIES_FILE_LOCATION, propsFileName);

		TextIO textIO = TextIOUtils.chooseTextIO();

		// Uncomment the line below to ignore user interrupts.
		// textIO.getTextTerminal().registerUserInterruptHandler(term -> System.out.println("\n\t### User interrupt ignored."), false);

		if (textIO.getTextTerminal() instanceof WebTextTerminal)
		{
			WebTextTerminal webTextTerm = (WebTextTerminal) textIO.getTextTerminal();
			TextIoApp<?> textIoApp = new RatpackTextIoApp(app, webTextTerm);
			WebTextIoExecutor webTextIoExecutor = (new WebTextIoExecutor()).withPort(8080);
			webTextIoExecutor.execute(textIoApp);
		}
		else
		{
			app.accept(textIO, null);
		}
	}
}

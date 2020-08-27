package org.jaxclipse;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;
import org.beryx.textio.web.RunnerData;

import java.util.function.BiConsumer;

public class DebugTextIOApp implements BiConsumer<TextIO, RunnerData>
{

	@Override
	public void accept(TextIO textIO, RunnerData arg1)
	{
		TextTerminal<?> terminal = textIO.getTextTerminal();

		terminal.println("FOO and BAR inc.");
	}
}
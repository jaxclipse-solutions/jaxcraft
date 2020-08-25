package org.jaxclipse;

import org.jaxclipse.inject.MainModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main
{

	public static void main(String[] args)
	{
		try
		{
			Injector injector = Guice.createInjector(new MainModule());
			GameExecutor gameExecutor = injector.getInstance(GameExecutor.class);
			REPLThread replThread = new REPLThread();
			REPLLauncher launcher = new REPLLauncher(replThread);
			replThread.setTextIO(launcher.getTextIO());

			gameExecutor.setUpREPL(replThread);
			gameExecutor.init();

			replThread.start();
			gameExecutor.play();
		}
		catch (RuntimeException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
	}
}

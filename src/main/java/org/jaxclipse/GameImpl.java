package org.jaxclipse;

import org.jaxclipse.base.AbstractGame;
import org.jaxclipse.base.Game;
import org.jaxclipse.core.CommandProvider;
import org.jaxclipse.core.InventoryContainer;
import org.jaxclipse.core.model.GameInitModel;

public class GameImpl extends AbstractGame implements Game
{

	public GameImpl(CommandProvider commandProvider, InventoryContainer inventory)
	{
		super(commandProvider, inventory);
	}

	@Override
	public void init(GameInitModel gameInitModel)
	{
		if (gameInitModel == null)
		{
			throw new RuntimeException("gameInitModel");
		}
		// print("______ _____ _ \n"
		// + "| ___ \\ | ___| | | \n"
		// + "| |_/ /___ ___ _ __ ___ | |____ ___ __ | | ___ _ __ ___ _ __ \n"
		// + "| // _ \\ / _ \\| '_ ` _ \\ | __\\ \\/ / '_ \\| |/ _ \\| '__/ _ \\ '__|\n"
		// + "| |\\ \\ (_) | (_) | | | | | | | |___> <| |_) | | (_) | | | __/ | \n"
		// + "\\_| \\_\\___/ \\___/|_| |_| |_| \\____/_/\\_\\ .__/|_|\\___/|_| \\___|_| \n"
		// + " | | \n"
		// + " |_| \n" + "\n" + "\n" + "\n");
		print("*************************************");
		super.init(gameInitModel);
	}

	@Override
	public String getGameFile()
	{
		// return "sample.txt.xml";
		return "House.txt.xml";

	}

}

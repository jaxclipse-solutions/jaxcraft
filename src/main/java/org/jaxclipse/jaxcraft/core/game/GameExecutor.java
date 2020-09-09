package org.jaxclipse.jaxcraft.core.game;

import org.jaxclipse.core.CommandProvider;
import org.jaxclipse.core.GameFileParserImpl;
import org.jaxclipse.core.InventoryContainer;
import org.jaxclipse.core.InventoryContainerImpl;
import org.jaxclipse.core.JaxcraftConsole;
import org.jaxclipse.core.model.GameInitModel;
import org.jaxclipse.core.model.JaxcraftContext;

public class GameExecutor
{
	private Game game;
	private JaxcraftConsole jaxcraftConsole;

	public GameExecutor(JaxcraftConsole jaxcraftConsole)
	{
		this.jaxcraftConsole = jaxcraftConsole;
	}

	public void init()
	{
		try
		{
			GameFileParserImpl gameFileParserImpl = new GameFileParserImpl();
			InventoryContainer inventoryContainer = new InventoryContainerImpl();
			CommandProvider commandProvider = new CommandProvider();
			Game game = new GameImpl();
			game.setConsole(jaxcraftConsole);
			this.game = game;

			GameInitModel gameModel = gameFileParserImpl.parse(game.getGameFile());
			JaxcraftContext context = new JaxcraftContext(gameModel, commandProvider, inventoryContainer);

			game.init(context);
		}
		catch (Exception e)
		{
			System.err.println(e.toString());
			System.exit(1);
		}
	}

	public void play()
	{
		game.play();
	}

}

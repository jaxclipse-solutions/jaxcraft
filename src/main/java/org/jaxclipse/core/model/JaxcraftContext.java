package org.jaxclipse.core.model;

import org.jaxclipse.core.CommandProvider;
import org.jaxclipse.core.InventoryContainer;

public class JaxcraftContext
{
	GameInitModel gameModel;

	CommandProvider commandProvider;

	InventoryContainer inventoryContainer;

	RoomModel currentRoom;

	String prompt;

	String user;

	public JaxcraftContext(GameInitModel gameModel, CommandProvider commandProvider, InventoryContainer inventoryContainer)
	{
		super();
		this.gameModel = gameModel;
		this.commandProvider = commandProvider;
		this.inventoryContainer = inventoryContainer;
	}

	public GameInitModel getGameModel()
	{
		return gameModel;
	}

	public void setGameModel(GameInitModel gameModel)
	{
		this.gameModel = gameModel;
	}

	public CommandProvider getCommandProvider()
	{
		return commandProvider;
	}

	public void setCommandProvider(CommandProvider commandProvider)
	{
		this.commandProvider = commandProvider;
	}

	public InventoryContainer getInventoryContainer()
	{
		return inventoryContainer;
	}

	public void setInventoryContainer(InventoryContainer inventoryContainer)
	{
		this.inventoryContainer = inventoryContainer;
	}

	public String getPrompt()
	{
		return prompt;
	}

	public void setPrompt(String prompt)
	{
		this.prompt = prompt;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

}

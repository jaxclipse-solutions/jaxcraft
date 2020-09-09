package org.jaxclipse.jaxcraft.core.game;

import org.jaxclipse.core.model.JaxcraftContext;

public class GameImpl extends AbstractGame implements Game
{

	public GameImpl()
	{
		super();
	}

	@Override
	public void init(JaxcraftContext context)
	{
		if (context == null)
		{
			throw new RuntimeException("context");
		}

		print("*************************************");
		super.init(context);
	}

	@Override
	public String getGameFile()
	{
		return "House.txt.xml";

	}

}

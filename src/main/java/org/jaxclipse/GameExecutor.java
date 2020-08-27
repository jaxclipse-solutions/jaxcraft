package org.jaxclipse;

import org.jaxclipse.base.Game;
import org.jaxclipse.core.GameFileParserImpl;
import org.jaxclipse.core.exception.GameFileParseException;
import org.jaxclipse.core.model.GameInitModel;

public class GameExecutor
{

	private Game game;
	private final GameFileParserImpl gameFileParserImpl;

	public GameExecutor(Game game, GameFileParserImpl gameFileParserImpl)
	{
		this.gameFileParserImpl = gameFileParserImpl;
		this.game = game;
	}

	public void init()
	{
		try
		{
			GameInitModel gameModel = parseGameFile();
			game.init(gameModel);
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

	private GameInitModel parseGameFile() throws GameFileParseException
	{
		return gameFileParserImpl.parse(game.getGameFile());
	}

	public void setUpREPL(TextIOApp replThread)
	{
		// TODO: avoid code smells

		replThread.getTextIO().getTextTerminal().println("...");
		game.setREPLThread(replThread);
		replThread.setGame(game);

	}
}

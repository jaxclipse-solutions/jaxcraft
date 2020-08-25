package org.jaxclipse;


import org.jaxclipse.base.Game;
import org.jaxclipse.core.GameFileParserImpl;
import org.jaxclipse.core.exception.GameFileParseException;
import org.jaxclipse.core.model.GameInitModel;

import com.google.inject.Inject;

public class GameExecutor {

	private Game game;
	private final GameFileParserImpl gameFileParserImpl;

	@Inject
	public GameExecutor(Game game, GameFileParserImpl gameFileParserImpl) {
		this.gameFileParserImpl = gameFileParserImpl;
		this.game = game;
	}

	public void init() {
		try {
			GameInitModel gameModel = parseGameFile();
			game.init(gameModel);
		} catch (Exception e) {
			System.err.println(e.toString());
			System.exit(1);
		}
	}

	public void play() {
		game.play();
	}

	private GameInitModel parseGameFile() throws GameFileParseException {
		return gameFileParserImpl.parse(game.getGameFile());
	}

	public void setUpREPL(REPLThread replThread)
	{
		// TODO: avoid code smells
		System.out.println("TEST setUpREPL...");
		replThread.getTextIO().getTextTerminal().println("GameExecutor::: setUpRepl...");
		game.setREPLThread(replThread);
		replThread.setGame(game);

	}
}

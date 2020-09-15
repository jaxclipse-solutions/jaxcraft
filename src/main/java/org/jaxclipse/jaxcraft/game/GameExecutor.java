package org.jaxclipse.jaxcraft.game;

import org.jaxclipse.jaxcraft.command.CommandProvider;
import org.jaxclipse.jaxcraft.model.InventoryContainer;
import org.jaxclipse.jaxcraft.model.InventoryContainerImpl;
import org.jaxclipse.jaxcraft.model.JaxcraftContext;
import org.jaxclipse.jaxcraft.model.JaxcraftWorldMap;

public class GameExecutor {
    private Game game;
    private JaxcraftConsole jaxcraftConsole;

    public GameExecutor(JaxcraftConsole jaxcraftConsole) {
        this.jaxcraftConsole = jaxcraftConsole;
    }

    public void init() {
        try {
            GameFileParserImpl gameFileParserImpl = new GameFileParserImpl();

            Game game = new GameImpl();
            game.setConsole(jaxcraftConsole);
            this.game = game;

            JaxcraftWorldMap gameModel = gameFileParserImpl.parse(game.getGameFile());
            JaxcraftContext context = new JaxcraftContext(gameModel);

            game.init(context);
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(1);
        }
    }

    public void play() {
        game.play();
    }

}

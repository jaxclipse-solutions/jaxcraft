package org.jaxclipse.jaxcraft.game;

import org.jaxclipse.jaxcraft.model.JaxcraftContext;

public class GameImpl extends AbstractGame implements Game {

    public GameImpl() {
        super();
    }

    @Override
    public void init(JaxcraftContext context) {
        if (context == null) {
            throw new RuntimeException("context");
        }

        print("*************************************");
        super.init(context);
    }

    @Override
    public String getGameFile() {
        return "world.json";

    }

    @Override
    public boolean helpMessage() {
        print("Command                  - Description.");
        print(" help                    - show this list.");
        print(" n, s, e, w              - move in that direction.");
        print(" i                       - list inventory.");
        print(" take <item>             - take an object.");
        print(" read <item>             - inspect details of the item.");
        print(" put <item> <container>  - puts the item in the container.");
        print(" open <container>        - opens the container.");
        print(" turnon <item>           - activates the item.");
        print(" attack <creature> with <item> - attempt to attack.");
        print("---------------------------------------------------------");
        return true;
    }

}

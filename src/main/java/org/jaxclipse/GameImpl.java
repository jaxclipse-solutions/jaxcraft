package org.jaxclipse;


import org.apache.commons.lang.NullArgumentException;
import org.jaxclipse.base.AbstractGame;
import org.jaxclipse.base.Game;
import org.jaxclipse.core.InventoryContainer;
import org.jaxclipse.core.OutStream;
import org.jaxclipse.core.model.GameInitModel;
import org.jaxclipse.inject.CommandProvider;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class GameImpl extends AbstractGame implements Game {

  @Inject
  public GameImpl(InputHandler parser, CommandProvider commandProvider,
      InventoryContainer inventory, OutStream outStream) {
    super(parser, commandProvider, inventory, outStream);
  }

  @Override
  public void init(GameInitModel gameInitModel) {
    if (gameInitModel == null) {
      throw new NullArgumentException("gameInitModel");
    }
    print("______                        _____           _                     \n"
        + "| ___ \\                      |  ___|         | |                    \n"
        + "| |_/ /___   ___  _ __ ___   | |____  ___ __ | | ___  _ __ ___ _ __ \n"
        + "|    // _ \\ / _ \\| '_ ` _ \\  |  __\\ \\/ / '_ \\| |/ _ \\| '__/ _ \\ '__|\n"
        + "| |\\ \\ (_) | (_) | | | | | | | |___>  <| |_) | | (_) | | |  __/ |   \n"
        + "\\_| \\_\\___/ \\___/|_| |_| |_| \\____/_/\\_\\ .__/|_|\\___/|_|  \\___|_|   \n"
        + "                                       | |                          \n"
        + "                                       |_|                          \n" + "\n" + "\n"
        + "[display startup message]\n");
    super.init(gameInitModel);
  }

  @Override
  public String getGameFile() {
    // return "src/main/resources/sample.txt.xml";
    // return "sample.txt.xml";
    return "House.txt.xml";

  }

}

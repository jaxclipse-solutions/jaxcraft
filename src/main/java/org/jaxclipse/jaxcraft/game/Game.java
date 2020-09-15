package org.jaxclipse.jaxcraft.game;

import org.jaxclipse.jaxcraft.command.JaxcraftCommand;
import org.jaxclipse.jaxcraft.command.UserCommand;
import org.jaxclipse.jaxcraft.model.JaxcraftContext;
import org.jaxclipse.jaxcraft.model.enums.DirectionType;

import java.util.List;

public interface Game {

    public static final String ACTIVATE_FORMAT = "You activate the %s.";
    public static final String ATTACK_MESSAGE_FORMAT = "You assault the %s with the %s.";
    public static final String BYE_WORD = "Bye";
    public static final String CONTAINS_MESSAGE_FORMAT = "%s contains %s.";
    public static final int DEFAULT_CURRENT_ROOM_INDEX = 0;
    public static final String EMPTY_MESSAGE_FORMAT = "% is empty.";
    public static final int ERROR_EXIT_STATUS = 1;
    public static final String ERROR_MESSAGE = "Error";
    public static final String INVENTORY_ADD_ITEM_FORMAT = "The %s was added to your inventory";
    public static final String INVENTORY_CONTAINS_FORMAT = "Your inventory: %s";
    public static final String INVENTORY_EMPTY_MESSAGE = "Your inventory is empty";
    public static final String NEW_LINE = "\n";
    public static final String NO_DIRECTION_MESSAGE = "You can't go that way!";
    public static final String NOTHING_MESSAGE = "Nothing written.";
    public static final String NOTHING_TO_TAKE_MESSAGE = "There's nothing to take with that name!";
    public static final String PUT_MESSAGE_FORMAT = "Item %s added to %s.";
    public static final int SUCCESS_EXIT_STATUS = 0;

    boolean addToInventory(String string);

    boolean attackCreatureWithItem(String creatureName, String itemName);

    boolean exitGame();

    List<JaxcraftCommand> getCommandExecutors();

    String getGameFile();

    void goInDirection(DirectionType direction);

    void init(JaxcraftContext context);

    boolean openContainer(String containerName);

    void play();

    void printInventory();

    boolean processCommand(UserCommand userCommand, JaxcraftCommand jaxcraftCommand);

    boolean putItemToContainer(String itemName, String containerName);

    boolean readItem(String itemName);

    void setConsole(JaxcraftConsole jaxcraftConsole);

    boolean turnOnItem(String itemName);

    boolean helpMessage();

    boolean handleInput(String line);

}

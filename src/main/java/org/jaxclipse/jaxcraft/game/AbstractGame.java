package org.jaxclipse.jaxcraft.game;

import org.jaxclipse.jaxcraft.command.ActionParserHelper;
import org.jaxclipse.jaxcraft.command.CommandProvider;
import org.jaxclipse.jaxcraft.command.JaxcraftCommand;
import org.jaxclipse.jaxcraft.command.UserCommand;
import org.jaxclipse.jaxcraft.command.UserCommandParser;
import org.jaxclipse.jaxcraft.model.*;
import org.jaxclipse.jaxcraft.model.enums.ActionType;
import org.jaxclipse.jaxcraft.model.enums.DirectionType;
import org.jaxclipse.jaxcraft.model.enums.TriggerType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

// import jline.internal.Log;

public abstract class AbstractGame implements Game {

    private List<RoomModel> rooms;
    private List<ItemModel> items;
    private List<ContainerModel> containers;
    private List<CreatureModel> creatures;
    private final List<TriggerModel> triggers;
    private List<JaxcraftCommand> executors;
    private RoomModel currentRoom;
    private final Map<String, ItemContainer> itemContainers;

    private InventoryContainer inventory;

    private JaxcraftConsole jaxcraftConsole;
    private CommandProvider commandProvider;

    public AbstractGame() {
        rooms = new ArrayList<>();
        triggers = new ArrayList<>();
        itemContainers = new HashMap<>();
    }

    public void init(JaxcraftContext context) {
        this.commandProvider = context.getCommandProvider();
        this.inventory = context.getInventoryContainer();
        JaxcraftWorldMap gameInitModel = context.getGameModel();

        executors = commandProvider.getAllCommands();

        rooms = gameInitModel.getRooms();
        items = gameInitModel.getItems();
        containers = gameInitModel.getObjects();
        creatures = gameInitModel.getCreatures();

        currentRoom = rooms.get(DEFAULT_CURRENT_ROOM_INDEX);
        initTriggers(gameInitModel);

        itemContainers.put(inventory.getName(), inventory);
        itemContainers.putAll(gameInitModel.getItemContainers());
    }

    public void play() {
        printCurrentRoom();
        jaxcraftConsole.setShowPrompt(true);
        while (true) // ummm ?
        {
            String line = jaxcraftConsole.getLineInput();
            if (!handleInput(line)) {
                jaxcraftConsole.consolePrint(Game.ERROR_MESSAGE);
            }
        }
    }

    public boolean handleInput(String line) {
        boolean handled = false;
        UserCommand command = UserCommandParser.resolve(line);
        if (command != null) {
            Optional<JaxcraftCommand> executor = getCommandExecutors().stream().filter(e -> e.getCommands().contains(command.getCommand()))
                    .findFirst();
            if (executor.isPresent()) {
                JaxcraftCommand jaxcraftCommand = executor.get();
                jaxcraftConsole.consolePrint();
                handled = processCommand(command, jaxcraftCommand);
            }

        }
        return handled;
    }

    private void initTriggers(JaxcraftWorldMap model) {
        model.getCreatures().stream().forEach(m -> triggers.add(m.getTrigger()));

        model.getRooms().stream().flatMap(room -> room.getTriggers().stream()).forEach(tm -> {
            if (!triggers.contains(tm)) {
                triggers.add(tm);
            }
        });
    }

    public boolean processCommand(UserCommand userCommand, JaxcraftCommand jaxcraftCommand) {
        Optional<TriggerModel> trigger = currentRoom.getTriggers().stream().filter(e -> e.getCommand().equals(userCommand.getCommand())).findFirst();
        if (trigger.isPresent()) {
			if (processTrigger(trigger.get())) {
				return true;
			}
		}
//        } else {
//            // Log.debug("No triggers processed");
//            System.out.println("No triggers processed");
//        }

        return jaxcraftCommand.execute(userCommand, this);
    }

    protected boolean processTrigger(TriggerModel trigger) {
        ConditionModel condition = trigger.getCondition();
        String objectName = condition.getObject();
        String ownerName = condition.getOwner();
        if (ownerName != null) {
            ItemContainer owner = getItemContainerByName(ownerName);
            if (owner == null) {
                throw new RuntimeException("owner cannot be null (" + ownerName + ")");
            }
            boolean has = owner.contains(objectName);
            if (has == condition.isHas()) {
                executeActionIfExists(trigger.getAction());
                print(trigger.getMessages());
                return true;
            }
        } else {
            ItemContainer obj = getItemContainerByName(objectName);
            if (obj != null) {
                if (obj.getStatus().equals(condition.getStatus())) {
                    print(trigger.getMessages());
                    return true;
                }
            }
        }
        return false;
    }

    private void checkItemTriggers(ItemModel item) {
        for (int i = 0; i < triggers.size(); i++) {
            TriggerModel trigger = triggers.get(i);
            if (isConditionHappened(trigger.getCondition(), item)) {
                if (trigger.getType().equals(TriggerType.SINGLE)) {
                    triggers.remove(i);
                }
                print(trigger.getMessages());
            }
        }
    }

    private boolean isConditionHappened(ConditionModel condition, ItemModel item) {
        if (null == condition.getStatus()) {
            return false;
        }
        if (condition.getObject().equals(item.getName()) && condition.getStatus().equals(item.getStatus())) {
            return true;
        }
        return false;
    }

    private boolean executeActionIfExists(String actionString) {
        ActionModel actionModel = ActionParserHelper.parse(actionString);
        if (actionModel == null) {
            return false;
        }
        ActionType type = actionModel.getActionType();
        switch (type) {
            case ADD:
                ItemModel item = getItem(actionModel.getItem());
                if (item != null) {
                    String ownerName = actionModel.getOwner();
                    ItemContainer owner = getItemContainerByName(ownerName);
                    if (owner != null) {
                        owner.addItem(item.getName());
                    } else {
                        throw new RuntimeException("owner cannot be null (" + owner + ")");
                    }
                }
                break;
            case DELETE:
                String objName = actionModel.getItem();
                CreatureModel creature = getObjectByName(objName);
                if (creature != null) {
                    creature.setExists(false);
                } else {
                    throw new RuntimeException("object cannot be null (" + objName + ")");
                }
                break;
            case UPDATE:
                HasStatus owner = getOwnerByName(actionModel.getItem());
                if (owner != null) {
                    owner.setStatus(actionModel.getStatus());
                }
                break;
            default:
                return false;
        }
        return true;
    }

    private CreatureModel getObjectByName(String name) {
        return getCreatureByName(name);
    }

    private HasStatus getOwnerByName(String name) {
        for (ContainerModel obj : containers) {
            if (obj.getName().equals(name)) {
                return obj;
            }
        }
        return getInventoryItem(name);
    }

    private ItemContainer getItemContainerByName(String name) {
        return itemContainers.get(name);
    }

    public void printInventory() {
        if (inventory.hasItems()) {
            print(String.format(INVENTORY_CONTAINS_FORMAT, inventory.itemsToString()));
        } else {
            print(INVENTORY_EMPTY_MESSAGE);
        }
    }

    public void goInDirection(DirectionType direction) {
        String nextRoomName = currentRoom.nextRoom(direction);
        RoomModel nextRoom = getRoomByName(nextRoomName);
        if (nextRoom == null) {
            print(NO_DIRECTION_MESSAGE);
        } else {
            currentRoom = nextRoom;
            printCurrentRoom();
        }
    }

    private ItemModel getItem(String itemName) {
        for (ItemModel item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    private ItemModel getItemInCurrentRoom(String itemName) {
        if (isCurrentRoomContainsItem(itemName)) {
            return getItem(itemName);
        }
        return null;
    }

    private <T> T getPOJOByPredicateListByName(List<T> list, Predicate<? super T> predicate, String creatureName) {
        Optional<T> optPOJO = list.stream().filter(predicate).findFirst();

        if (optPOJO.isPresent()) {
            return optPOJO.get();
        } else {
            return null;
        }

    }

    private CreatureModel getCreatureByName(String name) {
        Predicate<CreatureModel> namePredicate = model -> model.getName().equals(name);
        Predicate<CreatureModel> existsPredicate = model -> model.isExists();
        return getPOJOByPredicateListByName(creatures, namePredicate.and(existsPredicate), name);
    }

    private ContainerModel getContainerByName(String name) {
        Predicate<ContainerModel> namePredicate = model -> model.getName().equals(name);
        return getPOJOByPredicateListByName(containers, namePredicate, name);
    }

    private RoomModel getRoomByName(String name) {
        Predicate<RoomModel> namePredicate = model -> model.getName().equals(name);
        return getPOJOByPredicateListByName(rooms, namePredicate, name);
    }

    public boolean exitGame() {
        try {
            print(BYE_WORD);
            System.exit(SUCCESS_EXIT_STATUS);
            // System.exit(ERROR_EXIT_STATUS);

        } catch (SecurityException e) {
            System.exit(ERROR_EXIT_STATUS);
        }
        return true;
    }

    public boolean addToInventory(String itemName) {
        ItemModel item = getItemInCurrentRoom(itemName);
        if (item != null) {

            if (!inventory.contains(item.getName())) {
                inventory.addItem(item.getName());
            }

            currentRoom.removeItem(item.getName());

            List<ContainerModel> containers = getContainersInCurrentRoom();
            for (ContainerModel container : containers) {
                if (container.contains(item.getName())) {
                    container.removeItem(item.getName());
                }
            }
            print(String.format(INVENTORY_ADD_ITEM_FORMAT, item.getName()));
            return true;
        }
        return false;
    }

    public boolean attackCreatureWithItem(String creatureName, String itemName) {
        CreatureModel creature = getCreatureByName(creatureName);
        ItemModel item = getItem(itemName);
        if (creature != null && item != null) {
            if (creature.getVulnerability().equals(item.getName())) {
                AttackModel attackModel = creature.getAttack();
                if (attackModel != null) {
                    if (isConditionHappened(attackModel.getCondition(), item)) {
                        List<String> actions = attackModel.getActions();
                        for (String action : actions) {
                            executeActionIfExists(action);
                        }

                        print(String.format(ATTACK_MESSAGE_FORMAT, creature.getName(), item.getName()));
                        print(attackModel.getMessage());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean turnOnItem(String itemName) {
        ItemModel item = getInventoryItem(itemName);
        if (item != null) {
            TurnOnModel turnOnModel = item.getTurnOnModel();
            if (turnOnModel != null) {
                print(String.format(ACTIVATE_FORMAT, item.getName()));
                print(turnOnModel.getMessage());

                if (executeActionIfExists(item.getTurnOnModel().getAction())) {
                    checkItemTriggers(item);
                } else {
                    print(ERROR_MESSAGE);
                }
                return true;
            }
        }
        return false;
    }

    public boolean putItemToContainer(String itemName, String containerName) {
        ContainerModel container = getContainerInCurrentRoomByName(containerName);
        ItemModel item = getInventoryItem(itemName);
        if (container != null && item != null) {
            if (container.getAccept() != null) {
                if (container.getAccept().equals(item.getName())) {
                    proccessPuttingItem(container, item);
                    return true;
                }
            } else {
                proccessPuttingItem(container, item);
                return true;
            }
        }
        return false;
    }

    private void proccessPuttingItem(ContainerModel container, ItemModel item) {
        print(String.format(PUT_MESSAGE_FORMAT, item.getName(), container.getName()));
        container.addItem(item.getName());
        inventory.removeItem(item.getName());
        if (container.hasTriggers()) {
            processTrigger(container.getTrigger());
        }
    }

    public boolean readItem(String itemName) {
        ItemModel item = getInventoryItem(itemName);
        if (item != null) {
            String message = item.getMessage();
            if (message != null && !message.isEmpty()) {
                print(message);
            } else {
                print(NOTHING_MESSAGE);
            }
            return true;
        }
        return false;
    }

    public boolean openContainer(String containerName) {
        ContainerModel container = getContainerInCurrentRoomByName(containerName);
        if (container != null) {
            if (container.hasItems()) {
                print(String.format(CONTAINS_MESSAGE_FORMAT, container.getName(), container.itemsToString()));

            } else {
                print(String.format(EMPTY_MESSAGE_FORMAT, containerName));
            }
            return true;
        }
        return false;
    }

    private ContainerModel getContainerInCurrentRoomByName(String name) {
        List<String> containers = currentRoom.getContainers();
        for (String containerName : containers) {
            if (containerName.equals(name)) {
                return getContainerByName(containerName);
            }
        }
        return null;
    }

    private List<ContainerModel> getContainersInCurrentRoom() {
        List<ContainerModel> result = new ArrayList<ContainerModel>();
        List<String> containers = currentRoom.getContainers();
        for (String containerName : containers) {
            result.add(getContainerByName(containerName));
        }
        return result;
    }

    private ItemModel getInventoryItem(String itemName) {
        if (inventory.contains(itemName)) {
            return getItem(itemName);
        }
        return null;
    }

    public abstract String getGameFile();

    public List<JaxcraftCommand> getCommandExecutors() {
        return executors;
    }

    private void printCurrentRoom() {
        print(currentRoom.getDescription());
    }

    private boolean isCurrentRoomContainsItem(String itemName) {
        boolean inRoom = currentRoom.getItem(itemName);
        boolean inContainers = false;
        List<String> containers = currentRoom.getContainers();
        for (String containerName : containers) {
            ContainerModel container = getContainerByName(containerName);
            if (container != null && container.contains(itemName)) {
                inContainers = true;
                break;
            }
        }
        return inRoom || inContainers;
    }

    private void print(Collection<String> messages) {
        jaxcraftConsole.consolePrint(messages.toArray(new String[]{}));
    }

    protected void print(String... messages) {
        jaxcraftConsole.consolePrint(messages);
    }

    @Override
    public void setConsole(JaxcraftConsole jaxcraftConsole) {
        this.jaxcraftConsole = jaxcraftConsole;
    }
}

package org.jaxclipse.jaxcraft.model;

import org.jaxclipse.jaxcraft.command.CommandProvider;

public class JaxcraftContext {
    JaxcraftWorldMap gameModel;

    CommandProvider commandProvider;

    InventoryContainer inventoryContainer;

    RoomModel currentRoom;

    String prompt;

    String user;

    public JaxcraftContext(JaxcraftWorldMap gameModel) {
        super();
        this.gameModel = gameModel;
        this.inventoryContainer = new InventoryContainerImpl();
        this.commandProvider = new CommandProvider();
    }

    public JaxcraftWorldMap getGameModel() {
        return gameModel;
    }

    public void setGameModel(JaxcraftWorldMap gameModel) {
        this.gameModel = gameModel;
    }

    public CommandProvider getCommandProvider() {
        return commandProvider;
    }

    public void setCommandProvider(CommandProvider commandProvider) {
        this.commandProvider = commandProvider;
    }

    public InventoryContainer getInventoryContainer() {
        return inventoryContainer;
    }

    public void setInventoryContainer(InventoryContainer inventoryContainer) {
        this.inventoryContainer = inventoryContainer;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}

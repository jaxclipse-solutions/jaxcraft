package org.jaxclipse.jaxcraft.game;

import java.util.Collection;

public interface JaxcraftConsole {

    public void setShowPrompt(boolean showPrompt);

    public String getLineInput();

    public String getLineInput(String promptTextOverride);

    public void consolePrint(Collection<String> messages);

    public void consolePrint(String... messages);

}

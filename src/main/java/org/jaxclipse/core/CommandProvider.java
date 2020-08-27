package org.jaxclipse.core;

import org.apache.commons.lang3.NotImplementedException;
import org.jaxclipse.core.command.AbstractCommand;
import org.jaxclipse.core.command.AttackCommand;
import org.jaxclipse.core.command.ExitCommand;
import org.jaxclipse.core.command.InventoryCommand;
import org.jaxclipse.core.command.NavigationCommand;
import org.jaxclipse.core.command.OpenCommand;
import org.jaxclipse.core.command.PutCommand;
import org.jaxclipse.core.command.ReadCommand;
import org.jaxclipse.core.command.TakeCommand;
import org.jaxclipse.core.command.TurnOnCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Inject provider for commands as factory
 * 
 * @author dbimko
 * 
 */
public class CommandProvider
{

	Map<Class<? extends AbstractCommand>, AbstractCommand> commandMap;

	public CommandProvider()
	{
		commandMap = new HashMap<>();
		commandMap.put(NavigationCommand.class, new NavigationCommand());
		commandMap.put(InventoryCommand.class, new InventoryCommand());
		commandMap.put(TakeCommand.class, new TakeCommand());
		commandMap.put(ReadCommand.class, new ReadCommand());
		commandMap.put(TurnOnCommand.class, new TurnOnCommand());
		commandMap.put(AttackCommand.class, new AttackCommand());
		commandMap.put(OpenCommand.class, new OpenCommand());
		commandMap.put(PutCommand.class, new PutCommand());
		commandMap.put(ExitCommand.class, new ExitCommand());

	}

	public AbstractCommand get() {
		throw new NotImplementedException("no abstract command to get.");
	}

	public AbstractCommand get(Class<? extends AbstractCommand> cls) {
		return commandMap.get(cls);
	}

}

package org.jaxclipse.core;

import com.google.common.collect.Lists;

import org.apache.commons.lang3.NotImplementedException;
import org.jaxclipse.core.command.JaxcraftCommand;
import org.jaxclipse.core.command.AttackCommand;
import org.jaxclipse.core.command.ExitCommand;
import org.jaxclipse.core.command.InventoryCommand;
import org.jaxclipse.core.command.NavigationCommand;
import org.jaxclipse.core.command.OpenCommand;
import org.jaxclipse.core.command.PutCommand;
import org.jaxclipse.core.command.ReadCommand;
import org.jaxclipse.core.command.TakeCommand;
import org.jaxclipse.core.command.TurnOnCommand;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Inject provider for commands as factory
 * 
 * @author dbimko
 * 
 */
public class CommandProvider
{

	Map<Class<? extends JaxcraftCommand>, JaxcraftCommand> commandMap;

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

	public List<JaxcraftCommand> getAllCommands()
	{

		return commandMap.values().stream().collect(Collectors.toList());

	}

	public JaxcraftCommand get(Class<? extends JaxcraftCommand> cls)
	{
		return commandMap.get(cls);
	}

}

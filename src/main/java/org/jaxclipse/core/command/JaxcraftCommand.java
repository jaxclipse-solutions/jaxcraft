package org.jaxclipse.core.command;

import java.util.List;

import org.jaxclipse.core.UserCommand;
import org.jaxclipse.jaxcraft.core.game.Game;

/**
 * This abstract class is the superclass of all classes representing an commands.
 * 
 * @author dbimko
 * 
 * @see AttackCommand
 * @see InventoryCommand
 * @see NavigationCommand
 * @see OpenCommand
 * @see PutCommand
 * @see ReadCommand
 * @see TakeCommand
 * @see TurnOnCommand
 * 
 */
public abstract class JaxcraftCommand
{

	/**
	 * Returns an array of all recognized commands
	 * 
	 * @return an array of commands
	 */
	public abstract List<String> getCommands();

	/**
	 * Execute command
	 * 
	 * @param command
	 *            Contains parameter for command
	 * @return
	 */
	public abstract boolean execute(UserCommand command, Game game);
}

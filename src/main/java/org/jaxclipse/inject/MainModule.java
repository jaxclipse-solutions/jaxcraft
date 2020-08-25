package org.jaxclipse.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import org.jaxclipse.GameExecutor;
import org.jaxclipse.GameImpl;
import org.jaxclipse.base.Game;
import org.jaxclipse.core.GameFileParserImpl;
import org.jaxclipse.core.InventoryContainer;
import org.jaxclipse.core.InventoryContainerImpl;
import org.jaxclipse.core.command.NavigationCommand;

public class MainModule extends AbstractModule {

	@Override
	protected void configure() {

		bind(GameFileParserImpl.class);
		bind(Game.class).to(GameImpl.class).in(Singleton.class);

		bind(InventoryContainer.class).to(InventoryContainerImpl.class).in(
				Singleton.class);
		bind(GameExecutor.class).in(Singleton.class);
		bind(NavigationCommand.class);

	}
}

package org.jaxclipse.jaxcraft.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DirectionType
{
	NORTH("north", "n"), SOUTH("south", "s"), EAST("east", "e"), WEST("west", "w");

	@Getter
	private String name;

	@Getter
	private String shortName;
}

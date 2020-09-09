package org.jaxclipse.jaxcraft.model.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoomType
{
	NONE("none"), EXIT("exit");

	@Getter
	private String name;
}
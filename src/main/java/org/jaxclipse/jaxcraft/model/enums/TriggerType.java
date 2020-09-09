package org.jaxclipse.jaxcraft.model.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TriggerType
{
	PERMANENT("permanent"), SINGLE("single");

	@Getter
	private String type;
}

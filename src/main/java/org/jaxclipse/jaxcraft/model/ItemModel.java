package org.jaxclipse.jaxcraft.model;

import lombok.Data;

@Data
public class ItemModel implements HasStatus
{
	private String name;
	private String message;
	private String status;
	private TurnOnModel turnOnModel;
}

package org.jaxclipse.jaxcraft.model;

import lombok.Data;

@Data
public class CreatureModel
{
	private String name;

	private String vulnerability;

	private TriggerModel trigger;

	private AttackModel attack;

	private boolean exists = true;

}

package org.jaxclipse.jaxcraft.model;

import org.jaxclipse.jaxcraft.model.enums.ActionType;

import lombok.Data;

@Data
public class ActionModel
{
	private String item;
	private String status;
	private String owner;
	private ActionType actionType;

	public ActionModel(ActionType actionType)
	{
		this.actionType = actionType;
	}
}

package org.jaxclipse.jaxcraft.model;

import org.jaxclipse.jaxcraft.model.enums.DirectionType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BorderModel
{
	private String name;
	private DirectionType direction;

}

package org.jaxclipse.jaxcraft.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AttackModel
{
	private ConditionModel condition;

	private List<String> actions = new ArrayList<String>();

	private List<String> message = new ArrayList<String>();

}

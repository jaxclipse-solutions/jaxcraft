package org.jaxclipse.jaxcraft.model;

import org.jaxclipse.jaxcraft.model.enums.TriggerType;

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
public class TriggerModel
{

	private TriggerType type;

	private List<String> messages = new ArrayList<>();

	private ConditionModel condition;

	private String command;

	private String action;

}

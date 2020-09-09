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
public class ContainerModel
{
	private String name;
	private String status;
	private String accept;
	private List<String> items = new ArrayList<>();
	private TriggerModel trigger;
}

package org.jaxclipse.jaxcraft.model;


import org.jaxclipse.jaxcraft.model.enums.RoomType;

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
public class RoomModel

{
	private String name;
	private String description;
	private String status;
	private List<BorderModel> borders = new ArrayList<>();
	private List<String> items = new ArrayList<>();
	private List<TriggerModel> triggers = new ArrayList<>();
	private List<String> containers = new ArrayList<>();
	private RoomType type = RoomType.NONE;

}
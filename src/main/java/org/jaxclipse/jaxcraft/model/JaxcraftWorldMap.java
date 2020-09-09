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
public class JaxcraftWorldMap
{

	private List<RoomModel> rooms = new ArrayList<>();

	private List<ItemModel> items = new ArrayList<>();

	private List<ContainerModel> objects = new ArrayList<>();

	private List<CreatureModel> creatures = new ArrayList<>();

}

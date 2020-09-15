package org.jaxclipse.jaxcraft.model;


import org.jaxclipse.jaxcraft.model.enums.DirectionType;
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
public class RoomModel implements ItemContainer

{
	private String name;
	private String description;
	private String status;
	private List<BorderModel> borders = new ArrayList<>();
	private List<String> items = new ArrayList<>();
	private List<TriggerModel> triggers = new ArrayList<>();
	private List<String> containers = new ArrayList<>();
	private RoomType type = RoomType.NONE;

	public String nextRoom(DirectionType direction)
	{
		BorderModel border = getBorder(direction);
		if (border != null)
		{
			return border.getName();
		}
		return null;
	}

	public BorderModel getBorder(DirectionType direction)
	{
		BorderModel result = null;
		for (BorderModel b : borders)
		{
			if (b.getDirection().equals(direction))
			{
				result = b;
				break;
			}
		}
		return result;
	}

	public void addItem(String item)
	{
		items.add(item);
	}

	public void removeItem(String item)
	{
		items.remove(item);
	}

	public boolean getItem(String name)
	{
		for (String itemName : items)
		{
			if (itemName.equalsIgnoreCase(name))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean contains(String itemName)
	{
		if (itemName == null)
		{
			return false;
		}
		for (String item : items)
		{
			if (item.equals(itemName))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasItems()
	{
		return items.size() > 0;
	}

	@Override
	public boolean hasTriggers()
	{
		return triggers.size() > 0;
	}

	@Override
	public String itemsToString()
	{
		StringBuilder sb = new StringBuilder();
		for (String item : items)
		{
			sb.append(item);
			sb.append(", ");
		}
		sb.setLength(sb.length() - 2);
		return sb.toString();
	}
}
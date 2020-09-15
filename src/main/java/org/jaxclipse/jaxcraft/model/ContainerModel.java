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
public class ContainerModel implements ItemContainer
{
	private String name;
	private String status;
	private String accept;
	private List<String> items = new ArrayList<>();
	private TriggerModel trigger;

	@Override
	public void addItem(String itemName)
	{
		items.add(itemName);

	}

	@Override
	public void removeItem(String itemName)
	{
		items.remove(itemName);

	}

	@Override
	public boolean contains(String itemName)
	{
		return items.contains(itemName);

	}

	@Override
	public boolean hasItems()
	{
		return items.size() > 0;

	}

	@Override
	public boolean hasTriggers()
	{
		return trigger != null;

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

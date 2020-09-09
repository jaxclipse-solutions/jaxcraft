package org.jaxclipse.jaxcraft.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jaxclipse.jaxcraft.model.enums.DirectionType;
import org.jaxclipse.jaxcraft.model.enums.TriggerType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SampleWorldTest
{

	@SuppressWarnings("serial")
	@Test
	public void testSampleWorld() throws Exception
	{

		RoomModel entrance = new RoomModel();
		entrance.setName("Entrance");
		entrance.setDescription("You find yourself at the mouth of a cave and decide that in spite of common sense and any sense of self preservation that you're going to go exploring north into it.  It's a little dark, but luckily there are some torches on the wall.");
		entrance.setItems(new ArrayList<String>()
		{
			{
				add("torch");
			}
		});
		entrance.setBorders(new ArrayList<BorderModel>()
		{
			{
				add(new BorderModel("MainCavern", DirectionType.NORTH));
			}
		});
		List<String> messages = new ArrayList<>();
		messages.add("*stumble* need some light...");
		ConditionModel cm = new ConditionModel();
		cm.setHas(false);
		cm.setObject("torch");
		cm.setOwner("inventory");
		entrance.setTriggers(new ArrayList<TriggerModel>()
		{
			{
				add(
						new TriggerModel(TriggerType.PERMANENT,
								messages, cm, "n", null));
			}
		});

		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.writeValue(System.out, entrance);

	}
}

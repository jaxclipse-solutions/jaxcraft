package org.jaxclipse.jaxcraft.model;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jaxclipse.jaxcraft.model.enums.DirectionType;
import org.jaxclipse.jaxcraft.model.enums.RoomType;
import org.jaxclipse.jaxcraft.model.enums.TriggerType;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SampleWorldTest
{

	@Test
	public void testMakeWorld() throws Exception
	{
		String world = "{\"rooms\":[{\"name\":\"Entrance\",\"description\":\"You find yourself at the mouth of a cave and decide that in spite of common sense and any sense of self preservation that you're going to go exploring north into it.  It's a little dark, but luckily there are some torches on the wall.\",\"status\":null,\"borders\":[{\"name\":\"MainCavern\",\"direction\":{\"name\":\"north\",\"shortName\":\"n\"}}],\"items\":[\"torch\"],\"triggers\":[{\"type\":\"PERMANENT\",\"messages\":[\"*stumble* need some light...\"],\"condition\":{\"has\":false,\"object\":\"torch\",\"owner\":\"inventory\",\"status\":null},\"command\":\"n\",\"action\":null}],\"containers\":[],\"type\":\"NONE\"},{\"name\":\"MainCavern\",\"description\":\"A huge cavern surrounds you with a locked door to the north, a chest in the center, and a very dark corner...\",\"status\":null,\"borders\":[{\"name\":\"Entrance\",\"direction\":{\"name\":\"south\",\"shortName\":\"s\"}},{\"name\":\"Staircase\",\"direction\":{\"name\":\"north\",\"shortName\":\"n\"}}],\"items\":[],\"triggers\":[{\"type\":\"PERMANENT\",\"messages\":[\"lock needs a key... not to mention you don't want to get too close to that side of the room... there's something in that corner...\"],\"condition\":{\"has\":false,\"object\":\"lock\",\"owner\":null,\"status\":\"locked\"},\"command\":\"n\",\"action\":null}],\"containers\":[\"chest\",\"lock\"],\"type\":\"NONE\"},{\"name\":\"Staircase\",\"description\":\"You found the exit!\",\"status\":null,\"borders\":[{\"name\":\"MainCavern\",\"direction\":{\"name\":\"south\",\"shortName\":\"s\"}}],\"items\":[],\"triggers\":[],\"containers\":[],\"type\":\"EXIT\"}],\"items\":[{\"name\":\"torch\",\"message\":\"next to a small button it reads \\\"push for big flame\\\"\",\"status\":\"lit\",\"turnOnModel\":{\"message\":\"the torch has erupted into a menacing inferno\",\"action\":\"update torch to inferno\"}},{\"name\":\"explosive\",\"message\":\"turn on for boom :-). Warning!  Keep away from gnomes!\",\"status\":\"idle\",\"turnOnModel\":{\"message\":\"you hear ticking...\",\"action\":\"Update explosive to ticking\"}},{\"name\":\"key\",\"message\":\"Exit\",\"status\":null,\"turnOnModel\":null}],\"objects\":[{\"name\":\"lock\",\"status\":\"locked\",\"accept\":\"key\",\"items\":[],\"trigger\":{\"type\":null,\"messages\":[\"The lock drops off and the door opens\"],\"condition\":{\"has\":true,\"object\":\"key\",\"owner\":\"lock\",\"status\":null},\"command\":null,\"action\":\"Update lock to unlocked\"}}],\"creatures\":[]}";
		ObjectMapper objectMapper = new ObjectMapper();

		JaxcraftWorldMap map = objectMapper.readValue(world, new TypeReference<JaxcraftWorldMap>()
		{
		});

		System.out.println("---");
		System.out.println(map);
		System.out.println("---");

	}

	@Test
	public void testUnmarshalRoom() throws Exception
	{
		// String room = "{\"name\":\"Staircase\",\"description\":\"You found the
		// exit!\",\"status\":null,\"borders\":[{\"name\":\"MainCavern\",\"direction\":\"SOUTH\"}],\"items\":[],\"triggers\":[],\"containers\":[],\"type\":\"EXIT\"}";
		String room = "{\"name\":\"Staircase\",\"description\":\"You found the exit!\",\"status\":null,\"borders\":[{\"name\":\"MainCavern\",\"direction\":{\"name\":\"south\",\"shortName\":\"s\"}}],\"items\":[],\"triggers\":[],\"containers\":[],\"type\":\"EXIT\"}";

		ObjectMapper objectMapper = new ObjectMapper();
		RoomModel stairCase = objectMapper.readValue(room, new TypeReference<RoomModel>()
		{
		});

		System.out.println("---");
		System.out.println(stairCase);
		System.out.println("---");

	}

	@Test
	public void testUnmarshalBorder() throws Exception
	{
		try
		{

			// BorderModel mcbm = new BorderModel("MainCavern", DirectionType.SOUTH);
			ObjectMapper objectMapper = new ObjectMapper();

			// objectMapper.writeValue(System.out, DirectionType.NORTH);
			// objectMapper.writeValue(System.out, DirectionType.SOUTH);
			objectMapper.writeValue(System.out, DirectionType.WEST);

			// DirectionType dt = objectMapper.readValue("SOUTH", new TypeReference<DirectionType>() {
			// });

			// objectMapper.writeValue(System.out, dt);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	@Test
	public void testMarshalBorder()
	{
		try
		{
			String json = "{\"name\":\"west\",\"shortName\":\"w\"}";
			ObjectMapper om = new ObjectMapper();
			DirectionType dt = om.readValue(json, new TypeReference<DirectionType>()
			{
			});
			// System.out.println(dt);

			BorderModel test = new BorderModel();
			test.setName("Garage");
			test.setDirection(dt);

			// ObjectMapper om2 = new ObjectMapper();
			// om2.writeValue(System.out, test);

			String json2 = "{\"name\":\"Garage\",\"direction\":{\"name\":\"west\",\"shortName\":\"w\"}}";
			ObjectMapper om3 = new ObjectMapper();
			BorderModel ds = om3.readValue(json2, new TypeReference<BorderModel>()
			{
			});
			System.out.println(ds);
		}
		catch (Exception e)
		{
			System.out.println("Oh noz");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("serial")
	@Test
	public void testSampleWorld() throws Exception
	{

		RoomModel entrance = new RoomModel();
		entrance.setName("Entrance");
		entrance.setDescription(
				"You find yourself at the mouth of a cave and decide that in spite of common sense and any sense of self preservation that you're going to go exploring north into it.  It's a little dark, but luckily there are some torches on the wall.");
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
				add(new TriggerModel(TriggerType.PERMANENT, messages, cm, "n", null));
			}
		});

		RoomModel mainCavern = new RoomModel();
		mainCavern.setName("MainCavern");
		mainCavern.setDescription("A huge cavern surrounds you with a locked door to the north, a chest in the center, and a very dark corner...");
		mainCavern.setContainers(new ArrayList<String>()
		{
			{
				add("chest");
				add("lock");
			}
		});

		mainCavern.setBorders(new ArrayList<BorderModel>()
		{
			{
				add(new BorderModel("Entrance", DirectionType.SOUTH));
				add(new BorderModel("Staircase", DirectionType.NORTH));
			}
		});

		List<String> mainCavernMessages = new ArrayList<>();
		mainCavernMessages.add(
				"lock needs a key... not to mention you don't want to get too close to that side of the room... there's something in that corner...");
		ConditionModel mainCavernConditionModel = new ConditionModel();
		mainCavernConditionModel.setHas(false);
		mainCavernConditionModel.setObject("lock");
		mainCavernConditionModel.setStatus("locked");

		mainCavern.setTriggers(new ArrayList<TriggerModel>()
		{
			{
				add(new TriggerModel(TriggerType.PERMANENT, mainCavernMessages, mainCavernConditionModel, "n", null));
			}
		});

		RoomModel exit = new RoomModel();
		exit.setName("Staircase");
		exit.setType(RoomType.EXIT);
		exit.setDescription("You found the exit!");

		exit.setBorders(new ArrayList<BorderModel>()
		{
			{
				add(new BorderModel("MainCavern", DirectionType.SOUTH));
			}
		});

		JaxcraftWorldMap world = new JaxcraftWorldMap();
		world.setRooms(new ArrayList<RoomModel>()
		{
			{
				add(entrance);
				add(mainCavern);
				add(exit);
			}
		});

		ItemModel torch = new ItemModel();
		torch.setName("torch");
		torch.setMessage("next to a small button it reads \"push for big flame\"");
		torch.setStatus("lit");
		TurnOnModel torchOnModel = new TurnOnModel();
		torchOnModel.setAction("update torch to inferno");
		torchOnModel.setMessage("the torch has erupted into a menacing inferno");
		torch.setTurnOnModel(torchOnModel);

		ItemModel explosive = new ItemModel();
		explosive.setName("explosive");
		explosive.setMessage("turn on for boom :-). Warning!  Keep away from gnomes!");
		explosive.setStatus("idle");
		TurnOnModel explosiveOnModel = new TurnOnModel();
		explosiveOnModel.setAction("Update explosive to ticking");
		explosiveOnModel.setMessage("you hear ticking...");
		explosive.setTurnOnModel(explosiveOnModel);

		ItemModel key = new ItemModel();
		key.setMessage("Exit");
		key.setName("key");

		world.setItems(new ArrayList<ItemModel>()
		{
			{
				add(torch);
				add(explosive);
				add(key);
			}
		});

		ContainerModel lock = new ContainerModel();
		lock.setStatus("locked");
		lock.setName("lock");
		lock.setAccept("key");

		TriggerModel lockTriggerModel = new TriggerModel();
		lockTriggerModel.setAction("Update lock to unlocked");
		lockTriggerModel.setMessages(new ArrayList<String>()
		{
			{
				add("The lock drops off and the door opens");
			}
		});
		ConditionModel lockConditionModel = new ConditionModel();
		lockConditionModel.setHas(true);
		lockConditionModel.setOwner("lock");
		lockConditionModel.setObject("key");

		lockTriggerModel.setCondition(lockConditionModel);
		lock.setTrigger(lockTriggerModel);

		world.setObjects(new ArrayList<ContainerModel>()
		{
			{
				add(lock);
			}
		});

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(System.out, world);

	}

	@Test
	public void testCreature() throws JsonGenerationException, JsonMappingException, IOException
	{
		CreatureModel cm = new CreatureModel();
		cm.setName("gnome");
		cm.setVulnerability("explosive");

		AttackModel am = new AttackModel();
		am.setActions(new ArrayList<String>()
		{
			{
				add("Add key to MainCavern");
				add("Delete gnome");
			}
		});
		am.setMessage(new ArrayList<String>()
		{
			{
				add("But instead of exploding it plays a song and the gnome gets up and dances a jig before disappearing.  Funny, but you find he was sitting on a key!");
			}
		});
		ConditionModel conditionModel = new ConditionModel();
		conditionModel.setObject("explosive");
		conditionModel.setStatus("ticking");
		am.setCondition(conditionModel);
		cm.setAttack(am);

		ConditionModel cm2 = new ConditionModel();
		cm2.setObject("torch");
		cm2.setStatus("inferno");

		TriggerModel tm = new TriggerModel();
		tm.setType(TriggerType.SINGLE);
		tm.setMessages(new ArrayList<String>()
		{
			{
				add("You see a gnome in the dark corner... watching you with its super pointy hat...");
			}
		});
		tm.setCondition(cm2);
		cm.setTrigger(tm);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(System.out, cm);
		assertTrue(true);
	}

}

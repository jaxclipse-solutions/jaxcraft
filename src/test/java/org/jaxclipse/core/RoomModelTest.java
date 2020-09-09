package org.jaxclipse.core;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jaxclipse.jaxcraft.model.BorderModel;
import org.jaxclipse.jaxcraft.model.RoomModel;
import org.jaxclipse.jaxcraft.model.TriggerModel;
import org.jaxclipse.jaxcraft.model.enums.DirectionType;
import org.jaxclipse.jaxcraft.model.enums.RoomType;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class RoomModelTest
{

	@Test
	public void testRoomModel()
	{
		boolean breakOnStart = false;
		assertTrue(!breakOnStart);
	}

	@Test
	public void testBorderItem() throws JsonGenerationException, JsonMappingException, IOException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		BorderModel bi = new BorderModel("testBorder", DirectionType.EAST);
		// objectMapper.writeValue(System.out, bi);
		List<BorderModel> borders = new ArrayList<>();
		borders.add(bi);

		List<String> items = new ArrayList<>();
		List<TriggerModel> triggers = new ArrayList<>();
		List<String> containers = new ArrayList<>();
		
		RoomModel rm = new RoomModel("testRoom", "testRoomDescription", "testStatus"
				, borders, items, triggers, containers, RoomType.NONE);

		objectMapper.writeValue(System.out, rm);

		// {"name":"testRoom","description":"testRoomDescription","status":"testStatus","borders":[{"name":"testBorder","direction":"EAST"}],"items":[],"triggers":[],"containers":[],"type":"NONE"}
	}
}

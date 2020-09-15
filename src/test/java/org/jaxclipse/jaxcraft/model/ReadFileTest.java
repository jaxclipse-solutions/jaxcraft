package org.jaxclipse.jaxcraft.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import java.io.File;

public class ReadFileTest
{

	@Test
	public void testReadFile() throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();

		JaxcraftWorldMap world = mapper.readValue(new File("C:\\Users\\jeffery.caldwell\\projects\\jaxcraft\\src\\main\\resources\\world.json"),
				JaxcraftWorldMap.class);

		System.out.println(world);
	}
}

package org.jaxclipse.jaxcraft.game;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jaxclipse.jaxcraft.game.exception.GameFileParseException;
import org.jaxclipse.jaxcraft.model.JaxcraftWorldMap;

import java.io.File;
import java.io.InputStream;

public class GameFileParserImpl
{

	private FileHelper fileHelper = new FileHelper();

	public JaxcraftWorldMap parse(String filename) throws GameFileParseException
	{
		try
		{
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);

			// InputStream streamContent = fileHelper.fileAsStream(filename);
			InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
			JaxcraftWorldMap world = objectMapper
					.readValue(new File("src\\main\\resources\\world.json"), JaxcraftWorldMap.class);

			// JaxcraftWorldMap map = objectMapper.readValue(in, JaxcraftWorldMap.class);

			return world;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new GameFileParseException(e);
		}
	}
}

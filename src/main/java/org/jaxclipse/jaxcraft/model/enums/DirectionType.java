package org.jaxclipse.jaxcraft.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = DirectionType.DirectionTypeDeserializer.class)

public enum DirectionType {
    NORTH("north", "n"), SOUTH("south", "s"), EAST("east", "e"), WEST("west", "w");

    private String name;

    private String shortName;

    private DirectionType(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }


    public String getName() {
        return this.name;
    }

    public String getShortName() {
        return this.shortName;
    }

    public static class DirectionTypeDeserializer extends StdDeserializer<DirectionType> {
        public DirectionTypeDeserializer() {
            super(DirectionType.class);
        }

        @Override
        public DirectionType deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
            final JsonNode jsonNode = jp.readValueAsTree();
            String name = jsonNode.get("name").asText();
            String shortName = jsonNode.get("shortName").asText();

            for (DirectionType me : DirectionType.values()) {
                if (me.getName().equals(name) && me.getShortName().equals(shortName)) {
                    return me;
                }
            }
            throw dc.mappingException("Cannot deserialize DirectionType from name " + name + " and shortName " + shortName);
        }
    }

	public static DirectionType getByName(String name)
	{
		DirectionType[] directions = DirectionType.values();
		for (DirectionType d : directions)
		{
			if (d.name.equals(name))
			{
				return d;
			}
		}
		return null;
	}

	public static DirectionType getByShortName(String name)
	{
		DirectionType[] directions = DirectionType.values();
		for (DirectionType d : directions)
		{
			if (d.shortName.equals(name))
			{
				return d;
			}
		}
		return null;
	}
}


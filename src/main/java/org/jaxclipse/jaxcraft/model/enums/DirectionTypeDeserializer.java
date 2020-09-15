package org.jaxclipse.jaxcraft.model.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;

public class DirectionTypeDeserializer extends JsonDeserializer {
    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        DirectionType type = DirectionType.valueOf(p.getValueAsString());
        if (type != null){
            return type;
        }
        throw new JsonMappingException("invalid value for type, must be valid");

    }
}

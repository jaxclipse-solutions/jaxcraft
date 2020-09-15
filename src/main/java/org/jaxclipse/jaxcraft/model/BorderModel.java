package org.jaxclipse.jaxcraft.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import org.jaxclipse.jaxcraft.model.enums.DirectionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jaxclipse.jaxcraft.model.enums.DirectionTypeDeserializer;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorderModel {
    private String name;
    //@JsonDeserialize(using = DirectionTypeDeserializer.class)

    private DirectionType direction;

}

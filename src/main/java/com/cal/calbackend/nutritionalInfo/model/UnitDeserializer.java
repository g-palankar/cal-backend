package com.cal.calbackend.nutritionalInfo.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import tech.units.indriya.unit.Units;

import javax.measure.Unit;
import java.io.IOException;

public class UnitDeserializer extends StdDeserializer<Unit<?>> {

    public UnitDeserializer() {
        this(null);
    }

    public UnitDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Unit<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String unitString = node.asText();
        return Units.getInstance().getUnit(unitString);
    }
}

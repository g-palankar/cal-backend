package com.cal.calbackend.nutritionalInfo.searializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import tech.units.indriya.unit.Units;

import javax.measure.Unit;
import java.io.IOException;

public class UnitDeserializer extends StdDeserializer<Unit> {

    public UnitDeserializer() {
        super(Unit.class);
    }

    @Override
    public Unit deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String symbol = node.get("symbol").asText();
        return Units.getInstance().getUnit(symbol);
    }
}

package com.cal.calbackend.nutritionalInfo.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import javax.measure.Unit;
import java.io.IOException;

public class UnitSerializer extends StdSerializer<Unit> {

    public UnitSerializer() {
        this(null);
    }

    public UnitSerializer(Class<Unit> t) {
        super(t);
    }


    @Override
    public void serialize(Unit value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeString(value.toString());
    }
}

package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.LocalDate;

import java.io.IOException;

/**
 * For dates like 20151123
 */
class SquishedDateDeserializer extends JsonDeserializer<LocalDate> {


    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        String textDate = jsonParser.getText();
        return new LocalDate(
                Integer.valueOf(textDate.substring(0, 4)),
                Integer.valueOf(textDate.substring(4, 6)),
                Integer.valueOf(textDate.substring(6, 8))
        );
    }
}


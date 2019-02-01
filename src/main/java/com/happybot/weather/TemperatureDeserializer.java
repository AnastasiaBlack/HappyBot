package com.happybot.weather;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class TemperatureDeserializer extends JsonDeserializer<Temperature> {

    @Override
    public Temperature deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        final Double temp = node.get("temp").asDouble();
        final Double humidity = node.get("humidity").asDouble();
        final Double pressure = node.get("pressure").asDouble();
        final Double temp_max = node.get("temp_max").asDouble();
        final Double temp_min = node.get("temp_min").asDouble();

        com.happybot.weather.Temperature temperature = new com.happybot.weather.Temperature();
        temperature.temp = temp;
        temperature.humidity = humidity;
        temperature.pressure = pressure;
        temperature.temp_max = temp_max;
        temperature.temp_min = temp_min;

        return temperature;
    }
}

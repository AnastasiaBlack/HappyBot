package com.happybot.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    @JsonDeserialize(using = TemperatureDeserializer.class)
    public Temperature main;

    public Weather(Temperature main) {
        this.main = main;
    }

    public Weather() {

    }
}
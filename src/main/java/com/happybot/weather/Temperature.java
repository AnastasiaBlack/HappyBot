package com.happybot.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature {
    public Double temp;
    public Double pressure;
    public Double humidity;
    public Double temp_min;
    public Double temp_max;

    public double getTemperatureCelsius() {
        return temp - 273.15;
    }
}

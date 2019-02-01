package com.happybot.core;

import com.happybot.quote.Quote;
import com.happybot.weather.WeatherService;

public class MessageContentBuilder {

    public String getQuote() {
        return new Quote().getQuote();
    }

    public String getTodaysWeather() {
        return new WeatherService().getWeather("Dnipro");
    }
}

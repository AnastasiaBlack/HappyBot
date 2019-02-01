package com.happybot.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherService {

    private final String API_SERVER = "http://api.openweathermap.org/data/2.5/weather?";
    private final String APP_ID = "36b59b6130d3cae6462241d6fbf65c1a";

    public String getWeather(String cityName) {
        String requestUri = API_SERVER + "q=" + cityName + "&APPID=" + APP_ID;
        String responseString = jsonGetRequest(requestUri);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Temperature.class, new TemperatureDeserializer());
        mapper.registerModule(module);
        Weather weather = null;
        try {
            weather = mapper.readValue(responseString, Weather.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather != null ? getWeatherMessage(weather) : "Some temperature";
    }

    private String getWeatherMessage(Weather weather) {
        return "\nToday's weather:\nTemperature: " + weather.main.getTemperatureCelsius() + " C" +
                "\nPressure: " + weather.main.pressure +
                "\nHumidity: " + weather.main.humidity + "%";
    }

    private static String jsonGetRequest(String urlQueryString) {
        String json = null;
        try {
            URL url = new URL(urlQueryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = new Scanner(inStream, "UTF-8").useDelimiter("\\Z").next(); // input stream to string
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        json = json.replaceAll("\r", "").replaceAll("\n", "").replaceAll(" ", "");
        return json;
    }
}

package ru.mike.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mike.adapter.dto.OpenWeatherResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        System.out
                .println(jsonGetRequest("https://api.openweathermap.org/data/2.5/weather?lat=54&lon=45&appid=667878cc40014e1d5b76fc83617cd944"));
    }

    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

    public static String jsonGetRequest(String urlQueryString) {
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
            System.out.println(connection.getResponseCode());

            InputStream inStream = connection.getInputStream();
            json = streamToString(inStream); // input stream to string

            ObjectMapper objectMapper = new ObjectMapper();
            OpenWeatherResponse openWeatherResponse = objectMapper.readValue(
                    new File("C:/Users/mike_/IdeaProjects/testTaskAdapter/Adapter/src/main/java/weather.json"),
                    OpenWeatherResponse.class);
            System.out.println(openWeatherResponse);
            openWeatherResponse.getTemp();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}

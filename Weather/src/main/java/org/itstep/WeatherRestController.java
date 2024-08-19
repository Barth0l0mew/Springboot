package org.itstep;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class WeatherRestController {
    @GetMapping({"/",""})
    public void getWether (){
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL("https://api.weather.yandex.ru/v2/forecast?lat=55.1927&lon=30.2664");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Yandex-Weather-Key","4656b7dc-a3d4-4fbe-9a77-30f8608ebe22");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        //Обработка данных
        int responseCode =0;
        try {
            responseCode = conn.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Responce code "+responseCode);
        // Чтение ответа
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String inputLine;
        StringBuilder response = new StringBuilder();

        while (true) {
            try {
                if (!((inputLine = in.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            response.append(inputLine);
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Вывод тела ответа
        System.out.println("Response Body: " + response.toString());
        //ИЗВЛЕЧЬ информацию
        System.out.println(response.indexOf("hours")+"  "+ response.indexOf(""));
        String jsonString = response.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Преобразование строки JSON в JsonNode
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode forecasts = rootNode.path("forecasts");

            // Перебор прогнозов для получения температуры на 15:00
            int i = 0;
            for (JsonNode forecast : forecasts) {
                JsonNode hours = forecast.path("hours");

                for (JsonNode hour : hours) {
                    String hourValue = hour.path("hour").asText();
                    if (Integer.toString(i).equals(hourValue)) {
                        int temperature = hour.path("temp").asInt();
                        if (i<24)
                            WeatherNow.temperatures[i++] = temperature;
                        System.out.printf("Hour: %d Temperature: %d%n", hour.path("hour").asInt(), temperature);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(WeatherNow.temperatures));
    }

}

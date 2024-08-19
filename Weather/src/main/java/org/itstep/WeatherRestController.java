package org.itstep;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class WeatherRestController {
    @GetMapping("/")
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
    }

}

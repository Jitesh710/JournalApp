package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather_api_key}")
    private String API_KEY;
    @Value("${weather_api_url}")
    private String API_URL;

    @Autowired
    private RestTemplate restTemplate;      // class to process API requests

    public WeatherResponse getWeather(String city) {
        final String finalUrl = API_URL.replace("API_KEY", API_KEY).replace("CITY", city);
        ResponseEntity<WeatherResponse> weatherResponse = restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherResponse.class);
         WeatherResponse body = weatherResponse.getBody();
         return body;
    }

}

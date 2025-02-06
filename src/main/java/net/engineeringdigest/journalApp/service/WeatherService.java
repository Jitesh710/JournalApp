package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.servlet.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather_api_key}")
    private String API_KEY;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private RestTemplate restTemplate;      // class to process API requests

    public WeatherResponse getWeather(String city) {
        final String finalUrl = cacheService.appCache.get("weather_api_key").replace("<API_KEY>", API_KEY).replace("<CITY>", city);
        ResponseEntity<WeatherResponse> weatherResponse = restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherResponse.class);
         WeatherResponse body = weatherResponse.getBody();
         return body;
    }

}

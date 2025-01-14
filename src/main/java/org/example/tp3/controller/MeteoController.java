package org.example.tp3.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class MeteoController {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${meteoconcept.api.token}")
    private String meteoconceptApiToken;
    private static final String meteoconceptApiBaseUrl = "https://api.meteo-concept.com/api/";

    private static final String ETALAB_API_URL = "https://api-adresse.data.gouv.fr/search/?q=";

    @PostMapping("/meteo")
    public String processMeteoForm(@RequestParam("address") String address, Model model) throws JsonProcessingException {


        String apiUrlEtalab = ETALAB_API_URL + address.trim().replace(" ", "+") + "&limit=1";
        String responseEtalab = restTemplate.getForObject(apiUrlEtalab, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseEtalab);
        JsonNode firstFeature = root.path("features").get(0);
        String cityName = firstFeature.path("properties").path("name").asText();
        JsonNode coordinates = firstFeature.path("geometry").path("coordinates");

        double longitude = coordinates.get(0).asDouble();
        double latitude = coordinates.get(1).asDouble();

        model.addAttribute("cityName", cityName);
        model.addAttribute("cityLatitude", latitude);
        model.addAttribute("cityLongitude", longitude);

        String apiUrlMeteoConcept = String.format("%sforecast/daily?token=%s&latlng=%.4f,%.4f", meteoconceptApiBaseUrl, meteoconceptApiToken, latitude, longitude);
        String responseMeteoConcept = restTemplate.getForObject(apiUrlMeteoConcept, String.class);

        JsonNode forecastArray = objectMapper.readTree(responseMeteoConcept).path("forecast");
        JsonNode todayForecast = forecastArray.get(0);

        model.addAttribute("tmin", todayForecast.path("tmin").asDouble());
        model.addAttribute("tmax", todayForecast.path("tmax").asDouble());
        model.addAttribute("windSpeed", todayForecast.path("wind10m").asInt());
        model.addAttribute("windGust", todayForecast.path("gust10m").asInt());
        model.addAttribute("weather", getWeatherDescription(todayForecast.path("weather").asInt()));
        model.addAttribute("rain", todayForecast.path("probarain").asInt());

        return "meteo";
    }

    private String getWeatherDescription(int weatherCode) {
        return switch (weatherCode) {
            case 0 -> "Ensoleillé";
            case 1 -> "Peu nuageux";
            case 2 -> "Partiellement nuageux";
            case 3 -> "Nuageux";
            case 4 -> "Très nuageux";
            case 40 -> "Pluie légère";
            case 41 -> "Pluie modérée";
            case 11 -> "Orage";
            case 21 -> "Neige légère";
            default -> "Conditions inconnues";
        };
    }
}

package org.example.tp3.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.tp3.service.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class MeteoController {

    @Autowired
    private GeocodingService geocodingService;

    private final RestTemplate restTemplate;

    @Autowired
    public MeteoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private static final String ETALAB_API_URL = "https://api-adresse.data.gouv.fr/search/?q=";

    @PostMapping("/meteo")
    public String processMeteoForm(@RequestParam("address") String address, Model model) throws JsonProcessingException {

        System.out.println("Adresse saisie : " + address);




        String addressmeteo = address.toLowerCase().replace(" ", "+");

        String apiUrlEtalab = ETALAB_API_URL + address + "&limit=1";
        String responseEtalab = restTemplate.getForObject(apiUrlEtalab, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseEtalab);
        JsonNode firstFeature = root.path("features").get(0).path("geometry").path("coordinates");

        double longitude = firstFeature.get(0).asDouble();
        double latitude = firstFeature.get(1).asDouble();

        // 3) Mettre ces infos dans le model Thymeleaf
        model.addAttribute("userAddress", address);
        model.addAttribute("latitude", latitude);
        model.addAttribute("longitude", longitude);
        model.addAttribute("userAddress", address);

        return "meteo";
    }
}

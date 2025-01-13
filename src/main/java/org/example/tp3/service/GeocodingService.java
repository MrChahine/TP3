package org.example.tp3.service;

import org.example.tp3.Response.GeoApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GeocodingService {

    private final RestTemplate restTemplate;

    public GeocodingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String geocodeAddress(String address) {
        String apiUrl = "https://api-adresse.data.gouv.fr/search/?q=" + address;
        return restTemplate.getForObject(apiUrl, String.class);
    }
}

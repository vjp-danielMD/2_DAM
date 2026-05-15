package com.iesvjp.countriesfx_danielmoreno.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iesvjp.countriesfx_danielmoreno.model.Country;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.util.*;

public class ApiService {
    // URL limitada a campos específicos para evitar el error 400
    private static final String API_URL = "https://restcountries.com/v3.1/all?fields=name,capital,region,subregion,population,currencies,languages,flags,translations";

    public static List<Country> getCountries() throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Accept", "application/json")
                .header("User-Agent", "JavaFX-App")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            List<Country> list = new Gson().fromJson(response.body(), new TypeToken<ArrayList<Country>>(){}.getType());
            list.sort(Comparator.comparing(Country::getNameEs, String.CASE_INSENSITIVE_ORDER));
            return list;
        } else {
            throw new Exception("Error HTTP: " + response.statusCode());
        }
    }
}
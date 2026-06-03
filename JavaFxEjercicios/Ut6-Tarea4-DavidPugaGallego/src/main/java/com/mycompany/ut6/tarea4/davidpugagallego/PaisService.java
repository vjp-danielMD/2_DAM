package com.mycompany.ut6.tarea4.davidpugagallego;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class PaisService {

private static final String API_URL = "https://restcountries.com/v3.1/all?fields=name,capital,region,subregion,population,currencies,languages,flags";
    public List<Pais> obtenerTodosLosPaises() throws Exception {
        List<Pais> listaPaises = new ArrayList<>();

        // 1. Crear el cliente HTTP y la petición (AÑADIMOS CABECERAS PARA EVITAR HTTP 400)
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Accept", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) JavaFX_App")
                .GET()
                .build();

        // 2. Enviar la petición y obtener respuesta en String
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Si devuelve algo raro, imprimimos la respuesta en consola para espiarlo
        if (response.statusCode() != 200) {
            System.out.println("RESPUESTA DEL SERVIDOR: " + response.body());
            throw new RuntimeException("Error al conectar con la API. Código: " + response.statusCode());
        }

        // 3. Parsear el JSONArray de la respuesta
        JSONArray jsonArray = new JSONArray(response.body());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonPais = jsonArray.getJSONObject(i);
            Pais pais = new Pais();

            // Nombre común y oficial
            JSONObject nameObj = jsonPais.getJSONObject("name");
            pais.setNombre(nameObj.getString("common"));
            pais.setNombreOficial(nameObj.optString("official", "N/A"));

            // Capital (es un array en el JSON, pillamos la primera)
            if (jsonPais.has("capital") && !jsonPais.getJSONArray("capital").isEmpty()) {
                pais.setCapital(jsonPais.getJSONArray("capital").getString(0));
            } else {
                pais.setCapital("N/A");
            }

            // Región y Subregión
            pais.setRegion(jsonPais.optString("region", "N/A"));
            pais.setSubregion(jsonPais.optString("subregion", "N/A"));
            pais.setPoblacion(jsonPais.optLong("population", 0));

            // Parsear Monedas (vienen con códigos dinámicos como "USD", "EUR")
            if (jsonPais.has("currencies")) {
                JSONObject currenciesObj = jsonPais.getJSONObject("currencies");
                List<String> listaMonedas = new ArrayList<>();
                for (String key : currenciesObj.keySet()) {
                    JSONObject m = currenciesObj.getJSONObject(key);
                    listaMonedas.add(m.getString("name") + " (" + key + ")");
                }
                pais.setMonedas(String.join(", ", listaMonedas));
            } else {
                pais.setMonedas("N/A");
            }

            // Parsear Idiomas
            if (jsonPais.has("languages")) {
                JSONObject languagesObj = jsonPais.getJSONObject("languages");
                List<String> listaIdiomas = new ArrayList<>();
                for (String key : languagesObj.keySet()) {
                    listaIdiomas.add(languagesObj.getString(key));
                }
                pais.setIdiomas(String.join(", ", listaIdiomas));
            } else {
                pais.setIdiomas("N/A");
            }

            // Bandera (usaremos la imagen en formato PNG)
            if (jsonPais.has("flags")) {
                pais.setUrlBandera(jsonPais.getJSONObject("flags").getString("png"));
            }

            listaPaises.add(pais);
        }

        // Ordenar alfabéticamente por nombre común
        listaPaises.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));

        return listaPaises;
    }
}
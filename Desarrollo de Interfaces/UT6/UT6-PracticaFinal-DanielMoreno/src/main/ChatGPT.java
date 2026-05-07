package main;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * 
 * @author daniel moreno
 */

public class ChatGPT {
    private final String nombreModelo = "qwen/qwen3-vl-4b";
    private final String url = "http://127.0.0.1:1234/v1/chat/completions";

    public String enviarPregunta(String prompt) throws Exception {
        String textoLimpio = prompt.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", " ").trim();
        String jsonBody = "{\"model\": \"" + nombreModelo + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + textoLimpio + "\"}], \"stream\": false}";

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .proxy(HttpClient.Builder.NO_PROXY)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Connection", "close")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        // Usamos la otra clase para limpiar el resultado
        return RecibirRespuesta.limpiarJSON(response.body());
    }
}
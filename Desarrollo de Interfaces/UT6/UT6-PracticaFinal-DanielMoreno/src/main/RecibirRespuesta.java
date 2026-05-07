package main;

public class RecibirRespuesta {
    public static String limpiarJSON(String body) throws Exception {
        if (body == null || !body.contains("\"content\":")) {
            throw new Exception("Respuesta inválida del servidor");
        }
        int inicio = body.indexOf("\"content\":") + 10;
        inicio = body.indexOf("\"", inicio) + 1;
        int fin = body.indexOf("\"", inicio);

        String resultado = body.substring(inicio, fin);
        return resultado.replace("\\n", "\n").replace("\\\"", "\"");
    }
}
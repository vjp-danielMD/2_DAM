package main;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import javax.swing.SwingUtilities;

public class VentanaPrincipal extends javax.swing.JFrame {

    public VentanaPrincipal() {
        initComponents();
        this.setTitle("Asistente IA - Nivel Avanzado");
    }

    private String parsearRespuesta(String body) throws Exception {
        if (body == null || !body.contains("\"content\":")) {
            throw new Exception("Respuesta inválida del servidor");
        }
        // Buscamos donde empieza el texto del mensaje
        int inicio = body.indexOf("\"content\":") + 10;
        inicio = body.indexOf("\"", inicio) + 1;
        int fin = body.indexOf("\"", inicio);

        String resultado = body.substring(inicio, fin);

        // Limpiamos los saltos de línea y comillas escapadas que vienen en el JSON
        return resultado.replace("\\n", "\n").replace("\\\"", "\"");
    }

    private String llamarLMStudio(String prompt) throws Exception {
        String nombreModelo = "qwen/qwen3-vl-4b";
        // Escapamos correctamente caracteres para el JSON
        String textoLimpio = prompt.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", " ").trim();
        String jsonBody = "{\"model\": \"" + nombreModelo + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + textoLimpio + "\"}], \"temperature\": 0.7, \"stream\": false}";

        // Usamos una configuración de cliente más estándar para evitar bloqueos
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1) // Forzamos HTTP 1.1 para mayor estabilidad con LM Studio
                .proxy(HttpClient.Builder.NO_PROXY)
                .connectTimeout(java.time.Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:1234/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Connection", "close") // ºevita el ECONNRESET al terminar
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        // El hilo se detendrá aquí hasta que reciba el JSON completo
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return parsearRespuesta(response.body());
    }

    private void leerConFlask(String texto) {
        new Thread(() -> {
            try {
                // Reemplaza espacios por %20 y caracteres especiales
                String encodedTexto = java.net.URLEncoder.encode(texto, "UTF-8");

                java.net.URI uri = java.net.URI.create("http://127.0.0.1:5000/leer?texto=" + encodedTexto);
                java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
                java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                        .uri(uri)
                        .GET()
                        .build();

                client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());

            } catch (Exception e) {
                System.err.println("Error enviando a Flask: " + e.getMessage());
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPregunta = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtRespuesta = new javax.swing.JTextArea();
        btnEjecutar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtPregunta.setColumns(20);
        txtPregunta.setRows(3);
        jScrollPane1.setViewportView(txtPregunta);

        txtRespuesta.setEditable(false);
        txtRespuesta.setColumns(20);
        txtRespuesta.setLineWrap(true);
        txtRespuesta.setRows(5);
        txtRespuesta.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtRespuesta);

        btnEjecutar.setText("PREGUNTAR Y LEER (FLASK)");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        jLabel1.setText("Pregunta:");
        jLabel2.setText("Respuesta IA:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2)
                                        .addComponent(btnEjecutar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {
        String prompt = txtPregunta.getText().trim();
        if (prompt.isEmpty()) {
            return;
        }

        txtRespuesta.setText("Pensando... (espera unos segundos)");
        btnEjecutar.setEnabled(false);

        new Thread(() -> {
            try {
                // 1. Llamada a la IA
                String respuesta = llamarLMStudio(prompt);

                // 2. Actualizar Interfaz
                SwingUtilities.invokeLater(() -> {
                    txtRespuesta.setText(respuesta);
                    txtRespuesta.setCaretPosition(0); // Scroll arriba
                    btnEjecutar.setEnabled(true);
                });

                // 3. Mandar a la voz
                leerConFlask(respuesta);

            } catch (Exception e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    txtRespuesta.setText("Error: " + e.getMessage());
                    btnEjecutar.setEnabled(true);
                });
            }
        }).start();
    }

    public static void main(String args[]) {
        System.setProperty("jdk.httpclient.allowRestrictedHeaders", "connection");

        java.awt.EventQueue.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }

    private javax.swing.JButton btnEjecutar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtPregunta;
    private javax.swing.JTextArea txtRespuesta;
}

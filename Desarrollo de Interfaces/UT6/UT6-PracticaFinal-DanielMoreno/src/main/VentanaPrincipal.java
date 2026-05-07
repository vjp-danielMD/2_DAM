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
    }

    /**
     * Método para enviar el texto a la API de Flask (Python) para que lo lea.
     */
    private void leerConFlask(String texto) {
        if (texto.isEmpty() || texto.equals("Respuesta de la IA...")) return;
        
        new Thread(() -> {
            try {
                String encodedTexto = URLEncoder.encode(texto, StandardCharsets.UTF_8);
                URI uri = URI.create("http://127.0.0.1:5000/leer?texto=" + encodedTexto);
                
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(uri)
                        .GET()
                        .build();

                client.send(request, HttpResponse.BodyHandlers.ofString());
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
        btnEnviarIA = new javax.swing.JButton();
        btnReproducirVoz = new javax.swing.JButton();
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

        btnEnviarIA.setBackground(new java.awt.Color(204, 255, 204));
        btnEnviarIA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEnviarIA.setText("MANDAR");
        btnEnviarIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarIAActionPerformed(evt);
            }
        });

        btnReproducirVoz.setBackground(new java.awt.Color(204, 204, 255));
        btnReproducirVoz.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReproducirVoz.setText("LEER");
        btnReproducirVoz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReproducirVozActionPerformed(evt);
            }
        });

        jLabel1.setText("Escribe tu pregunta:");

        jLabel2.setText("Respuesta de la IA:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnviarIA, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReproducirVoz, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEnviarIA, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnReproducirVoz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void btnEnviarIAActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String prompt = txtPregunta.getText().trim();
        if (prompt.isEmpty()) return;

        txtRespuesta.setText("Pensando...");
        btnEnviarIA.setEnabled(false);

        new Thread(() -> {
            try {
                ChatGPT api = new ChatGPT();
                String respuesta = api.enviarPregunta(prompt);

                SwingUtilities.invokeLater(() -> {
                    txtRespuesta.setText(respuesta);
                    btnEnviarIA.setEnabled(true);
                });
            } catch (Exception e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    txtRespuesta.setText("Error: " + e.getMessage());
                    btnEnviarIA.setEnabled(true);
                });
            }
        }).start();
    }                                           

    private void btnReproducirVozActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        String respuestaActual = txtRespuesta.getText().trim();
        leerConFlask(respuestaActual);
    }                                                

    public static void main(String args[]) {
        System.setProperty("jdk.httpclient.allowRestrictedHeaders", "connection");

        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnEnviarIA;
    private javax.swing.JButton btnReproducirVoz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtPregunta;
    private javax.swing.JTextArea txtRespuesta;
    // End of variables declaration                   
}
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.file.*;

public class ClienteUDP {
    private String ipServidor;
    private int puertoServidor;
    private String nombreArchivo;
    private String frase;

    // constructor de la clase
    public ClienteUDP(String ipServidor, int puertoServidor, String nombreArchivo, String frase) {
        this.ipServidor = ipServidor;
        this.puertoServidor = puertoServidor;
        this.nombreArchivo = nombreArchivo;
        this.frase = frase;
    }

    // funcion para gestionar las condiciones del fichero
    public void gestionarFichero() throws IOException {
        File archivo = new File(this.nombreArchivo);
        List<String> lineas = new ArrayList<>();
        
        // si el fichero existe, se leen sus lineas
        if (archivo.exists()) {
            lineas = Files.readAllLines(archivo.toPath());
        }

        // control de insercion segun las lineas
        if (lineas.size() > 2) {
            lineas.add(2, this.frase);
        } else {
            lineas.add(this.frase);
        }

        // guardar los cambios en el fichero
        Files.write(archivo.toPath(), lineas);
    }

    // funcion para enviar por udp y recibir feedback
    public void enviarAlServidor() {
        try {
            this.gestionarFichero();

            DatagramSocket socket = new DatagramSocket();
            File archivo = new File(this.nombreArchivo);
            byte[] datosArchivo = Files.readAllBytes(archivo.toPath());

            InetAddress dirServidor = InetAddress.getByName(this.ipServidor);
            DatagramPacket paqueteEnvio = new DatagramPacket(datosArchivo, datosArchivo.length, dirServidor, this.puertoServidor);
            
            // enviar fichero al servidor
            socket.send(paqueteEnvio);
            System.out.println("Archivo enviado al servidor.");

            // recibir comunicacion feedback del servidor
            byte[] buffer = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteRecibido);

            System.out.println("Conexión cerrada.");
            socket.close();

        } catch (Exception e) {
            System.out.println("Error de comunicacion: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la IP del Servidor: ");
        String ipServidor = sc.nextLine();
        
        System.out.print("Ingrese el puerto del Servidor: ");
        int puertoServidor = Integer.parseInt(sc.nextLine());
        
        System.out.print("Ingrese el nombre del archivo: ");
        String nombreArchivo = sc.nextLine();
        
        System.out.print("Ingrese la frase a insertar: ");
        String frase = sc.nextLine();

        ClienteUDP cliente = new ClienteUDP(ipServidor, puertoServidor, nombreArchivo, frase);
        cliente.enviarAlServidor();
    }
}
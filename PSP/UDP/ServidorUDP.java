import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.file.*;

public class ServidorUDP {
    private int puertoServidor;

    // constructor del servidor
    public ServidorUDP(int puertoServidor) {
        this.puertoServidor = puertoServidor;
    }

    // funcion para iniciar el servidor udp y gestionar archivo
    public void iniciar() {
        try {
            DatagramSocket socket = new DatagramSocket(this.puertoServidor);
            System.out.println("Servidor UDP iniciado en el puerto " + this.puertoServidor);

            byte[] buffer = new byte[65535];
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
            
            // esperar y recibir paquete del cliente
            socket.receive(paqueteRecibido);
            System.out.println("Archivo recibido desde el cliente.");

            // meter variables de cliente y servidor como pide la rubrica
            int puertoCliente = paqueteRecibido.getPort();
            InetAddress ipCliente = paqueteRecibido.getAddress();

            byte[] datosRecibidos = Arrays.copyOf(paqueteRecibido.getData(), paqueteRecibido.getLength());
            String nombreRecibido = "archivo_recibido.pdf";
            File archivoRecibido = new File(nombreRecibido);
            
            // guardar fichero recibido
            Files.write(archivoRecibido.toPath(), datosRecibidos);
            
            System.out.println("Archivo guardado como: " + nombreRecibido);
            System.out.println("Contenido del archivo:");
            
            // leer y mostrar el fichero
            List<String> lineas = Files.readAllLines(archivoRecibido.toPath());
            for (String linea : lineas) {
                System.out.println(linea);
            }

            // comunicacion feedback para el cliente
            String mensaje = "ok";
            byte[] datosFeedback = mensaje.getBytes();
            DatagramPacket paqueteEnvio = new DatagramPacket(
                datosFeedback, datosFeedback.length, 
                ipCliente, puertoCliente
            );
            socket.send(paqueteEnvio);

            System.out.println("Conexión cerrada.");
            socket.close();

        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el puerto del Servidor: ");
        int puertoServidor = Integer.parseInt(sc.nextLine());

        ServidorUDP servidor = new ServidorUDP(puertoServidor);
        servidor.iniciar();
    }
}
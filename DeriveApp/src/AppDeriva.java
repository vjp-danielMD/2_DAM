
public class AppDeriva {

    public static void main(String[] args) {
        SistemaDeAutenticacion auth = new SistemaDeAutenticacion();
        auth.registrarUsuario("Daniel", "daniel@email.com", "segura123");

        if (auth.autenticar("daniel@email.com", "segura123")) {
            GeneradorDeRutas generador = new GeneradorDeRutas();
            Ruta ruta = generador.generarRutaAleatoria("Plaza Mayor");

            CuadernoDigital cuaderno = new CuadernoDigital();
            cuaderno.registrarExperiencia(new Experiencia(
                    "Sentí calma en este rincón",
                    "imagen.jpg",
                    "audio.mp3",
                    40.0275, -6.0875
            ));

            MapaPsicogeográfico mapa = new MapaPsicogeográfico();
            mapa.agregarEtiqueta("Plaza Mayor", "Calma");

            System.out.println("Deriva completada y registrada.");
        }
    }
}

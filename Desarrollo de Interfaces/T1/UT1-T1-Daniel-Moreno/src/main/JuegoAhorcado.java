package main;

import java.util.*;

public class JuegoAhorcado {

    private final List<Palabra> palabras;
    private final Scanner sc;
    private final String letrasDisponibles = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    public JuegoAhorcado(List<Palabra> palabras, Scanner sc) {
        this.palabras = palabras;
        this.sc = sc;
    }

    public void iniciar() {
        // Selección aleatoria de palabra y pista
        Palabra seleccionada = palabras.get((int) (Math.random() * palabras.size()));
        String palabra = seleccionada.getPalabra().toUpperCase();
        String pista = seleccionada.getPista();

        // Inicializa el progreso con guiones bajos
        char[] progreso = new char[palabra.length()];
        Arrays.fill(progreso, '_');

        int intentos = 0;
        Set<Character> usadas = new HashSet<>();

        // Abecedario
        StringBuilder abecedario = new StringBuilder(letrasDisponibles);

        // Bucle principa
        while (intentos < RenderizadorAhorcado.getMaxFallos() && new String(progreso).contains("_")) {
            mostrarPantalla(intentos, progreso, abecedario, pista);

            // Leer entrada del usuario
            String entrada = sc.nextLine().toUpperCase();

            // Validar que solo se ingrese una letra
            if (entrada.length() != 1) {
                System.out.println("Solo puedes introducir una letra.");
                continue;
            }

            char letra = entrada.charAt(0);

            // Comprobar si la letra ya fue usada
            if (usadas.contains(letra)) {
                System.out.println("Ya usaste esa letra.");
            } else {

                usadas.add(letra);

                // Marcar letra usada en el abecedario con "_"
                int pos = abecedario.indexOf(String.valueOf(letra));
                if (pos != -1) {
                    abecedario.setCharAt(pos, '_');
                }

                // Comprobar si la letra está en la palabra
                if (palabra.indexOf(letra) >= 0) {
                    // reemplaza los "_" por la letra correcta en las posiciones correspondientes
                    for (int i = 0; i < palabra.length(); i++) {
                        if (palabra.charAt(i) == letra) {
                            progreso[i] = letra;
                        }
                    }
                } else {
                    intentos++; // Aumenta fallos si la letra no está
                }
            }
        }
        
        if (new String(progreso).equals(palabra)) {
            mostrarPantalla(intentos, progreso, abecedario, pista);
            System.out.println("\nGanaste! La palabra era: " + palabra);
        } else {
            mostrarPantalla(intentos, progreso, abecedario, pista);
            System.out.println("\nPerdiste... la palabra era: " + palabra);
        }
    }

    private void mostrarPantalla(int intentos, char[] progreso, StringBuilder abecedario, String pista) {
        String dibujo = RenderizadorAhorcado.getEstado(intentos);
        String[] lineasDibujo = dibujo.split("\n");

        // Construye el progreso con espacios
        StringBuilder progresoFormateado = new StringBuilder();
        for (char c : progreso) { // Recorre cada posición de la palabra
            progresoFormateado.append(c).append(' ');
        }

        // Panel derecho 
        String[] panelDerecho = {
            abecedario.toString(),
            progresoFormateado.toString(),
            "\"" + pista + "\""
        };

        System.out.println("\n\n");

        int maxLineas = Math.max(lineasDibujo.length, panelDerecho.length);

        // Bucle que imprime lado izquierdo (ahorcado) + lado derecho (panel)
        for (int i = 0; i < maxLineas; i++) {
            String parteIzquierda;
            if (i < lineasDibujo.length) {
                parteIzquierda = lineasDibujo[i];
            } else {
                parteIzquierda = "        ";
            }

            String parteDerecha;
            if (i < panelDerecho.length) {
                parteDerecha = "   " + panelDerecho[i];
            } else {
                parteDerecha = "";
            }

            System.out.println(parteIzquierda + parteDerecha);
        }

        System.out.print("introduzca una letra: ");
    }

}

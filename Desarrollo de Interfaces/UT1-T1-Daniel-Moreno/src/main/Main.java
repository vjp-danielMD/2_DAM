package main;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Palabra> palabras = GestorPalabras.cargarDatos();
        JuegoAhorcado juego = new JuegoAhorcado(palabras, new Scanner(System.in));
        juego.iniciar();
    }
}

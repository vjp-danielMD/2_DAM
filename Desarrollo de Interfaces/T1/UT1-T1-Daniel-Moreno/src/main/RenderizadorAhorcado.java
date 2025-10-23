package main;

public class RenderizadorAhorcado {

    private static final String[] AHORCADO = {
        """
         +---+
         |   |
             |
             |
             |
             |
        =========
        """,
        """
         +---+
         |   |
         O   |
             |
             |
             |
        =========
        """,
        """
         +---+
         |   |
         O   |
         |   |
             |
             |
        =========
        """,
        """
         +---+
         |   |
         O   |
        /|   |
             |
             |
        =========
        """,
        """
         +---+
         |   |
         O   |
        /|\\  |
             |
             |
        =========
        """,
        """
         +---+
         |   |
         O   |
        /|\\  |
        /    |
             |
        =========
        """,
        """
         +---+
         |   |
         O   |
        /|\\  |
        / \\  |
             |
        =========
        """
    };

    public static String getEstado(int intentos) {
        return AHORCADO[intentos];
    }

    /** Devuelve el número máximo de fallos permitidos */
    public static int getMaxFallos() {
        return AHORCADO.length - 1;
    }
}

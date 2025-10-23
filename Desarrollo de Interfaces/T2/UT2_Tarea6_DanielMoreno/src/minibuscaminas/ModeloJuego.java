package minibuscaminas;

import java.util.Random;
/**
 * 
 * @author daniel
 */
public class ModeloJuego {
    public final int tamano;
    public final int minas;
    private final boolean[][] campoMinas;
    private final boolean[][] revelado;
    public int vidas;
    public int contadorSegurosRevelados;

    public ModeloJuego(int tamano, int minas, int vidasIniciales) {
        this.tamano = tamano;
        this.minas = minas;
        this.campoMinas = new boolean[tamano][tamano];
        this.revelado = new boolean[tamano][tamano];
        this.vidas = vidasIniciales;
        this.contadorSegurosRevelados = 0;
        colocarMinas();
    }

    private void colocarMinas() {
        Random rnd = new Random();
        int colocadas = 0;
        while (colocadas < minas) {
            int r = rnd.nextInt(tamano);
            int c = rnd.nextInt(tamano);
            if (!campoMinas[r][c]) {
                campoMinas[r][c] = true;
                colocadas++;
            }
        }
    }

    public boolean esMina(int fila, int col) {
        return campoMinas[fila][col];
    }

    public boolean estaRevelado(int fila, int col) {
        return revelado[fila][col];
    }

    public ResultadoRevelar revelar(int fila, int col) {
        if (revelado[fila][col]) return ResultadoRevelar.YA_REVELADO;
        revelado[fila][col] = true;
        if (campoMinas[fila][col]) {
            vidas--;
            if (vidas <= 0) return ResultadoRevelar.MINA_FIN;
            return ResultadoRevelar.MINA_GOLPEADA;
        } else {
            contadorSegurosRevelados++;
            if (contadorSegurosRevelados == tamano * tamano - minas) return ResultadoRevelar.TODAS_SEGURAS;
            return ResultadoRevelar.SEGURO;
        }
    }

    public void revelarTodo() {
        for (int i = 0; i < tamano; i++) for (int j = 0; j < tamano; j++) revelado[i][j] = true;
    }

    public enum ResultadoRevelar {
        YA_REVELADO,
        SEGURO,
        MINA_GOLPEADA,
        MINA_FIN,
        TODAS_SEGURAS
    }
}

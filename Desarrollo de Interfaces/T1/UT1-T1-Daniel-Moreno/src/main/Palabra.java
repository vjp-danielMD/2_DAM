package main;

/**
 *
 * @author daniel
 */
public class Palabra {

    private String palabra;
    private String pista;

    public Palabra(String palabra, String pista) {
        this.palabra = palabra;
        this.pista = pista;
    }

    public Palabra() {
        this.palabra = "";
        this.pista = "";
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getPista() {
        return pista;
    }

    public void setPista(String pista) {
        this.pista = pista;
    }

    @Override
    public String toString() {
        return "Palabra{" + "palabra=" + palabra + ", pista=" + pista + '}';
    }

}

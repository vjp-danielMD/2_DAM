package prueba1;

/**
 *
 * @author daniel
 */
public class Palabra {

    private String palabra1;
    private String frase;
    private String palabra2;

    public Palabra(String palabra1, String frase, String palabra2) {
        this.palabra1 = palabra1;
        this.frase = frase;
        this.palabra2 = palabra2;
    }

    public String getPalabra1() {
        return palabra1;
    }

    public void setPalabra1(String palabra1) {
        this.palabra1 = palabra1;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getPalabra2() {
        return palabra2;
    }

    public void setPalabra2(String palabra2) {
        this.palabra2 = palabra2;
    }

    @Override
    public String toString() {
        return palabra1 + "\n" + frase + "\n" + palabra2 + "\n";
    }

}

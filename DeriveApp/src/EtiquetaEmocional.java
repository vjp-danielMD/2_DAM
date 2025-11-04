// Clase que representa una etiqueta emocional asociada a un lugar

public class EtiquetaEmocional {

    private String lugar;
    private String emocion;

    public EtiquetaEmocional(String lugar, String emocion) {
        this.lugar = lugar;
        this.emocion = emocion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getEmocion() {
        return emocion;
    }

    public void setEmocion(String emocion) {
        this.emocion = emocion;
    }

    
}

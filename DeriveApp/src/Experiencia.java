// Clase que representa una experiencia registrada por el usuario

public class Experiencia {

    private String texto;
    private String imagenPath;
    private String audioPath;
    private double latitud;
    private double longitud;

    public Experiencia(String texto, String imagenPath, String audioPath, double latitud, double longitud) {
        this.texto = texto;
        this.imagenPath = imagenPath;
        this.audioPath = audioPath;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

}

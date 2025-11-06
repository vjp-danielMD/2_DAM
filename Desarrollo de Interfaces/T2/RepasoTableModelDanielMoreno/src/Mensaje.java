/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author danie
 */
public class Mensaje {

    private String emisor;
    private String destinatario;
    private String asunto;
    private String mensaje;

    public Mensaje(String emisor, String destinatario, String asunto, String mensaje) {
        this.emisor = emisor;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String[] toArrayString() {
        String[] s = new String[4]; 
        s[0] = this.emisor;
        s[1] = this.destinatario;
        s[2] = this.asunto;
        s[3] = this.mensaje;
        return s;
    }
}

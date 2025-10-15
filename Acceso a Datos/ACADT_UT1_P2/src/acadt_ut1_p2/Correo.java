package acadt_ut1_p2;

/**
 *
 * @author daniel
 */
public class Correo {

    private String destinatario;
    private String asunto;
    private String cuerpo;

    public Correo(String destinatario, String asunto, String cuerpo) {
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }

    public Correo() {
        this.destinatario = "";
        this.asunto = "";
        this.cuerpo = "";
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

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    @Override
    public String toString() {
        return "Correo{" + "destinatario=" + destinatario + ", asunto=" + asunto + ", cuerpo=" + cuerpo + '}';
    }
}

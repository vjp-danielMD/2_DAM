
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author danie
 */
public class LogicaNegocio {

    private final List<Mensaje> listMensajes;

    public LogicaNegocio() {
        this.listMensajes = new ArrayList<>(Arrays.asList(
                new Mensaje("Ana", "Luis", "Reunión", "Confirmamos la reunión a las 10."),
                new Mensaje("Carlos", "Marta", "Informe", "El informe estará listo mañana."),
                new Mensaje("Elena", "Pedro", "Viaje", "¿Has reservado los billetes?"),
                new Mensaje("Lucía", "Jorge", "Cena", "¿Te apetece cenar esta noche?"),
                new Mensaje("Mario", "Clara", "Proyecto", "Necesito tu parte del proyecto."),
                new Mensaje("Sofía", "Raúl", "Entrega", "La entrega se ha pospuesto."),
                new Mensaje("Andrés", "Nuria", "Pago", "¿Has recibido el pago?"),
                new Mensaje("Teresa", "Iván", "Evento", "El evento empieza a las 18h."),
                new Mensaje("Diego", "Sara", "Examen", "¿Estudiaste para el examen?"),
                new Mensaje("Laura", "Tomás", "Vacaciones", "¿Cuándo te vas de vacaciones?"),
                new Mensaje("Pablo", "Rosa", "Revisión", "Revisé tu documento."),
                new Mensaje("Carmen", "Alberto", "Consulta", "Tengo una duda sobre el tema."),
                new Mensaje("Javier", "Beatriz", "Reclamo", "Envié el reclamo ayer."),
                new Mensaje("Natalia", "Hugo", "Reunión", "¿Puedes adelantar la reunión?"),
                new Mensaje("Miguel", "Patricia", "Actualización", "Actualicé los datos del sistema.")
        ));
    }

    public List<Mensaje> getListMensajes() {
        return listMensajes;
    }
    
    

}

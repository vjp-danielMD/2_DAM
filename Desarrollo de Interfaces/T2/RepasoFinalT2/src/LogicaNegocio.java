
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author daniel
 */
public class LogicaNegocio {

    private final List<Evento> eventoList;

    public LogicaNegocio() {
        this.eventoList = new ArrayList<>(Arrays.asList(
                new Evento("Concierto de Jazz", "2025-11-12", "Auditorio Municipal"),
                new Evento("Exposición de Fotografía", "2025-12-03", "Centro Cultural San Marcos"),
                new Evento("Feria de Artesanía", "2026-01-15", "Plaza Mayor"),
                new Evento("Torneo de Ajedrez", "2025-11-20", "Casa de la Juventud"),
                new Evento("Festival de Cine Independiente", "2026-02-10", "Teatro Principal"),
                new Evento("Carrera Popular", "2025-12-18", "Parque de los Pinos"),
                new Evento("Mercado Medieval", "2026-03-22", "Casco Antiguo"),
                new Evento("Charla sobre Energías Renovables", "2025-11-25", "Sala Polivalente del Ayuntamiento"),
                new Evento("Taller de Pintura al Óleo", "2026-01-08", "Escuela de Arte y Diseño"),
                new Evento("Concierto de Rock Alternativo", "2025-12-28", "Sala Underground"),
                new Evento("Maratón de Lectura", "2025-11-15", "Biblioteca Municipal"),
                new Evento("Feria del Libro", "2026-04-23", "Plaza de España"),
                new Evento("Exhibición de Drones", "2025-12-01", "Polideportivo Norte"),
                new Evento("Taller de Cocina Mediterránea", "2026-01-20", "Centro Gastronómico"),
                new Evento("Concierto Sinfónico", "2025-11-30", "Gran Teatro"),
                new Evento("Exposición de Escultura", "2026-02-15", "Museo de Arte Contemporáneo"),
                new Evento("Torneo de Fútbol 7", "2025-12-10", "Campo Municipal"),
                new Evento("Festival de Música Electrónica", "2026-03-05", "Recinto Ferial"),
                new Evento("Clase Magistral de Fotografía", "2025-11-28", "Centro Cultural"),
                new Evento("Mercadillo Navideño", "2025-12-15", "Plaza del Ayuntamiento"),
                new Evento("Carrera Solidaria 10K", "2026-01-25", "Paseo Marítimo"),
                new Evento("Taller de Cerámica", "2025-12-05", "Escuela de Oficios"),
                new Evento("Concierto de Flamenco", "2026-02-20", "Tablao El Duende"),
                new Evento("Exposición de Pintura Moderna", "2025-11-22", "Galería de Arte"),
                new Evento("Torneo de Pádel", "2025-12-12", "Club Deportivo"),
                new Evento("Festival de Teatro Callejero", "2026-03-15", "Centro Histórico"),
                new Evento("Taller de Escritura Creativa", "2026-01-10", "Casa de la Cultura"),
                new Evento("Feria de Vehículos Clásicos", "2025-12-20", "Explanada del Puerto"),
                new Evento("Concierto de Música Clásica", "2026-02-28", "Auditorio Principal"),
                new Evento("Exposición de Arte Urbano", "2025-11-25", "Nave Industrial"),
                new Evento("Torneo de Baloncesto 3x3", "2025-12-08", "Pabellón Deportivo"),
                new Evento("Festival de Cortos", "2026-01-30", "Cine Multicines"),
                new Evento("Taller de Yoga en el Parque", "2025-11-18", "Parque Central"),
                new Evento("Mercado de Productos Ecológicos", "2025-12-22", "Plaza Verde"),
                new Evento("Concierto de Gospel", "2026-03-12", "Iglesia Santa María"),
                new Evento("Exposición de Modelismo", "2026-01-15", "Centro de Convenciones"),
                new Evento("Torneo de Videojuegos", "2025-12-27", "Centro Juvenil"),
                new Evento("Festival Gastronómico", "2026-02-05", "Recinto Ferial"),
                new Evento("Taller de Fotografía Nocturna", "2025-11-20", "Mirador del Castillo"),
                new Evento("Carrera de Orientación", "2025-12-14", "Monte del Parque Natural"),
                new Evento("Concierto de Música Latina", "2026-03-20", "Sala de Conciertos"),
                new Evento("Exposición de Cómics", "2026-01-22", "Biblioteca Central"),
                new Evento("Torneo de Tenis", "2025-11-26", "Club de Tenis"),
                new Evento("Festival de Magia", "2025-12-30", "Teatro Municipal"),
                new Evento("Taller de Jardinería Urbana", "2026-02-12", "Vivero Municipal"),
                new Evento("Feria de Ciencia y Tecnología", "2026-03-25", "Palacio de Congresos"),
                new Evento("Concierto Acústico", "2025-12-02", "Café Cultural"),
                new Evento("Exposición de Fotografía de Naturaleza", "2026-01-28", "Centro de Interpretación"),
                new Evento("Torneo de Voleibol Playa", "2025-11-29", "Playa Municipal"),
                new Evento("Festival de Danza Contemporánea", "2026-02-18", "Teatro de la Danza")
        ));
    }

    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void anadirEvento(Evento e) {
        eventoList.add(e);
    }

    public void eliminarEvento(int index) {
        eventoList.remove(index);
    }

    public void actualizarEvento(int index, Evento nuevoEvento) {
        if (index >= 0 && index < eventoList.size()) {
            eventoList.set(index, nuevoEvento);
        }
    }
}

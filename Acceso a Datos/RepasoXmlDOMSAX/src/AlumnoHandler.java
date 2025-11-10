
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author danie
 */
public class AlumnoHandler extends DefaultHandler {

    private List<Alumno> listaAlumnos = new ArrayList<>();
    private Alumno alumnoActual;
    private List<String> asignaturasActuales;
    private StringBuilder contenido = new StringBuilder();

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contenido.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.toLowerCase()) {
            case "nombre":
                alumnoActual = new Alumno(contenido.toString().trim(), new ArrayList<>());
                break;
            case "asignatura":
                asignaturasActuales.add(contenido.toString().trim());
                break;
            case "asignaturas":
                alumnoActual = new Alumno(alumnoActual.getNombre(), asignaturasActuales);
                break;
            case "alumno":
                listaAlumnos.add(alumnoActual);
            break;
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("alumno")) {
            alumnoActual = new Alumno("", new ArrayList<>());
            asignaturasActuales = new ArrayList<>();
        }
        contenido.setLength(0);
    }

}

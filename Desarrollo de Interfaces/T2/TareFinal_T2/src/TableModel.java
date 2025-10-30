
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author alumno
 */

public class TableModel extends AbstractTableModel {

    private final List<Pelicula> peliculas;
    private final String[] columnNames = {"Título", "Año", "Duración (min)"};

    public TableModel(List<Pelicula> peliculas) {
        this.peliculas = new ArrayList<>(peliculas);
    }

    @Override
    public int getRowCount() {
        return peliculas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pelicula pelicula = peliculas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return pelicula.getTitulo();
            case 1:
                return pelicula.getAno();
            case 2:
                return pelicula.getDuracion();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}

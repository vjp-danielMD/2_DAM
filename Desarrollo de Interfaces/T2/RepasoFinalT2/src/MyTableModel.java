
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danieL
 */
public class MyTableModel extends AbstractTableModel {

    private final List<Evento> eventoList;
    private final String[] columnNames = {"Nombre", "Fecha", "Ubicación"};

    public MyTableModel(List<Evento> eventoList) {
        this.eventoList = new ArrayList<>(eventoList);
    }

    @Override
    public int getRowCount() {
        return eventoList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Evento evento = eventoList.get(rowIndex);
        String[] datos = evento.toArrayString();

        if (columnIndex >= 0 && columnIndex < datos.length) {
            return datos[columnIndex];
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

}

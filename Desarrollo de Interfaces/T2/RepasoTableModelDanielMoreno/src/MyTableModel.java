
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author danie
 */
public class MyTableModel extends AbstractTableModel {

    private final List<Mensaje> listMensajes;
    private final String[] columnNames = {"Emisor", "Destinatario", "Asunto", "Mensaje"};

    public MyTableModel(List<Mensaje> listMensajes) {
        this.listMensajes = new ArrayList<>(listMensajes);
    }

    
    @Override
    public int getRowCount() {
        return listMensajes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mensaje mensaje = listMensajes.get(rowIndex);
        String[] datos = mensaje.toArrayString();
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

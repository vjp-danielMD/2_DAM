
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author alumno
 */
public class MyTableModel extends AbstractTableModel {

    private final List<Articulo> listaArticulos;
    private final String[] columnNames = {"ID", "Articulo", "Descripcion", "Precio"};

    public MyTableModel(List<Articulo> listaArticulos) {
        this.listaArticulos = new ArrayList<>(listaArticulos);
    }

    public List<Articulo> getListaArticulos() {
        return listaArticulos;
    }

    
    @Override
    public int getRowCount() {
        return listaArticulos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Articulo evento = listaArticulos.get(rowIndex);
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

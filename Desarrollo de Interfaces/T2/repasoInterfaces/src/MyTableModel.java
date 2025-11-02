
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author daniel
 */
public class MyTableModel extends AbstractTableModel {

    private final List<Raton> ratones;
    private final String[] columnNames = {"marca", "modelo", "frecuencia"};

    public MyTableModel(List<Raton> ratones) {
        this.ratones = new ArrayList<>(ratones);
    }

    @Override
    public int getRowCount() {
        return ratones.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Raton raton = ratones.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return raton.getMarca();
            case 1: 
                return raton.getModelo();
            case 2: 
                return raton.getFrecuencia();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    
}

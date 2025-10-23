/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumno
 */
public class TableModelAlumnos extends AbstractTableModel {

    private List<Alumno> listAlumno;
    private String[] columnas = {"Nombre", "Apellido", "Curso"};

    public TableModelAlumnos(List<Alumno> listAlumno) {
        this.listAlumno = listAlumno;
    }

    @Override
    public int getRowCount() {
        return listAlumno.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listAlumno.get(rowIndex).getNombre();
            case 1:
                return listAlumno.get(rowIndex).getApellido();
            case 2:
                return listAlumno.get(rowIndex).getCurso();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

}

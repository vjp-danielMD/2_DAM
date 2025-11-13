/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.*;

/**
 *
 * @author alumno
 */
public class Ejercicio1 {

    public static void main(String[] args) {
        try {
            //CARGAR EL DRIVER
            Class.forName("com.mysql.cj.jdbc.Driver");

            //ESTABLECEMOS CONEXION
            Connection conexion = Conexion.getConexion();
//            crearProcedimientoSalario(conexion);
//            subidaSalarioEmpleados(10, 100, conexion);
            crearProcedimientoDepartamentos(conexion);
            getDepartamentos(conexion);
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void subidaSalarioEmpleados(int dep, double subida, Connection con) {
        try {
            String sql = "{call subida_sal(?, ?) }";

            //preparamos la llamada
            CallableStatement cs = con.prepareCall(sql);

            //damos valor a los argumentos
            cs.setInt(1, dep); //primer argumento-dep
            cs.setDouble(2, subida); //segundo argumento-subida
            cs.executeUpdate(); //ejecutar el procedimiento

            System.out.println("Subida realizada.");
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void crearProcedimientoSalario(Connection conexion) {
        try {

            Statement statement = conexion.createStatement();
            String queryDrop = "DROP PROCEDURE IF EXISTS subida_sal;";

            String queryCreate
                    = "CREATE PROCEDURE subida_sal (IN d INT, IN subida INT) "
                    + "BEGIN "
                    + "UPDATE empleados SET salario = salario + subida WHERE dept_no = d; "
                    + "END";

            System.out.println(queryCreate);

            // borrar si existe
            statement.execute(queryDrop);

            //luego crear
            statement.execute(queryCreate);

            System.out.println("Procedimiento creado");

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * procedimiento para mostrar datos
     */
    public static void crearProcedimientoDepartamentos(Connection con) {
        try {
            Statement statement = con.createStatement();

            String queryDrop = "DROP PROCEDURE IF EXISTS get_departamentos;";
            String queryCreate
                    = "CREATE PROCEDURE get_departamentos() "
                    + "BEGIN "
                    + "SELECT * FROM departamentos; "
                    + "END";

            statement.execute(queryDrop);
            statement.execute(queryCreate);

            System.out.println("Procedimiento creado.");

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getDepartamentos(Connection con) {
        try {
            String sql = "{call get_departamentos() }";

            CallableStatement cs = con.prepareCall(sql);

            ResultSet rs = cs.executeQuery(); //ejecutar el procedimiento

            while (rs.next()) {
                System.out.println("Código: " + rs.getString(1));
                System.out.println("Departamento: " + rs.getString(2));
            }
            cs.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void crearProcedimientoLocalidad(Connection con) {

        try {
            Statement statement = con.createStatement();
            String queryDrop = "DROP PROCEDURE IF EXISTS localidad_depart;";
            String queryCreate = "CREATE PROCEDURE localidad_depart (IN d INT, OUT localidad VARCHAR(15)) "
                    + "BEGIN "
                    + "SELECT loc INTO localidad FROM departamentos WHERE dept_no = d; "
                    + "END";
            System.out.println(queryCreate);
            statement.execute(queryDrop);
            statement.execute(queryCreate);
            System.out.println("Procedimiento creado.");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void obtenerLocalidad(int dep, Connection con) {
        try {

            String sql = "{call localidad_depart(?)}";

            // Preparamos la llamada
            CallableStatement cs = con.prepareCall(sql);

            // Damos valor a los argumentos
            cs.setInt(1, dep); // primer argumento-dep

            cs.registerOutParameter(2, Types.VARCHAR); // devuelve la localidad

            // Ejecutar el procedimiento
            cs.execute();
            System.out.println("Localidad: " + cs.getString(2));

            // Cerrar CallableStatement
            cs.close(); // Cerrar conexión
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}

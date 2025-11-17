package main;

import java.sql.*;
import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {
        try {
            Connection conexion = Conexion.getConexion();
            Scanner sc = new Scanner(System.in);

            // Crear procedimientos desde código
            crearProcedimientoEmpleadosPorDepartamento(conexion);
            crearProcedimientoAltaEmpleado(conexion);
            crearProcedimientoEmpleadosAntiguos(conexion);

            // a) Mostrar empleados por departamento
            System.out.print("Introduce el nombre del departamento: ");
            String dept = sc.nextLine();
            mostrarEmpleadosPorDepartamento(dept, conexion);

            // b) Alta de empleado
            System.out.println("Dar de alta un empleado:");
            System.out.print("Número: "); 
            int empNo = sc.nextInt();
            sc.nextLine(); // limpiar buffer
            System.out.print("Apellido: "); 
            String apellido = sc.nextLine();
            System.out.print("Oficio: "); 
            String oficio = sc.nextLine();
            System.out.print("Salario: "); 
            double salario = sc.nextDouble();
            System.out.print("Comisión: "); 
            double comision = sc.nextDouble();
            System.out.print("Departamento: "); 
            int deptNo = sc.nextInt();

            altaEmpleado(empNo, apellido, oficio, salario, comision, deptNo, conexion);

            // c) Mostrar empleados con antigüedad > 2 años
            mostrarEmpleadosAntiguos(conexion);

            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Procedimiento empleados por departamento
    public static void crearProcedimientoEmpleadosPorDepartamento(Connection con) {
        try {
            Statement st = con.createStatement();
            st.execute("DROP PROCEDURE IF EXISTS empleados_por_departamento");
            String sql = "CREATE PROCEDURE empleados_por_departamento(IN nombreDept VARCHAR(15)) "
                       + "BEGIN "
                       + "SELECT e.emp_no, e.apellido, e.oficio, e.salario, d.dnombre "
                       + "FROM empleados e INNER JOIN departamentos d ON e.dept_no = d.dept_no "
                       + "WHERE d.dnombre = nombreDept; "
                       + "END";
            st.execute(sql);
            System.out.println("Procedimiento empleados_por_departamento creado.");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // b) Procedimiento alta empleado
    public static void crearProcedimientoAltaEmpleado(Connection con) {
        try {
            Statement st = con.createStatement();
            st.execute("DROP PROCEDURE IF EXISTS alta_empleado");
            String sql = "CREATE PROCEDURE alta_empleado("
                       + "IN p_emp_no INT, IN p_apellido VARCHAR(20), IN p_oficio VARCHAR(50), "
                       + "IN p_fecha DATE, IN p_salario FLOAT(6,2), IN p_comision FLOAT(6,2), IN p_dept_no INT) "
                       + "BEGIN "
                       + "IF EXISTS (SELECT 1 FROM departamentos WHERE dept_no = p_dept_no) THEN "
                       + "INSERT INTO empleados(emp_no, apellido, oficio, fecha_alt, salario, comision, dept_no) "
                       + "VALUES(p_emp_no, p_apellido, p_oficio, p_fecha, p_salario, p_comision, p_dept_no); "
                       + "ELSE "
                       + "SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El departamento no existe'; "
                       + "END IF; "
                       + "END";
            st.execute(sql);
            System.out.println("Procedimiento alta_empleado creado.");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // c) Procedimiento empleados antiguos
    public static void crearProcedimientoEmpleadosAntiguos(Connection con) {
        try {
            Statement st = con.createStatement();
            st.execute("DROP PROCEDURE IF EXISTS empleados_antiguos");
            String sql = "CREATE PROCEDURE empleados_antiguos() "
                       + "BEGIN "
                       + "SELECT emp_no, apellido, oficio, fecha_alt, salario "
                       + "FROM empleados "
                       + "WHERE TIMESTAMPDIFF(YEAR, fecha_alt, CURDATE()) > 2; "
                       + "END";
            st.execute(sql);
            System.out.println("Procedimiento empleados_antiguos creado.");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- INVOCACIÓN DE PROCEDIMIENTOS ----------------

    public static void mostrarEmpleadosPorDepartamento(String dept, Connection con) {
        try {
            String sql = "{call empleados_por_departamento(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, dept);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("emp_no") + " - " + rs.getString("apellido") + " - " + rs.getString("oficio"));
            }
            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void altaEmpleado(int empNo, String apellido, String oficio, double salario, double comision, int deptNo, Connection con) {
        try {
            String sql = "{call alta_empleado(?, ?, ?, CURDATE(), ?, ?, ?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, empNo);
            cs.setString(2, apellido);
            cs.setString(3, oficio);
            cs.setDouble(4, salario);
            cs.setDouble(5, comision);
            cs.setInt(6, deptNo);

            cs.executeUpdate();
            System.out.println("Empleado insertado correctamente.");
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void mostrarEmpleadosAntiguos(Connection con) {
        try {
            String sql = "{call empleados_antiguos()}";
            CallableStatement cs = con.prepareCall(sql);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("emp_no") + " - " + rs.getString("apellido") + " - Fecha alta: " + rs.getDate("fecha_alt"));
            }
            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

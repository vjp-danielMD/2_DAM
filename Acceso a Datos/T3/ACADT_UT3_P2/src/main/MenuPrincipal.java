package main;

import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("""
                               1. Gestión empleados
                               2. Gestión departamentos
                               0. Salir
                               Seleccione una opción:""");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 ->
                    menuEmpleados();
                case 2 ->
                    menuDepartamentos();
                case 0 ->
                    System.out.println("Saliendo...");
                default ->
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuEmpleados() {
        Scanner sc = new Scanner(System.in);
        int op;
        do {
            System.out.println("""
                               --- GESTIÓN EMPLEADOS ---
                               1. Insertar empleado
                               2. Modificar empleado
                               3. Eliminar empleado
                               4. Listar todos los empleados
                               5. Listar empleados por departamento
                               6. Subir sueldo a empleados de un departamento
                               0. Volver""");
            op = sc.nextInt();
            switch (op) {
                case 1 ->
                    EmpleadoDAO.insertarEmpleado();
                case 2 ->
                    EmpleadoDAO.modificarEmpleado();
                case 3 ->
                    EmpleadoDAO.eliminarEmpleado();
                case 4 ->
                    EmpleadoDAO.listarEmpleados();
                case 5 ->
                    EmpleadoDAO.listarPorDepartamento();
                case 6 ->
                    EmpleadoDAO.subirSueldoDepartamento();
            }
        } while (op != 0);
    }

    private static void menuDepartamentos() {
        Scanner sc = new Scanner(System.in);
        int op;
        do {
            System.out.println("""
                               --- GESTIÓN DEPARTAMENTOS ---
                               1. Insertar departamento
                               2. Modificar departamento
                               3. Eliminar departamento
                               4. Listar todos los departamentos
                               0. Volver""");
            op = sc.nextInt();
            switch (op) {
                case 1 ->
                    DepartamentoDAO.insertarDepartamento();
                case 2 ->
                    DepartamentoDAO.modificarDepartamento();
                case 3 ->
                    DepartamentoDAO.eliminarDepartamento();
                case 4 ->
                    DepartamentoDAO.listarDepartamentos();
            }
        } while (op != 0);
    }
}

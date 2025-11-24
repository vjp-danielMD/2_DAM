/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Conexion.Conexion;
import java.sql.*;
import DAO.*;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Main {

    public static void main(String[] args) {
        try {
            Connection con = Conexion.getConnection();
            System.out.println("Conexion establecida con Taller!");
            System.out.println("");
            menu(con);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void menu(Connection con){
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        do {            
            System.out.println("""
                               1. crear tabla Pieza
                               2. insertar pieza
                               3. crear procedimiento actualziar_stock
                               4. llamar actualizar_stock
                               5. mostrar tabla pieza
                               0. salir
                               seleccione una opcion:
                               """);
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    PiezaSQL.crearTablaPieza(con);
                    break;
                case 2:
                    PiezaSQL.insertarPieza(con);
                    break;
                case 3:
                    PiezaSQL.crearProcedimientoActualizarStock(con);
                    break;
                case 4:
                    PiezaSQL.llamarActualizarStock(con, PiezaSQL.pedirId(), PiezaSQL.pedirNewCantidad());
                    break;
                case 5: 
                    PiezaSQL.mostrarPiezas(con);
                    break;
                case 6:
                    CategoriaSQL.crearTablaCategoria(con);
                    break;
                default:
                    System.out.println("Escoja una opcion correcta!");
            }
        } while (opcion != 0);
    }
}

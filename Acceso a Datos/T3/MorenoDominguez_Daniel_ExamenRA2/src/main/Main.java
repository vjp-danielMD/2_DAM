/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.*;
import Conexion.Conexion;
import DAO.*;
import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class Main {

    public static void main(String[] args) {
        Connection con = Conexion.getConnection();
        System.out.println("Conexion establecida.");
        menu(con);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void menu(Connection con){
        int opcion;
        Scanner sc = new Scanner(System.in);
        do {            
            mostrarMenu();
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    MultaSQL.mostrarMultas(con);
                    break;
                case 2:
                    MultaSQL.insertarMulta(con);
                    break;
                case 3:
                    MultaSQL.CrearProcedimientoBorradoDeMulta(con);
                    MultaSQL.callBorradoMulta(con);
                    break;
                case 4:
                    MultaSQL.crearPorcedimientoGetEstadisticas(con);
                    MultaSQL.llamarProcedimientoGetEstadisticas(con);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Elija una opcion correcta.");
            }
        } while (opcion != 5);
    }
    
    public static void mostrarMenu(){
        System.out.println("""
                           1.- MOSTRAR TODAS LAS MULTAS.
                           2.- INSERTAR MULTA
                           3.- BORRAR MULTA
                           4.- ESTADISTICAS
                           5.- SALIR
                           OPCION:
                           """);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package orquideas;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connection conexion = Conexion.getConnection();
            System.out.println("Conexion establecida con la BBDD!");
            Scanner sc = new Scanner(System.in);
            menu(sc, conexion);
            conexion.close();
        } catch (Exception e) {
        }
    }

    public static void menu(Scanner sc, Connection conexion) {
        try {
            int opcion = 0;
            do {
                System.out.println("""
                                   == MENÚ ==
                                   1. crear tabla sustrato
                                   2. crear tabla orquidea
                                   3. insertar sustrato
                                   4. insertar orquidea
                                   5. crear procedimiento
                                   6. ver orquideas
                                   7. ver sustratos
                                   8. ver orquideas con sustratos
                                   9. buscar orquideas por sustrato
                                   0. salir
                                   Elija una opcion:
                                   """);
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        Sustrato.crearTablaSustrato(conexion);
                        break;
                    case 2:
                        Orquidea.crearTablaOrquidea(conexion);
                        break;
                    case 3:
                        Sustrato.insertarSustrato(conexion);
                        break;
                    case 4:
                        Orquidea.insertarOrquidea(conexion);
                        break;
                    case 5:
                        Orquidea.crearProcedimientoBuscarPorSustrato(conexion);
                        break;
                    case 6:
                        Orquidea.mostrarOrquideas(conexion);
                        break;
                    case 7:
                        Sustrato.mostrarSustratos(conexion);
                        break;
                    case 8:
                        Orquidea.mostrarOrquideasConSustratos(conexion);
                        break;
                    case 9:
                        Scanner id = new Scanner(System.in);
                        int idSustrato = 0;
                        System.out.println("inideque la ID del sustrato para filtrar las flores: ");
                        idSustrato = id.nextInt();
                        Orquidea.llamarProcedimientoBuscarPorSustrato(conexion, idSustrato);
                        break;
                    case 0:
                        System.out.println("saliendo...");
                        break;
                    default:
                        System.out.println("elige una opcion correcta.");
                }
            } while (opcion != 0);
        } catch (Exception e) {
        }
    }

}

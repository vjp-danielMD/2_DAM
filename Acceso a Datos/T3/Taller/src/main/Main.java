package main;

import Conexion.Conexion;
import java.sql.*;
import DAO.*;
import java.util.Scanner;

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
                               3. crear procedimiento actualizar_stock
                               4. llamar actualizar_stock
                               5. mostrar tabla pieza
                               ---
                               6. crear tabla categoria
                               7. insertar categoria
                               8. mostrar categoria
                               9. crear procedimiento actualizar_descripcion_categoria
                               10. actualizar descripcion de una categoria
                               ---
                               11. crear tabla material
                               12. insertar material
                               13. mostrar materiales
                               14. crear procedimiento actualizar_descripcion_material
                               15. actualizar descripcion de un material
                               --
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
                    PiezaSQL.llamarActualizarStock(
                            con, PiezaSQL.pedirId(),
                            PiezaSQL.pedirNewCantidad());
                    break;
                case 5: 
                    PiezaSQL.mostrarPiezas(con);
                    break;
                case 6:
                    CategoriaSQL.crearTablaCategoria(con);
                    break;
                case 7:
                    CategoriaSQL.insertarCategoria(con);
                    break;
                case 8:
                    CategoriaSQL.mostrarCategorias(con);
                    break;
                case 9:
                    CategoriaSQL.crearProcedimientoActualizarDescripcionCategoria(con);
                    break;
                case 10:
                    CategoriaSQL.llamarActualizarDescripcionCategoria(
                            con, CategoriaSQL.pedirIdCategoria(),
                            CategoriaSQL.pedirNuevaDescripcion());
                    break;
                case 11:
                    MaterialSQL.crearTablaMaterial(con);
                    break;
                case 12:
                    MaterialSQL.insertarMaterial(con);
                    break;
                case 13:
                    MaterialSQL.mostrarMateriales(con);
                    break;
                case 14:
                    MaterialSQL.crearProcedimientoActualizarDescripcionMaterial(con);
                    break;
                case 15:
                    MaterialSQL.llamarActualizarDescripcion(
                            con, MaterialSQL.pedirIdCategoria(),
                            MaterialSQL.pedirNuevaDescripcion());
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Escoja una opcion correcta!");
            }
        } while (opcion != 0);
    }
}

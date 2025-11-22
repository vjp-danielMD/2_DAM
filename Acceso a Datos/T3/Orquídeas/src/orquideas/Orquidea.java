/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orquideas;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class Orquidea {

    //crear tabla
    public static void crearTablaOrquidea(Connection conexion) {
        String sql = "CREATE TABLE IF NOT EXISTS orquidea (\n"
                + "    id_orquidea INT PRIMARY KEY AUTO_INCREMENT,\n"
                + "    especie VARCHAR(150) NOT NULL,\n"
                + "    habitat VARCHAR(150),\n"
                + "    peligro_extincion VARCHAR(100),\n"
                + "    aroma VARCHAR(150),\n"
                + "    propiedades TEXT,\n"
                + "    id_sustrato INT,\n"
                + "    FOREIGN KEY (id_sustrato) REFERENCES sustrato(id_sustrato));";
        try (Statement st = conexion.createStatement()) {
            st.execute(sql);
            System.out.println("Tabla creada con exito.");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1050) {
                System.out.println("Tabla 'orquidea' ya existe");
            } else {
                e.printStackTrace();
            }
        }
    }

    //insertar
    public static void insertarOrquidea(Connection conexion) {
        Scanner sc = new Scanner(System.in);

        String especie, habitat, peligroExtincion, aroma, propiedades;
        int idSustrato;
        System.out.println("== NUEVA ORQUIEDA ==");
        System.out.println("Especie:");
        especie = sc.nextLine();
        System.out.println("Habitat:");
        habitat = sc.nextLine();
        System.out.println("Peligro de extincion:");
        peligroExtincion = sc.nextLine();
        System.out.println("Aroma:");
        aroma = sc.nextLine();
        System.out.println("Propiedades:");
        propiedades = sc.nextLine();
        System.out.println("Id_Sustrato:");
        idSustrato = Integer.parseInt(sc.nextLine());

        String sql = "INSERT INTO orquidea (especie, habitat, peligro_extincion, aroma, propiedades, id_sustrato) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, especie);
            ps.setString(2, habitat);
            ps.setString(3, peligroExtincion);
            ps.setString(4, aroma);
            ps.setString(5, propiedades);
            ps.setInt(6, idSustrato);

            int filas = ps.executeUpdate();
            System.out.println(especie + " insertada; " + filas + " filas afectadas.");
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //mostrar
    public static void mostrarOrquideas(Connection conexion) {
        String sql = "SELECT * FROM orquidea;";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery(sql);
            int contador = 0;
            while (rs.next()) {
                System.out.println("Especie: " + rs.getString("especie"));
                System.out.println("Habitat: " + rs.getString("habitat"));
                System.out.println("Peligro de extincion: " + rs.getString("peligro_extincion"));
                System.out.println("Aroma: " + rs.getString("aroma"));
                System.out.println("Propiedades: " + rs.getString("propiedades"));
                System.out.println("ID_Sustrato: " + rs.getInt("id_sustrato"));
                System.out.println("----------------------");
                contador++;
            }
            if (contador == 0) {
                System.out.println("No hay orquideas registradas.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null && st != null) {
                try {
                    rs.close();
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void mostrarOrquideasConSustratos(Connection conexion) {
        String sql = "SELECT o.especie AS Orquidea, s.nombre AS Sustrato\n"
                + "FROM orquidea o\n"
                + "JOIN sustrato s ON o.id_sustrato = s.id_sustrato;";
        Statement st = null;
        ResultSet rs = null;
        int contador = 0;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Orquídea: " + rs.getString("Orquidea"));
                System.out.println("Sustrato: " + rs.getString("Sustrato"));
                System.out.println("---------------------------");
                contador++;
            }
            if (contador == 0) {
                System.out.println("No hay orquideas con sustrato.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null && rs != null) {
                try {
                    st.close();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void crearProcedimientoBuscarPorSustrato(Connection conexion) {
        try (Statement st = conexion.createStatement()) {
            st.execute("DROP PROCEDURE IF EXISTS buscarOrquideasPorSustrato");
            String sql = """
                     CREATE PROCEDURE buscarOrquideasPorSustrato(IN pid_sustrato INT)
                     BEGIN
                         SELECT especie, habitat, aroma, propiedades
                         FROM orquidea
                         WHERE id_sustrato = pid_sustrato;
                     END
                     """;
            st.execute(sql);
            System.out.println("Procedimiento 'buscarOrquideasPorSustrato' creado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void llamarProcedimientoBuscarPorSustrato(Connection conexion, int idSustrato) {
        String call = "{call buscarOrquideasPorSustrato(?)}";
        try (CallableStatement cs = conexion.prepareCall(call)) {
            cs.setInt(1, idSustrato);
            try (ResultSet rs = cs.executeQuery()) {
                int contador = 0;
                while (rs.next()) {
                    System.out.println("Especie: " + rs.getString("especie"));
                    System.out.println("Habitat: " + rs.getString("habitat"));
                    System.out.println("Aroma: " + rs.getString("aroma"));
                    System.out.println("Propiedades: " + rs.getString("propiedades"));
                    System.out.println("---------------------------");
                    contador++;
                }
                if (contador == 0) {
                    System.out.println("No se encontraron orquídeas para ese sustrato.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

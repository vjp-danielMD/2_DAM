package pruebaselect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author daniel
 */
public class PruebaSelect {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        selectMySQL();
    }

    public static void selectMySQL() {
    try {
        // Cargar el driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establecemos la conexión con la BD
        Connection conexion = 
            DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "123456");

        // Preparamos la consulta
        Statement sentencia = conexion.createStatement();
        String sql = "SELECT * FROM departamentos";
        ResultSet resultado = sentencia.executeQuery(sql);

        // Recorremos el resultado para visualizar cada fila
        // Se hace un bucle mientras haya registros, se van visualizando
        while (resultado.next()) {
            System.out.println(resultado.getInt(1) + 
                "\t" + resultado.getString(2) + 
                "\t" + resultado.getString(3));
        }

        resultado.close(); // Cerrar ResultSet
        sentencia.close(); // Cerrar Statement
        conexion.close(); // Cerrar conexión
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
}

}

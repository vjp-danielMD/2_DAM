package orquideas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author daniel
 */
public class Conexion {
    private final static String USER = "root";
    private final static String PASS = "123456";
    private final static String URL = "jdbc:mysql://localhost:3306/orquideas";

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(URL, USER, PASS);
            return conexion;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

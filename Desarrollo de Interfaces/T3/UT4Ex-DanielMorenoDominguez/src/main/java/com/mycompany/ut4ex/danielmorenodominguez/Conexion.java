/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ut4ex.danielmorenodominguez;

import java.sql.*;

/**
 *
 * @author alumno
 */
public class Conexion {
    private static String USER = "root";
    private static String PASS = "";
    private static String URL = "jdbc:mysql://localhost:3306/videojuegosex";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

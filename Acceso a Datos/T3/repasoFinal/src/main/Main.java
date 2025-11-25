/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.*;

/**
 *
 * @author daniel
 */
public class Main {

    public static void main(String[] args) {
        Connection con = Conexion.getConnection();
        System.out.println("Conexion con BBDD establecida!");
        menu(con);
        try {
            con.close();
            System.out.println("Conexion cerrada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void menu(Connection con){
        
    }
}

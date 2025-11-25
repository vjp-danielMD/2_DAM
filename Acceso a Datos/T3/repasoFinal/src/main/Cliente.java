package main;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class Cliente {

    private static String nombre;
    private static String direccion;
    private static String poblacion;
    private static String telefono;
    private static String NIF;

    public Cliente() {
    }

    public Cliente(String nombre, String direccion, String poblacion, String telefono, String NIF) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.telefono = telefono;
        this.NIF = NIF;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public static Cliente getCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        System.out.print("Dirección: ");
        direccion = sc.nextLine();
        System.out.print("Población: ");
        poblacion = sc.nextLine();
        System.out.print("Teléfono: ");
        telefono = sc.nextLine();
        System.out.print("NIF: ");
        NIF = sc.nextLine();
        return new Cliente(nombre, direccion, poblacion, telefono, NIF);
    }
}

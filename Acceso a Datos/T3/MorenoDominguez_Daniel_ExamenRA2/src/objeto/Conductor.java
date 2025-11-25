/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objeto;

import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Conductor {

    private String nif;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String poblacion;
    private String provincia;
    private int telefono;

    public Conductor(String nif, String nombre, String apellidos, String direccion, String poblacion, String provincia, int telefono) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.telefono = telefono;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Conductor getConductor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("NIF:");
        this.nif = sc.nextLine();
        System.out.println("Nombre:");
        this.nombre = sc.nextLine();
        System.out.println("Apellidos");
        this.apellidos = sc.nextLine();
        System.out.println("Direccion:");
        this.direccion = sc.nextLine();
        System.out.println("Poblacion:");
        this.poblacion = sc.nextLine();
        System.out.println("Provincia:");
        this.provincia = sc.nextLine();
        System.out.println("Telefono:");
        this.telefono = sc.nextInt();
        return new Conductor(nif, nombre, apellidos, direccion, poblacion, provincia, telefono);
    }
}

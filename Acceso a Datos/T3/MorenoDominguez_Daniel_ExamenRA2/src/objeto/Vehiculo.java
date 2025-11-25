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
public class Vehiculo {

    private String matricula;
    private String nif_conductor;
    private String marca;
    private String modelo;
    private int cv;

    public Vehiculo(String matricula, String nif_conductor, String marca, String modelo, int cv) {
        this.matricula = matricula;
        this.nif_conductor = nif_conductor;
        this.marca = marca;
        this.modelo = modelo;
        this.cv = cv;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNif_conductor() {
        return nif_conductor;
    }

    public void setNif_conductor(String nif_conductor) {
        this.nif_conductor = nif_conductor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }
    
    public Vehiculo getVehiculo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Mastricula:");
        this.matricula = sc.nextLine();
        System.out.println("NIF:");
        this.nif_conductor = sc.nextLine();
        System.out.println("Marca:");
        this.marca = sc.nextLine();
        System.out.println("Modelo:");
        this.modelo = sc.nextLine();
        System.out.println("CV:");
        this.cv = sc.nextInt();
        return new Vehiculo(matricula, nif_conductor, marca, modelo, cv);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objeto;

import java.util.Scanner;
import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author alumno
 */
public class Multa {
    
    private static int codMulta;
    private static String matricula;
    private static Date fecha = Date.valueOf(LocalDate.now());
    private static String lugar;
    private static int tipo;
    private static double sancion;

    private Multa(String nif, String lugar, int tipo, double sancion) {
        this.matricula = nif;
        this.lugar = lugar;
        this.tipo = tipo;
        this.sancion = sancion;
    }

    public int getCodMulta() {
        return codMulta;
    }

    public void setCodMulta(int codMulta) {
        this.codMulta = codMulta;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getSancion() {
        return sancion;
    }

    public void setSancion(double sancion) {
        this.sancion = sancion;
    }

    
    public static Multa getMulta(){
        Scanner sc = new Scanner(System.in);
        System.out.println("NIF:");
        String nif = sc.nextLine();
        System.out.println("Lugar:");
        lugar = sc.nextLine();
        System.out.println("Tipo: ");
        tipo =  sc.nextInt();
        System.out.println("Sancion: ");
        sancion = sc.nextDouble();
        return new Multa(nif, lugar, tipo, sancion);
    }
    
    
}

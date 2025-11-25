/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class Producto {

    private static String descripcion;
    private static int stockActual;
    private static int stockMinimo;
    private static double pvp;

    public Producto(String descripcion, int stockActual, int stockMinimo, double pvp) {
        this.descripcion = descripcion;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.pvp = pvp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    public static Producto getProducto() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Descripcion: ");
        descripcion = sc.nextLine();
        System.out.print("stock actual: ");
        stockActual = sc.nextInt();
        System.out.print("stock minimo: ");
        stockMinimo = sc.nextInt();
        System.out.print("P.V.P.: ");
        pvp = sc.nextDouble();
        return new Producto(descripcion, stockActual, stockMinimo, pvp);
    }
}

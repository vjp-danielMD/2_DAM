/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author danie
 */
import java.text.DecimalFormat;

public class Pedido {
    private String hamburguesa;
    private String pan;
    private String patatas;
    private String bebida;
    private boolean doble;
    private boolean queso;
    private boolean extraPatatas;
    private int ketchup;
    private int barbacoa;
    private int mostaza;
    private int thai;
    private boolean domicilio;
    private double precioSinIVA;
    private double iva;
    private double pvp;

    public Pedido(String hamburguesa, String pan, String patatas, String bebida,
                  boolean doble, boolean queso, boolean extraPatatas,
                  int ketchup, int barbacoa, int mostaza, int thai,
                  boolean domicilio,
                  double precioSinIVA, double iva, double pvp) {

        this.hamburguesa = hamburguesa;
        this.pan = pan;
        this.patatas = patatas;
        this.bebida = bebida;
        this.doble = doble;
        this.queso = queso;
        this.extraPatatas = extraPatatas;
        this.ketchup = ketchup;
        this.barbacoa = barbacoa;
        this.mostaza = mostaza;
        this.thai = thai;
        this.domicilio = domicilio;
        this.precioSinIVA = precioSinIVA;
        this.iva = iva;
        this.pvp = pvp;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return "Hamburguesa: " + hamburguesa +
               ", Pan: " + pan +
               ", Patatas: " + patatas +
               ", Bebida: " + bebida +
               ", Doble: " + doble +
               ", Queso: " + queso +
               ", Extra Patatas: " + extraPatatas +
               ", Salsas(K/B/M/T): " + ketchup + "/" + barbacoa + "/" + mostaza + "/" + thai +
               ", Domicilio: " + domicilio +
               ", Precio: " + df.format(precioSinIVA) + "€" +
               ", IVA: " + df.format(iva) + "€" +
               ", PVP: " + df.format(pvp) + "€";
    }
}

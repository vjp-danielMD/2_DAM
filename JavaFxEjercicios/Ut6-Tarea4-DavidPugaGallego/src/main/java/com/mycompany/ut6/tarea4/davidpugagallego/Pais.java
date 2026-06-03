package com.mycompany.ut6.tarea4.davidpugagallego;

public class Pais {
    private String nombre;
    private String nombreOficial;
    private String capital;
    private String region;
    private String subregion;
    private long poblacion;
    private String monedas;
    private String idiomas;
    private String urlBandera;

    // Constructor vacío
    public Pais() {}

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNombreOficial() { return nombreOficial; }
    public void setNombreOficial(String nombreOficial) { this.nombreOficial = nombreOficial; }

    public String getCapital() { return capital; }
    public void setCapital(String capital) { this.capital = capital; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getSubregion() { return subregion; }
    public void setSubregion(String subregion) { this.subregion = subregion; }

    public long getPoblacion() { return poblacion; }
    public void setPoblacion(long poblacion) { this.poblacion = poblacion; }

    public String getMonedas() { return monedas; }
    public void setMonedas(String monedas) { this.monedas = monedas; }

    public String getIdiomas() { return idiomas; }
    public void setIdiomas(String idiomas) { this.idiomas = idiomas; }

    public String getUrlBandera() { return urlBandera; }
    public void setUrlBandera(String urlBandera) { this.urlBandera = urlBandera; }

    // CRUCIAL: Esto es lo que verá el usuario en el ListView
    @Override
    public String toString() {
        return this.nombre;
    }
}
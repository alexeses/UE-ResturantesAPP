package com.github.alexeses.model;

public class Restaurantes {
    private final String nombre;
    private final String region;
    private final String ciudad;
    private final int distintion;
    private final String direccion;
    private final double precio_min;
    private final double precio_max;
    private final String cocina;
    private final String telefono;
    private final String web;

    public Restaurantes(String nombre, String region, String ciudad, int distintion, String direccion, double precio_min, double precio_max, String cocina, String telefono, String web) {
        this.nombre = nombre;
        this.region = region;
        this.ciudad = ciudad;
        this.distintion = distintion;
        this.direccion = direccion;
        this.precio_min = precio_min;
        this.precio_max = precio_max;
        this.cocina = cocina;
        this.telefono = telefono;
        this.web = web;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRegion() {
        return region;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getDistintion() {
        return distintion;
    }

    public String traducirDist() {
        if (distintion == 1) {
            return "*";
        } else if (distintion == 2) {
            return "**";
        } else if (distintion == 3) {
            return "***";
        }
        return "ERROR";
    }

    public String traducirPrecio(double min, double max) {
        double minPrecio = Double.parseDouble(String.valueOf(min));
        double maxPrecio = Double.parseDouble(String.valueOf(max));
        if (minPrecio == maxPrecio) {
            return String.valueOf(minPrecio);
        } else {
            return minPrecio + " - " + maxPrecio;
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public double getPrecio_min() {
        return precio_min;
    }

    public double getPrecio_max() {
        return precio_max;
    }

    public String getCocina() {
        return cocina;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getWeb() {
        return web;
    }

    public void add(Restaurantes restaurantes) {
        restaurantes.add(restaurantes);
    }
}

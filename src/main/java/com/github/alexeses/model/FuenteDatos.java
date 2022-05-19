/* package com.github.alexeses.model;

import com.github.alexeses.persistencia.RestaurantesPersistencia;

import java.util.ArrayList;

public class FuenteDatos {

    RestaurantesPersistencia restaurantesPersistencia;

    private ArrayList<Restaurantes> restaurantes;
    private Restaurantes restaurante;

    public FuenteDatos() {
        restaurantes = new ArrayList<Restaurantes>();
    }

    public void addRestaurante(Restaurantes restaurante) {
        restaurantes.add(restaurante);
    }

    public ArrayList<Restaurantes> getRestaurantes() {

        RestaurantesPersistencia restaurantesPersistencia = new RestaurantesPersistencia();
        restaurantes = restaurantesPersistencia.getAllRestaurantes();

        return restaurantes;
    }

    public ArrayList<Restaurantes> getSelectRestaurantes(String reg, Integer dist) {

        RestaurantesPersistencia restaurantesPersistencia = new RestaurantesPersistencia();
        restaurantes = restaurantesPersistencia.getRestaurantes(reg, dist);

        //     public Restaurantes getRestaurantes(String reg, Integer dist) {

        return restaurantes;
    }
}
*/
package com.github.alexeses.control;

import com.github.alexeses.gui.VConsultas;
import com.github.alexeses.gui.VMenu;
import com.github.alexeses.gui.VWelcome;
import com.github.alexeses.model.FuenteDatos;
import com.github.alexeses.persistencia.RestaurantesPersistencia;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CMichelin implements ActionListener {

    VConsultas vC;
    VMenu vM;
    VWelcome vW;
    FuenteDatos datos;

    public CMichelin(VConsultas vC, VMenu vM, VWelcome vW, FuenteDatos datos) {
        this.vC = vC;
        this.vM = vM;
        this.vW = vW;
        this.datos = datos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton) {
            if (e.getActionCommand().contains("Buscar")) {
                    // recuperar datos del filtro
                    // solicitar a restaurantePersistencia los restaurantes que cumplan con el filtro
                    // mostrar los restaurantes en la ventana de consultas
                //     public Restaurantes getRestaurantes(String reg, Integer dist) {
                //        Restaurantes restaurantes = null;

                String region = vC.getCmbxRegion().getSelectedItem().toString();
                Integer distincion = vC.getCmbxDistincion().getSelectedIndex();

                vC.updateTable(datos.getSelectRestaurantes(region, distincion));
                System.out.println("region: " + region + " distincion: " + distincion);
                System.out.println("restaurantes: " + datos.getSelectRestaurantes(region, distincion));

            }
        }

        if (e.getSource() instanceof JMenuItem) {
            if (e.getActionCommand().contains(vM.OPC1)) {
                vM.cargarPanel(vC);
                vC.updateTable(datos.getRestaurantes());


            } else if (e.getActionCommand().contains(vM.OPC2)) {
                System.out.println("Consulta 2");
            } else if (e.getActionCommand().contains(vM.OPC3)) {
                System.out.println("Consulta 3");
            }
        }


    }
}

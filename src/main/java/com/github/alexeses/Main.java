package com.github.alexeses;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.github.alexeses.control.CMichelin;
import com.github.alexeses.gui.VAddRestaurante;
import com.github.alexeses.gui.VConsultas;
import com.github.alexeses.gui.VWelcome;
import com.github.alexeses.gui.VMenu;
import com.github.alexeses.model.FuenteDatos;
import com.github.alexeses.persistencia.RestaurantesPersistencia;

import javax.swing.*;
import java.awt.*;

public class Main {

    static RestaurantesPersistencia datos;

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {

            VMenu vM = new VMenu();
            VWelcome vW = new VWelcome();
          //  datos = new RestaurantesPersistencia(); PENDIENTE DE REVISION ¿?
            VConsultas vC = new VConsultas();
            VAddRestaurante vAR = new VAddRestaurante();
            RestaurantesPersistencia rp = new RestaurantesPersistencia();

            FuenteDatos datos = new FuenteDatos();

            // (VConsultas vC, VMenu vM, VWelcome vW, FuenteDatos datos, VAddRestaurante vAR, RestaurantesPersistencia rp) {
            CMichelin controlador = new CMichelin(vC, vM, vW, datos, vAR, rp);

            vW.setControlador(controlador);
            vC.setControlador(controlador);
            vM.setControlador(controlador);
            vAR.setControlador(controlador);

            vM.setVisible(true);
            vM.cargarPanel(vW);

        });
    }

}

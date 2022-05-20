package com.github.alexeses;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.github.alexeses.control.CMichelin;
import com.github.alexeses.gui.*;
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
            VModRestaurante vMR = new VModRestaurante();
            VConsultas vC = new VConsultas();
            VAddRestaurante vAR = new VAddRestaurante();
            RestaurantesPersistencia rp = new RestaurantesPersistencia();

            CMichelin controlador = new CMichelin(vC, vM, vW, vAR, vMR, rp);

            vC.setControlador(controlador);
            vM.setControlador(controlador);
            vAR.setControlador(controlador);
            vMR.setControlador(controlador);

            vM.setVisible(true);
            vM.cargarPanel(vW);

        });
    }

}

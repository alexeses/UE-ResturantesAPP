package com.github.alexeses.gui;

import com.github.alexeses.control.CMichelin;

import javax.swing.*;
import java.awt.*;

public class VMenu extends JFrame {
    private JPanel mainMenu;
    private JMenu menu01;
    private JMenuItem opc1;
    private JMenuItem opc2;
    private JMenuItem opc3;
    private JScrollPane scrPanel;
    public final static String OPC1 = "Consulta de Resturantes";
    public final static String OPC2 = "Registro de Resturantes";
    public final static String OPC3 = "Modificacion de Resturantes";
    public final static int ANCHO = 800;
    public final static int ALTO = 600;


    public VMenu() {
        add(mainMenu);
        initComponents();
    }

    public void cargarPanel(JPanel panel) {
        scrPanel.setViewportView(panel);
    }

    private void initComponents() {
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Menu Michelin");
        setSize(ANCHO, ALTO);
        centrarVentana();
        opc1.setText(OPC1);
        opc2.setText(OPC2);
        opc3.setText(OPC3);
    }

    public void setControlador(CMichelin controlador) {
        opc1.addActionListener(controlador);
        opc2.addActionListener(controlador);
        opc3.addActionListener(controlador);
    }

    private void centrarVentana() {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = this.getPreferredSize();
        setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
    }
}

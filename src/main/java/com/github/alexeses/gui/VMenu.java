package com.github.alexeses.gui;

import com.github.alexeses.control.CMichelin;
import com.github.alexeses.persistencia.MessagesConfig;

import javax.swing.*;
import java.awt.*;

public class VMenu extends JFrame {
    private JPanel mainMenu;
    private JMenu menu01;
    private JMenuItem opc1;
    private JMenuItem opc2;
    private JMenuItem opc3;
    private JScrollPane scrPanel;
    public final static int ANCHO = 800;
    public final static int ALTO = 600;
    MessagesConfig msg;

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
        menu01.setText(msg.BTN_MENU_GENERAL);
        opc1.setText(msg.BTN_MENU_CONSULTA);
        opc2.setText(msg.BTN_MENU_REGISTRO);
        opc3.setText(msg.BTN_MENU_MODIFICACION);
        setSize(ANCHO, ALTO);
        centrarVentana();

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

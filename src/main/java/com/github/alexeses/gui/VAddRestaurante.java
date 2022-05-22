package com.github.alexeses.gui;

import com.github.alexeses.control.CMichelin;
import com.github.alexeses.persistencia.MessagesConfig;
import com.github.alexeses.persistencia.RestaurantesPersistencia;

import javax.swing.*;
import java.util.ArrayList;

public class VAddRestaurante extends JPanel {
    private JLabel lblTitulo;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblCocina;
    private JComboBox cmbCocina;
    private JLabel lblRegion;
    private JComboBox cmbxRegion;
    private JLabel lblCiudad;
    private JTextField txtCiudad;
    private JLabel lblDireccion;
    private JTextField txtDireccion;
    private JLabel lblDistincion;
    private JSpinner spnDistincion;
    private JLabel lblPrMin;
    private JTextField txtPrMin;
    private JLabel lblMaximo;
    private JTextField txtPrMax;
    private JLabel lblTelefono;
    private JTextField txtTelefono;
    private JTextField txtWeb;
    private JLabel lblWeb;
    private JButton btnGuardar;
    private JButton btnBorrar;
    private JPanel mainAdd;
    private RestaurantesPersistencia rP = new RestaurantesPersistencia();
    MessagesConfig msg;

    public VAddRestaurante() {
        add(mainAdd);
        config();
    }

    public void setControlador(CMichelin controlador) {
        btnGuardar.addActionListener(controlador);
        btnBorrar.addActionListener(controlador);
    }

    public void config() {

        btnBorrar.setText(msg.BTN_REG_BORRAR);
        btnGuardar.setText(msg.BTN_REG_GUARDAR);

        spnDistincion.setModel(new SpinnerNumberModel(0, 0, 3, 1)); // Mínimo y máximo
        ArrayList<String> regiones = rP.getRegiones();

        for (String r : regiones) {
            cmbxRegion.addItem(r);
        }

        ArrayList<String> cocinas = rP.getCocinas();

        for (String c : cocinas) {
            cmbCocina.addItem(c);
        }
    }

    public void clearFields() {
        txtNombre.setText("");
        txtCiudad.setText("");
        txtDireccion.setText("");
        txtPrMin.setText("");
        txtPrMax.setText("");
        txtTelefono.setText("");
        txtWeb.setText("");
        cmbCocina.setSelectedIndex(0);
        cmbxRegion.setSelectedIndex(0);
        spnDistincion.setValue(0);
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JComboBox getCmbCocina() {
        return cmbCocina;
    }

    public JComboBox getCmbxRegion() {
        return cmbxRegion;
    }

    public JTextField getTxtCiudad() {
        return txtCiudad;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public JSpinner getSpnDistincion() {
        return spnDistincion;
    }

    public JTextField getTxtPrMin() {
        return txtPrMin;
    }

    public JTextField getTxtPrMax() {
        return txtPrMax;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtWeb() {
        return txtWeb;
    }
}

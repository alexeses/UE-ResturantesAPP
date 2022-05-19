package com.github.alexeses.gui;

import com.github.alexeses.control.CMichelin;

import javax.swing.*;

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

    public VAddRestaurante() {
        add(mainAdd);
        config();
    }


    public void setControlador(CMichelin controlador) {
        btnGuardar.addActionListener(controlador);
        btnBorrar.addActionListener(controlador);
    }

    public void config() {
        spnDistincion.setModel(new SpinnerNumberModel(0, 0, 3, 1)); // Mínimo y máximo
    }

    public void borrarCampos() {
        txtNombre.setText("");
        txtCiudad.setText("");
        txtDireccion.setText("");
        txtPrMin.setText("");
        txtPrMax.setText("");
        txtTelefono.setText("");
        txtWeb.setText("");
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

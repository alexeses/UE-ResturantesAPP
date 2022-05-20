package com.github.alexeses.gui;

import com.github.alexeses.control.CMichelin;
import com.github.alexeses.model.Restaurantes;
import com.github.alexeses.persistencia.RestaurantesPersistencia;

import javax.swing.*;
import java.util.ArrayList;

public class VModRestaurante extends JPanel {
    private JLabel lblTitulo;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JButton btnBuscar;
    private JLabel lblRegion;
    private JComboBox cmbxRegion;
    private JLabel lblCiudad;
    private JTextField txtCiudad;
    private JLabel lblDireccion;
    private JTextField txtDireccion;
    private JLabel lblCocina;
    private JComboBox cmbxCocina;
    private JLabel lblDistincion;
    private JSpinner spnDistincion;
    private JLabel lblPrecioMin;
    private JTextField txtPrMin;
    private JLabel lblPrecioMax;
    private JTextField txtPRMax;
    private JLabel lblTelefono;
    private JTextField txtTelefono;
    private JLabel lblWeb;
    private JTextField txtWeb;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JPanel mainPanel;
    private RestaurantesPersistencia rP = new RestaurantesPersistencia();

    public VModRestaurante() {
        add(mainPanel);
        configBox();
    }

    private void configBox() {

        ArrayList<String> regiones = rP.getRegiones();

        for (String r : regiones) {
            cmbxRegion.addItem(r);
        }

        cmbxCocina.setEnabled(false);
        cmbxRegion.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtCiudad.setEnabled(false);
        txtPrMin.setEnabled(false);
        txtPRMax.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtWeb.setEnabled(false);
        spnDistincion.setEnabled(false);

        spnDistincion.setModel(new SpinnerNumberModel(1, 1, 3, 1));
    }

    public void setControlador(CMichelin controlador) {
        btnGuardar.addActionListener(controlador);
        btnCancelar.addActionListener(controlador);
        btnBuscar.addActionListener(controlador);
    }

    public void rellenarRest(String nombre) {

        ArrayList<String> cocinas = rP.getCocinas();

        for (String c : cocinas) {
            cmbxCocina.addItem(c);
        }

        ArrayList<Restaurantes> restaurantes = rP.getRestNombre(nombre);

        for (Restaurantes r : restaurantes) {
            txtNombre.setText(r.getNombre());
            cmbxRegion.setSelectedItem(r.getRegion());
            txtCiudad.setText(r.getCiudad());
            txtDireccion.setText(r.getDireccion());
            cmbxCocina.setSelectedItem(r.getCocina());
            spnDistincion.setValue(r.getDistintion());
            txtPrMin.setText(String.valueOf(r.getPrecio_min()));
            txtPRMax.setText(String.valueOf(r.getPrecio_max()));
            txtTelefono.setText(r.getTelefono());
            txtWeb.setText(r.getWeb());
        }

        activarCeldas();

    }

    private void activarCeldas() {
        cmbxCocina.setEnabled(true);
        cmbxRegion.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtCiudad.setEnabled(true);
        txtPrMin.setEnabled(true);
        txtPRMax.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtWeb.setEnabled(true);
        spnDistincion.setEnabled(true);
        btnBuscar.setEnabled(false);
    }

    public void clearAllFields() {
        txtNombre.setText("");
        txtDireccion.setText("");
        txtCiudad.setText("");
        txtPrMin.setText("");
        txtPRMax.setText("");
        txtTelefono.setText("");
        txtWeb.setText("");
        spnDistincion.setValue(1);

        txtNombre.setEnabled(true);
        btnBuscar.setEnabled(true);
        txtDireccion.setEnabled(false);
        txtCiudad.setEnabled(false);
        txtPrMin.setEnabled(false);
        txtPRMax.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtWeb.setEnabled(false);
        spnDistincion.setEnabled(false);
        cmbxCocina.setEnabled(false);
        cmbxRegion.setEnabled(false);
    }

    public JTextField getTxtNombre() {
        return txtNombre;
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

    public JComboBox getCmbxCocina() {
        return cmbxCocina;
    }

    public JSpinner getSpnDistincion() {
        return spnDistincion;
    }

    public JTextField getTxtPrMin() {
        return txtPrMin;
    }

    public JTextField getTxtPRMax() {
        return txtPRMax;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtWeb() {
        return txtWeb;
    }

    public void clearFields() {
        txtNombre.setText("");
        txtDireccion.setText("");
        txtCiudad.setText("");
        txtPrMin.setText("");
        txtPRMax.setText("");
        txtTelefono.setText("");
        txtWeb.setText("");
        spnDistincion.setValue(1);
        cmbxRegion.setSelectedIndex(0);
        cmbxCocina.setSelectedIndex(0);
    }
}

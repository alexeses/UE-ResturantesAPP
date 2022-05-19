package com.github.alexeses.control;

import com.github.alexeses.gui.VAddRestaurante;
import com.github.alexeses.gui.VConsultas;
import com.github.alexeses.gui.VMenu;
import com.github.alexeses.gui.VWelcome;
//import com.github.alexeses.model.FuenteDatos;
import com.github.alexeses.model.Restaurantes;
import com.github.alexeses.persistencia.RestaurantesPersistencia;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CMichelin implements ActionListener {

    VConsultas vC;
    VMenu vM;
    VWelcome vW;
    //FuenteDatos datos;
    VAddRestaurante vAR;
    RestaurantesPersistencia rp;
    Restaurantes r;


    public CMichelin(VConsultas vC, VMenu vM, VWelcome vW, VAddRestaurante vAR, RestaurantesPersistencia rp) {
        this.vC = vC;
        this.vM = vM;
        this.vW = vW;
        this.vAR = vAR;
        this.rp = rp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton) {
            if (e.getActionCommand().contains("Buscar")) {

                String region = vC.getCmbxRegion().getSelectedItem().toString();
                Integer distincion = vC.getCmbxDistincion().getSelectedIndex();

                vC.updateTable(rp.getRestaurantes(region, distincion));

                if (vC.getTblResturantes().getRowCount() == 0) {
                    JOptionPane.showMessageDialog(vC, "No se encontraron restaurantes con esos filtros", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else if (e.getActionCommand().contains("Borrar")) {

                if (vC.getTblResturantes().getSelectedRow() != -1) {
                    int row = vC.getTblResturantes().getSelectedRow();
                    String nombre = vC.getTblResturantes().getValueAt(row, 0).toString();

                    int opcion = JOptionPane.showConfirmDialog(vC, "¿Está seguro que desea borrar el restaurante " + nombre + "?", "Borrar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (opcion == JOptionPane.YES_OPTION) {
                        rp.borrarFila(vC.getSelecction());
                    }
                } else if (vC.getTblResturantes().getRowCount() == 0) {
                    JOptionPane.showMessageDialog(vC, "No se ha seleccionado ningún restaurante", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getActionCommand().contains("Guardar")) {
                guardarRestaurante();
            } else if (e.getActionCommand().contains("Limpiar")) {
                vAR.clearFields();
            }
        }

        if (e.getSource() instanceof JMenuItem) {
            if (e.getActionCommand().contains(vM.OPC1)) {
                vM.cargarPanel(vC);
            } else if (e.getActionCommand().contains(vM.OPC2)) {
                vM.cargarPanel(vAR);
                System.out.println("Consulta 2");
            } else if (e.getActionCommand().contains(vM.OPC3)) {
                System.out.println("Consulta 3");
            }
        }
    }

    private void guardarRestaurante() {

        String nombre = null;
        String cocina;
        String region;
        String ciudad = null;
        String direccion;
        double precioMin = 0.0;
        double precioMax = 0.0;
        int distincion;
        String web;
        String telefono = null;
        boolean next = true;

        while (next) {

            if (vAR.getTxtNombre().getText().isEmpty()) {
                JOptionPane.showMessageDialog(vAR, "El nombre del restaurante no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                next = false;
            } else {
                nombre = vAR.getTxtNombre().getText();
            }

            cocina = vAR.getCmbCocina().getSelectedItem().toString();
            region = vAR.getCmbxRegion().getSelectedItem().toString();

            if (vAR.getTxtCiudad().getText().isEmpty()) {
                JOptionPane.showMessageDialog(vAR, "La ciudad no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
                next = false;
            } else {
                ciudad = vAR.getTxtCiudad().getText();
            }

            direccion = vAR.getTxtDireccion().getText();

            if (vAR.getTxtPrMin().getText().isEmpty() || vAR.getTxtPrMax().getText().isEmpty()) {
                JOptionPane.showMessageDialog(vAR, "El precio mínimo y máximo no pueden estar vacíos", "Error", JOptionPane.ERROR_MESSAGE);
                next = false;
            } else if (Integer.parseInt(vAR.getTxtPrMin().getText()) > Integer.parseInt(vAR.getTxtPrMax().getText())) {
                JOptionPane.showMessageDialog(vAR, "El precio mínimo no puede ser mayor que el precio máximo", "Error", JOptionPane.ERROR_MESSAGE);
                next = false;
            } else if (Integer.parseInt(vAR.getTxtPrMin().getText()) < 0 || Integer.parseInt(vAR.getTxtPrMax().getText()) < 0) {
                JOptionPane.showMessageDialog(vAR, "El precio mínimo y máximo no pueden ser negativos", "Error", JOptionPane.ERROR_MESSAGE);
                next = false;
            } else if (Integer.parseInt(vAR.getTxtPrMin().getText()) == 0 || Integer.parseInt(vAR.getTxtPrMax().getText()) == 0) {
                JOptionPane.showMessageDialog(vAR, "El precio mínimo y máximo no pueden ser 0", "Error", JOptionPane.ERROR_MESSAGE);
                next = false;
            } else if (vAR.getTxtPrMin().getText().matches("\\d+") && !vAR.getTxtPrMax().getText().matches("\\d+") || vAR.getTxtPrMax().getText().matches("[0-9]+") && !vAR.getTxtPrMin().getText().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(vAR, "El precio mínimo y máximo deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
                next = false;
            } else {
                precioMin = Integer.parseInt(vAR.getTxtPrMin().getText());
                precioMax = Integer.parseInt(vAR.getTxtPrMax().getText());
            }

            distincion = (Integer) vAR.getSpnDistincion().getValue();
            web = vAR.getTxtWeb().getText();

            if (!vAR.getTxtTelefono().getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(vAR, "El teléfono debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
                next = false;
            } else if (vAR.getTxtTelefono().getText().length() != 9) {
                JOptionPane.showMessageDialog(vAR, "El teléfono debe tener 9 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
                next = false;
            } else {
                telefono = vAR.getTxtTelefono().getText();
            }

            // comprobar si existe el restaurante
            if (rp.existeResturante(nombre)) {
                JOptionPane.showMessageDialog(vAR, "El restaurante ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                rp.addRestaurante(new Restaurantes(nombre, region, ciudad, distincion, direccion, precioMin, precioMax, cocina, telefono, web));
                JOptionPane.showMessageDialog(vAR, "Restaurante añadido correctamente", "Añadido", JOptionPane.INFORMATION_MESSAGE);
                vAR.clearFields();
            }
        }

    }
}

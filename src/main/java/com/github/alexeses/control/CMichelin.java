package com.github.alexeses.control;

import com.github.alexeses.gui.VAddRestaurante;
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
    VAddRestaurante vAR;
    RestaurantesPersistencia rp;


    public CMichelin(VConsultas vC, VMenu vM, VWelcome vW, FuenteDatos datos, VAddRestaurante vAR, RestaurantesPersistencia rp) {
        this.vC = vC;
        this.vM = vM;
        this.vW = vW;
        this.datos = datos;
        this.vAR = vAR;
        this.rp = rp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton) {
            if (e.getActionCommand().contains("Buscar")) {

                String region = vC.getCmbxRegion().getSelectedItem().toString();
                Integer distincion = vC.getCmbxDistincion().getSelectedIndex();

                vC.updateTable(datos.getSelectRestaurantes(region, distincion));

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
}

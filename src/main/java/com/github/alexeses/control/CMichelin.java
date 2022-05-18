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
    RestaurantesPersistencia rp;

    public CMichelin(VConsultas vC, VMenu vM, VWelcome vW, FuenteDatos datos, RestaurantesPersistencia rp) {
        this.vC = vC;
        this.vM = vM;
        this.vW = vW;
        this.datos = datos;
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
                rp.borrarFila(vC.getSelecction());
            }
        }

        if (e.getSource() instanceof JMenuItem) {
            if (e.getActionCommand().contains(vM.OPC1)) {
                vM.cargarPanel(vC);


            } else if (e.getActionCommand().contains(vM.OPC2)) {
                System.out.println("Consulta 2");
            } else if (e.getActionCommand().contains(vM.OPC3)) {
                System.out.println("Consulta 3");
            }
        }


    }
}

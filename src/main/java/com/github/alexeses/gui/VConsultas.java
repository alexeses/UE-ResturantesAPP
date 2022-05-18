package com.github.alexeses.gui;

import com.github.alexeses.control.CMichelin;
import com.github.alexeses.model.Restaurantes;
import com.github.alexeses.persistencia.MessagesConfig;
import com.github.alexeses.persistencia.RestaurantesPersistencia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class VConsultas extends JPanel {
    private JLabel lblTitulo;
    private JLabel lblRegion;
    private JComboBox cmbxRegion;
    private JLabel lblDistincion;
    private JComboBox cmbxDistincion;
    private JButton btnBuscar;
    private JTable tblResturantes;
    private JScrollPane scpTable;
    private JPanel mainConsultas;
    private DefaultTableModel model;


    public VConsultas() {
        add(mainConsultas);
        createTable();

    }
    public void updateTable(ArrayList<Restaurantes> restaurantes) {
        model.setRowCount(0); // Limpia la table
        // model.getDataVector().clear(); // Método 2 para limpiar la table


        for (Restaurantes r : restaurantes) {
            model.addRow(
                    new Object[]{r.getNombre(), r.getRegion(), r.getCiudad(), r.getDistintion(), r.getDireccion(),
                            r.getPrecio_min(), r.getPrecio_max(), r.getCocina(), r.getTelefono(), r.getWeb()});
        }

    }

    private void createTable() {
        model = (DefaultTableModel) tblResturantes.getModel();

        model.addColumn(MessagesConfig.COLUMNNOMBRE);
        model.addColumn(MessagesConfig.COLUMNREGION);
        model.addColumn(MessagesConfig.COLUMNCIUDAD);
        model.addColumn(MessagesConfig.COLUMNDISTINCION);
        model.addColumn(MessagesConfig.COLUMNDIRECCION);
        model.addColumn(MessagesConfig.COLUMNPRMIN);
        model.addColumn(MessagesConfig.COLUMNPRMAX);
        model.addColumn(MessagesConfig.COLUMNCOCINA);
        model.addColumn(MessagesConfig.COLUMNTELEFONO);
        model.addColumn(MessagesConfig.COLUMNWEB);

        tblResturantes.setModel(model);
    }


    public void setControlador(CMichelin controlador) {
        cmbxRegion.addActionListener(controlador);
        cmbxDistincion.addActionListener(controlador);
        btnBuscar.addActionListener(controlador);
    }

    public JComboBox getCmbxRegion() {
        return cmbxRegion;
    }

    public JComboBox getCmbxDistincion() {
        return cmbxDistincion;
    }
}
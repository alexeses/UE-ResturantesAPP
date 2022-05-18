package com.github.alexeses.persistencia;

import com.github.alexeses.db.AccesoDB;
import com.github.alexeses.model.Restaurantes;

import java.sql.*;
import java.util.ArrayList;

public class RestaurantesPersistencia {

    private final AccesoDB db;
    private ArrayList<Restaurantes> restaurantes = new ArrayList<>();
    public RestaurantesPersistencia() {
        db = new AccesoDB();
    }

    public ArrayList<Restaurantes> getAllRestaurantes() {
        restaurantes = new ArrayList<>();

        String query = "SELECT * FROM "
                + MessagesConfig.BD_TABLA + ";";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = db.getConexion();
            stmt = con.createStatement(); // crear un statement para realizar la consulta
            rs = stmt.executeQuery(query); // ejecutar la query

            while (rs.next()) {
                String nombre = rs.getString(MessagesConfig.COLUMNNOMBRE);
                String region = rs.getString(MessagesConfig.COLUMNREGION);
                String ciudad = rs.getString(MessagesConfig.COLUMNCIUDAD);
                int distincion = rs.getInt(MessagesConfig.COLUMNDISTINCION);
                String direccion = rs.getString(MessagesConfig.COLUMNDIRECCION);
                double precio_min = rs.getDouble(MessagesConfig.COLUMNPRMIN);
                double precio_max = rs.getDouble(MessagesConfig.COLUMNPRMAX);
                String cocina = rs.getString(MessagesConfig.COLUMNCOCINA);
                String telefono = rs.getString(MessagesConfig.COLUMNTELEFONO);
                String web = rs.getString(MessagesConfig.COLUMNWEB);

                restaurantes.add(new Restaurantes(nombre, region, ciudad, distincion, direccion, precio_min, precio_max, cocina, telefono, web));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, stmt, rs);
        }

        return restaurantes;
    }

    public ArrayList<Restaurantes> getRestaurantesRegion(String reg) {

        restaurantes = new ArrayList<>();

        String query = "SELECT * FROM "
                + MessagesConfig.BD_TABLA + " WHERE REGION = '" + reg + "';";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = db.getConexion();
            stmt = con.createStatement(); // crear un statement para realizar la consulta
            rs = stmt.executeQuery(query); // ejecutar la query

            while (rs.next()) {
                String nombre = rs.getString(MessagesConfig.COLUMNNOMBRE);
                String region = rs.getString(MessagesConfig.COLUMNREGION);
                String ciudad = rs.getString(MessagesConfig.COLUMNCIUDAD);
                int distincion = rs.getInt(MessagesConfig.COLUMNDISTINCION);
                String direccion = rs.getString(MessagesConfig.COLUMNDIRECCION);
                double precio_min = rs.getDouble(MessagesConfig.COLUMNPRMIN);
                double precio_max = rs.getDouble(MessagesConfig.COLUMNPRMAX);
                String cocina = rs.getString(MessagesConfig.COLUMNCOCINA);
                String telefono = rs.getString(MessagesConfig.COLUMNTELEFONO);
                String web = rs.getString(MessagesConfig.COLUMNWEB);

                restaurantes.add(new Restaurantes(nombre, region, ciudad, distincion, direccion, precio_min, precio_max, cocina, telefono, web));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, stmt, rs);
        }

        return restaurantes;
    }

    public ArrayList<Restaurantes> getRestaurantesDist(Integer dist) {

        restaurantes = new ArrayList<>();

        String query = "SELECT * FROM "
                + MessagesConfig.BD_TABLA + " WHERE DISTINCION = '" + dist + "';";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = db.getConexion();
            stmt = con.createStatement(); // crear un statement para realizar la consulta
            rs = stmt.executeQuery(query); // ejecutar la query

            while (rs.next()) {
                String nombre = rs.getString(MessagesConfig.COLUMNNOMBRE);
                String region = rs.getString(MessagesConfig.COLUMNREGION);
                String ciudad = rs.getString(MessagesConfig.COLUMNCIUDAD);
                int distincion = rs.getInt(MessagesConfig.COLUMNDISTINCION);
                String direccion = rs.getString(MessagesConfig.COLUMNDIRECCION);
                double precio_min = rs.getDouble(MessagesConfig.COLUMNPRMIN);
                double precio_max = rs.getDouble(MessagesConfig.COLUMNPRMAX);
                String cocina = rs.getString(MessagesConfig.COLUMNCOCINA);
                String telefono = rs.getString(MessagesConfig.COLUMNTELEFONO);
                String web = rs.getString(MessagesConfig.COLUMNWEB);

                restaurantes.add(new Restaurantes(nombre, region, ciudad, distincion, direccion, precio_min, precio_max, cocina, telefono, web));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, stmt, rs);
        }

        return restaurantes;
    }


    public ArrayList<Restaurantes> getRestaurantes(String reg, Integer dist) {
        restaurantes = new ArrayList<>();

        if (dist == 0 && reg.contains("Todo")) {
            return getAllRestaurantes();
        } else if (dist == 0 && !reg.contains("Todo")) {
            return getRestaurantesRegion(reg);
        } else if (dist != 0 && reg.contains("Todo")) {
            return getRestaurantesDist(dist);
        } else {
            String query = "SELECT * FROM "
                    + MessagesConfig.BD_TABLA +
                    " WHERE " + MessagesConfig.COLUMNREGION
                    + " = ? AND "
                    + MessagesConfig.COLUMNDISTINCION + " = ?;";

            Connection con = null;
            PreparedStatement pstmt = null; // Por que su inicialización deberá ir entre un try y un catch
            ResultSet rs = null;


            try {
                con = db.getConexion();
                pstmt = con.prepareStatement(query); // preparar la query
                pstmt.setString(1, reg); // (pos1, parámetro del método)
                pstmt.setInt(2, dist); // (pos2, parámetro del método)
                rs = pstmt.executeQuery(); // ejecutar la query

                while (rs.next()) {

                    String nombre = rs.getString(MessagesConfig.COLUMNNOMBRE);
                    String region = rs.getString(MessagesConfig.COLUMNREGION);
                    String ciudad = rs.getString(MessagesConfig.COLUMNCIUDAD);
                    int distincion = rs.getInt(MessagesConfig.COLUMNDISTINCION);
                    String direccion = rs.getString(MessagesConfig.COLUMNDIRECCION);
                    double precio_min = rs.getDouble(MessagesConfig.COLUMNPRMIN);
                    double precio_max = rs.getDouble(MessagesConfig.COLUMNPRMAX);
                    String cocina = rs.getString(MessagesConfig.COLUMNCOCINA);
                    String telefono = rs.getString(MessagesConfig.COLUMNTELEFONO);
                    String web = rs.getString(MessagesConfig.COLUMNWEB);

                    restaurantes.add(new Restaurantes(nombre, region, ciudad, distincion, direccion, precio_min, precio_max, cocina, telefono, web));

                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                finConnect(con, pstmt, rs);
            }

            return restaurantes;

        }
    }

    public void borrarFila(String id) {

        String query = "DELETE FROM " + MessagesConfig.BD_TABLA + " WHERE NOMBRE = ?;";

        Connection con = null;
        PreparedStatement pstmt = null; // Por que su inicialización deberá ir entre un try y un catch
        ResultSet rs = null;

        try {
            con = db.getConexion();
            pstmt = con.prepareStatement(query); // preparar la query
            pstmt.setString(1, id); // (pos1, parámetro del método)
            rs = pstmt.executeQuery(); // ejecutar la query

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, pstmt, rs);
        }
    }

    private void finConnect(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
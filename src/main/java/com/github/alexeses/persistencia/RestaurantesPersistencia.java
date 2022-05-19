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

    // Method add restuarant
    public void addRestaurante(Restaurantes rest) {
        String query = "INSERT INTO " + MessagesConfig.BD_TABLA + " ("
                + MessagesConfig.COLUMNNOMBRE + ", "
                + MessagesConfig.COLUMNREGION + ", "
                + MessagesConfig.COLUMNCIUDAD + ", "
                + MessagesConfig.COLUMNDISTINCION + ", "
                + MessagesConfig.COLUMNDIRECCION + ", "
                + MessagesConfig.COLUMNPRMIN + ", "
                + MessagesConfig.COLUMNPRMAX + ", "
                + MessagesConfig.COLUMNCOCINA + ", "
                + MessagesConfig.COLUMNTELEFONO + ", "
                + MessagesConfig.COLUMNWEB + ") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        Connection con = null;
        PreparedStatement pstmt = null; // Por que su inicialización deberá ir entre un try y un catch
        ResultSet rs = null;

        try {
            con = db.getConexion();
            pstmt = con.prepareStatement(query); // preparar la query
            pstmt.setString(1, rest.getNombre()); // (pos1, parámetro del método)
            pstmt.setString(2, rest.getRegion()); // (pos2, parámetro del método)
            pstmt.setString(3, rest.getCiudad()); // (pos3, parámetro del método)
            pstmt.setInt(4, rest.getDistintion()); // (pos4, parámetro del método)
            pstmt.setString(5, rest.getDireccion()); // (pos5, parámetro d l método)
            pstmt.setDouble(6, rest.getPrecio_min()); // (pos6, parámetro del método)
            pstmt.setDouble(7, rest.getPrecio_max()); // (pos7, parámetro del método)
            pstmt.setString(8, rest.getCocina()); // (pos8, parámetro del método)
            pstmt.setString(9, rest.getTelefono()); // (pos9, parámetro del método)
            pstmt.setString(10, rest.getWeb()); // (pos10, parámetro del método)
            pstmt.executeUpdate(); // ejecutar la query
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, pstmt, rs);
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
            pstmt.executeUpdate(); // ejecutar la query

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, pstmt, rs);
        }
    }

    public boolean existeResturante(String rest) {
        String query = "SELECT * FROM " + MessagesConfig.BD_TABLA + " WHERE NOMBRE = ?;";

        Connection con = null;
        PreparedStatement pstmt = null; // Por que su inicialización deberá ir entre un try y un catch
        ResultSet rs = null;
        boolean existe = false;

        try {
            con = db.getConexion();
            pstmt = con.prepareStatement(query); // preparar la query
            pstmt.setString(1, rest); // (pos1, parámetro del método)
            rs = pstmt.executeQuery(); // ejecutar la query
            if(rs.next()){
                existe = true;
            } else {
                existe = false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, pstmt, rs);
        }
        return existe;
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
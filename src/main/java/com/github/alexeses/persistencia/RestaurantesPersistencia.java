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

            getInfo(rs);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, stmt, rs);
        }

        return restaurantes;
    }

    private void getInfo(ResultSet rs) throws SQLException {
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
    }

    public ArrayList<Restaurantes> getRestaurantesRegion(String reg) {

        restaurantes = new ArrayList<>();

        String query = "SELECT * FROM " + MessagesConfig.BD_TABLA + " WHERE REGION = ?;";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = db.getConexion();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, reg);
            rs = pstmt.executeQuery();

            getInfo(rs);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, pstmt, rs);
        }

        return restaurantes;
    }

    public ArrayList<Restaurantes> getRestaurantesDist(Integer dist) {

        restaurantes = new ArrayList<>();

        String query = "SELECT * FROM "
                + MessagesConfig.BD_TABLA + " WHERE DISTINCION = ?;";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = db.getConexion();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, dist);
            rs = pstmt.executeQuery();

            getInfo(rs);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, pstmt, rs);
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
            PreparedStatement pstmt = null; // Por que su inicializaci??n deber?? ir entre un try y un catch
            ResultSet rs = null;


            try {
                con = db.getConexion();
                pstmt = con.prepareStatement(query); // preparar la query
                pstmt.setString(1, reg); // (pos1, par??metro del m??todo)
                pstmt.setInt(2, dist); // (pos2, par??metro del m??todo)
                rs = pstmt.executeQuery(); // ejecutar la query

                getInfo(rs);

            } catch (ClassNotFoundException | SQLException e) {
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
        PreparedStatement pstmt = null; // Por que su inicializaci??n deber?? ir entre un try y un catch

        try {
            con = db.getConexion();
            pstmt = con.prepareStatement(query); // preparar la query
            pstmt.setString(1, rest.getNombre()); // (pos1, par??metro del m??todo)
            pstmt.setString(2, rest.getRegion()); // (pos2, par??metro del m??todo)
            pstmt.setString(3, rest.getCiudad()); // (pos3, par??metro del m??todo)
            pstmt.setInt(4, rest.getDistintion()); // (pos4, par??metro del m??todo)
            pstmt.setString(5, rest.getDireccion()); // (pos5, par??metro d l m??todo)
            pstmt.setDouble(6, rest.getPrecio_min()); // (pos6, par??metro del m??todo)
            pstmt.setDouble(7, rest.getPrecio_max()); // (pos7, par??metro del m??todo)
            pstmt.setString(8, rest.getCocina()); // (pos8, par??metro del m??todo)
            pstmt.setString(9, rest.getTelefono()); // (pos9, par??metro del m??todo)
            pstmt.setString(10, rest.getWeb()); // (pos10, par??metro del m??todo)
            pstmt.executeUpdate(); // ejecutar la query
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, pstmt, null);
        }

    }

    public void borrarFila(String id) {

        String query = "DELETE FROM " + MessagesConfig.BD_TABLA + " WHERE NOMBRE = ?;";

        Connection con = null;
        PreparedStatement pstmt = null; // Por que su inicializaci??n deber?? ir entre un try y un catch

        try {
            con = db.getConexion();
            pstmt = con.prepareStatement(query); // preparar la query
            pstmt.setString(1, id); // (pos1, par??metro del m??todo)
            pstmt.executeUpdate(); // ejecutar la query

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, pstmt, null);
        }
    }

    public boolean existeResturante(String rest) {
        String query = "SELECT * FROM " + MessagesConfig.BD_TABLA + " WHERE NOMBRE = ?;";

        Connection con = null;
        PreparedStatement pstmt = null; // Por que su inicializaci??n deber?? ir entre un try y un catch
        ResultSet rs = null;
        boolean existe = false;

        try {
            con = db.getConexion();
            pstmt = con.prepareStatement(query); // preparar la query
            pstmt.setString(1, rest); // (pos1, par??metro del m??todo)
            rs = pstmt.executeQuery(); // ejecutar la query
            existe = rs.next();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        } finally {
            finConnect(con, pstmt, rs);
        }
        return existe;
    }

    public ArrayList<String> getRegiones() {
        String query = "SELECT DISTINCT REGION FROM " + MessagesConfig.BD_TABLA + ";";

        Connection con = null;
        Statement stmt = null; // Por que su inicializaci??n deber?? ir entre un try y un catch
        ResultSet rs = null;
        ArrayList<String> regiones = new ArrayList<>();

        try {
            con = db.getConexion();
            stmt = con.createStatement(); // preparar la query
            rs = stmt.executeQuery(query); // ejecutar la query
            while (rs.next()) {
                regiones.add(rs.getString("REGION"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, stmt, rs);
        }
        return regiones;
    }

    public ArrayList<String> getCocinas() {
        String query = "SELECT DISTINCT COCINA FROM " + MessagesConfig.BD_TABLA + ";";

        Connection con = null;
        Statement stmt = null; // Por que su inicializaci??n deber?? ir entre un try y un catch
        ResultSet rs = null;
        ArrayList<String> cocinas = new ArrayList<>();

        try {
            con = db.getConexion();
            stmt = con.createStatement(); // preparar la query
            rs = stmt.executeQuery(query); // ejecutar la query
            while (rs.next()) {
                cocinas.add(rs.getString("COCINA"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, stmt, rs);
        }
        return cocinas;
    }

    public ArrayList<Restaurantes> getRestNombre(String nom) {

        restaurantes = new ArrayList<>();

        String query = "SELECT * FROM "
                + MessagesConfig.BD_TABLA + " WHERE NOMBRE = ?;";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = db.getConexion();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, nom);
            rs = pstmt.executeQuery();

            getInfo(rs);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, pstmt, rs);
        }

        if (restaurantes.isEmpty()) {
            return null;
        }

        return restaurantes;
    }

    public void modificarRestaurante(String nombre, String region, String ciudad, int distincion, String direccion, double precioMin, double precioMax, String cocina, String telefono, String web) {
        String query = "UPDATE " + MessagesConfig.BD_TABLA + " SET "
                + MessagesConfig.COLUMNREGION + " = ? , "
                + MessagesConfig.COLUMNCIUDAD + " = ? , "
                + MessagesConfig.COLUMNDISTINCION + " = ? , "
                + MessagesConfig.COLUMNDIRECCION + " = ? , "
                + MessagesConfig.COLUMNPRMIN + " = ? , "
                + MessagesConfig.COLUMNPRMAX + " = ? , "
                + MessagesConfig.COLUMNCOCINA + " = ? , "
                + MessagesConfig.COLUMNTELEFONO + " = ? , "
                + MessagesConfig.COLUMNWEB + " = ? "
                + "WHERE " + MessagesConfig.COLUMNNOMBRE + " = ?;";

        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = db.getConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, region);
            stmt.setString(2, ciudad);
            stmt.setInt(3, distincion);
            stmt.setString(4, direccion);
            stmt.setDouble(5, precioMin);
            stmt.setDouble(6, precioMax);
            stmt.setString(7, cocina);
            stmt.setString(8, telefono);
            stmt.setString(9, web);
            stmt.setString(10, nombre);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            finConnect(con, stmt, null);
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
package com.github.alexeses.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AccesoDB {
    private String driver;
    private String url;

    public AccesoDB() {
        //driver ="org.sqlite.JDBC";
        //url = "jdbc:sqlite:db/data.db";
        Properties prop = new Properties();
        InputStream is = null;

        try {
            is = new FileInputStream("db/ConfigDB.properties");
            prop.load(is);

            driver = prop.getProperty("DRIVER");
            url = prop.getProperty("URL");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public Connection getConexion() throws ClassNotFoundException, SQLException {
        Class.forName(driver);

        return DriverManager.getConnection(url);
    }
}

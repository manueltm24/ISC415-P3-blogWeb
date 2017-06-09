package com.blogWeb.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mt on 04/06/17.
 */
public class ConexionDB {
    private static ConexionDB instancia;
    private String URL = "jdbc:h2:tcp://localhost/~/blog"; //Modo Server...

    /**
     *Implementando el patron Singlenton
     */
    private  ConexionDB(){
        registrarDriver();
    }

    /**
     * Retornando la instancia.
     * @return
     */
    public static ConexionDB getInstancia(){
        if(instancia==null){
            instancia = new ConexionDB();
        }
        return instancia;
    }

    /**
     * Metodo para el registro de driver de conexión.
     */
    private void registrarDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, "sa", "");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }

}

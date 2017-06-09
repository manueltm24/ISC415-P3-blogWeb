package com.blogWeb.Clases;

import com.blogWeb.DataBase.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mt on 06/06/17.
 */
public class Etiqueta {
    private int id;
    private String etiqueta;

    public static List<Etiqueta> listadoEtiquetas() {
        ArrayList<Etiqueta> articulos = new ArrayList<Etiqueta>();


        Articulo art = null;
        Connection con = null;
        try {

            String query = "SELECT * FROM etiqueta";
            con = ConexionDB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                art = new Articulo();
                art.setId(rs.getInt("id"));
                art.setTitulo(rs.getString("etiqueta"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return articulos;

    }

    public static void insertarEtiqueta(Etiqueta etiqueta) {
        Connection con = null;
        try {

            String query = "INSERT INTO ETIQUETA(id, etiqueta) VALUES(?,?)";
            con = ConexionDB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, etiqueta.getId());
            prepareStatement.setString(2, etiqueta.getEtiqueta());
            //
            prepareStatement.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void eliminarEtiqueta(int idEtiqueta) {

        //ELIMINA EL ARTICULO
        Connection con = null;
        try {

            String query = "DELETE FROM ETIQUETA where id = " + idEtiqueta;
            con = ConexionDB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Ejecuto...
            prepareStatement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void modificarEtiqueta(int idEtiqueta, String etiqueta) {
        Connection con = null;
        try {
            con = ConexionDB.getInstancia().getConexion();

            PreparedStatement update = con.prepareStatement
                    ("UPDATE ARTICULO SET etiqueta = ? WHERE id = " + idEtiqueta);

            update.setString(1, etiqueta);


            update.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//QUERYS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
}

package com.blogWeb.Clases;

import com.blogWeb.DataBase.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mt on 06/06/17.
 */
public class Comentario {

    private int id;
    private String comentario;
    private int autorId;
    private int articuloId;

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public int getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(int articuloId) {
        this.articuloId = articuloId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    ///QUERYS y FUNCIONES

    public static int ultimoIdComentario(){
        int ultimoIndice=0;
        List<Comentario> listadoComentario = buscarListadoComentarios();

        if(buscarListadoComentarios().size()==0){
            ultimoIndice=1;
            return ultimoIndice;
        }
        for(int i=0;i<buscarListadoComentarios().size();i++){
            if(listadoComentario.get(i).getId()>ultimoIndice){
                ultimoIndice=listadoComentario.get(i).getId();

            }
        }
        return ultimoIndice;
    }

    public static List<Comentario> buscarListadoComentarios(){
        ArrayList<Comentario> listadoComentarios = new ArrayList<Comentario>();

        Comentario commet;
        Connection con = null;
        try {

            String query = "SELECT * FROM COMENTARIO ";
            con = ConexionDB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Ejecuto...
            ResultSet resultSet = prepareStatement.executeQuery();
            while(resultSet.next()){
                commet = new Comentario();
                commet.setId(resultSet.getInt("id"));
                commet.setComentario(resultSet.getString("comentario"));
                commet.setAutorId(resultSet.getInt("autorid"));
                commet.setArticuloId(resultSet.getInt("articuloid"));
                listadoComentarios.add(commet);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listadoComentarios;

    }


    public static void eliminarComentario(int idComentario){

        Connection con = null;
        try {

            String query = "DELETE FROM COMENTARIO where id = "+idComentario;
            con = ConexionDB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Ejecuto...
            prepareStatement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }



    public static List<Comentario> buscarListadoComentariosArticulo(int id){
        ArrayList<Comentario> listadoComentarios = new ArrayList<Comentario>();

        Comentario nuevoComentario;
        Connection con = null;
        try {

            String query = "SELECT * FROM COMENTARIO WHERE ARTICULOID= " +id;
            con = ConexionDB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                nuevoComentario = new Comentario();
                nuevoComentario.setId(rs.getInt("id"));
                nuevoComentario.setComentario(rs.getString("comentario"));
                nuevoComentario.setAutorId(rs.getInt("autorid"));
                nuevoComentario.setArticuloId(rs.getInt("articuloid"));
                listadoComentarios.add(nuevoComentario);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listadoComentarios;

    }

    public static void insertarComentario(Comentario coment){

        Connection con = null;
        try {

            String query = "INSERT INTO COMENTARIO(id, comentario, autorid, articuloid) values(?,?,?,?)";
            con = ConexionDB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, coment.getId());
            prepareStatement.setString(2, coment.getComentario());
            prepareStatement.setInt(3, coment.getAutorId());
            prepareStatement.setInt(4, coment.getArticuloId());

            //
            prepareStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}

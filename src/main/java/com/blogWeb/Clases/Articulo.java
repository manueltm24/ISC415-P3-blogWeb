package com.blogWeb.Clases;

import com.blogWeb.DataBase.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by mt on 06/06/17.
 */
public class Articulo {

    private int id;
    private String titulo;
    private String cuerpo;
    private int autor;
    private String fecha;
    private String resumen;

//QUERYS
    public static List<Articulo> listadoArticulos(){
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();


        Articulo art = null;
        Connection con = null;
        try {

            String query = "SELECT * FROM articulo";
            con = ConexionDB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                art = new Articulo();
                art.setId(rs.getInt("id"));
                art.setTitulo(rs.getString("titulo"));
                art.setCuerpo(rs.getString("cuerpo"));
                art.setAutor(rs.getInt("autor"));
                art.setFecha(rs.getString("fecha"));
                articulos.add(art);

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
        return articulos;

    }

    public static Articulo listadoArticulosEspecifico(int ArticuloId){
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();


        Articulo art = new Articulo();
        Connection con = null;
        try {

            String query = "SELECT * FROM articulo WHERE id= "+ArticuloId;
            con = ConexionDB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                //art = new Articulo();
                art.setId(rs.getInt("id"));
                art.setTitulo(rs.getString("titulo"));
                art.setCuerpo(rs.getString("cuerpo"));
                art.setAutor(rs.getInt("autor"));
                art.setFecha(rs.getString("fecha"));


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
        return art;

    }

    public static void insertarArticulo(Articulo nuevoArticulo){
        Connection con = null;
        try {

            String query = "INSERT INTO ARTICULO(id, titulo, cuerpo, autor,fecha) values(?,?,?,?,?)";
            con = ConexionDB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, nuevoArticulo.getId());
            prepareStatement.setString(2, nuevoArticulo.getTitulo());
            prepareStatement.setString(3, nuevoArticulo.getCuerpo());
            prepareStatement.setInt(4, nuevoArticulo.getAutor());
            prepareStatement.setString(5,nuevoArticulo.getFecha());
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

    public static String buscarNombreAutor(int id){
        String nombre= "";
        for(int i=0;i<Usuario.buscarListadoUsuarios().size();i++){
            if(id==Usuario.buscarListadoUsuarios().get(i).getId()){
                nombre = Usuario.buscarListadoUsuarios().get(i).getNombre();
            }
        }
        return nombre;
    }

    public static void eliminarArticulo(int idArticulo){

        //ELIMINA LOS COMENTARIOS DEL ARTICULO
        List<Comentario> comentariosELiminar = Comentario.buscarListadoComentariosArticulo(idArticulo);
        int comentarioId=0;
        for(int i=0;i<comentariosELiminar.size();i++){
            comentarioId=comentariosELiminar.get(i).getId();
            Comentario.eliminarComentario(comentarioId);
        }

        //ELIMINA EL ARTICULO
        Connection con = null;
        try {

            String query = "DELETE FROM ARTICULO where id = "+idArticulo;
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

    public static void modificarArticulo(int idArticulo, String titulo, String cuerpo) {
        Connection con = null;
        try {
            con = ConexionDB.getInstancia().getConexion();

            PreparedStatement update = con.prepareStatement
                    ("UPDATE ARTICULO SET titulo = ?, cuerpo = ? WHERE id = "+idArticulo);

            update.setString(1, titulo);
            update.setString(2,cuerpo);


            update.executeUpdate();


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

    public static int ultimoIdArticulo(){
        int ultimoIndice=0;
        List<Articulo> listadoArticulo = listadoArticulos();

        if(listadoArticulos().size()==0){
            ultimoIndice=1;
            return ultimoIndice;
        }
        for(int i=0;i<listadoArticulos().size();i++){
            if(listadoArticulo.get(i).getId()>ultimoIndice){
                ultimoIndice=listadoArticulo.get(i).getId();

            }
        }
        return ultimoIndice;
    }

    public String getFecha() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        String dateInString = new Date().toString();
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public String getResumen() {
        if (cuerpo.length() > 70) {
            return cuerpo.substring(0, 70);
        } else {
            return cuerpo;
        }

    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

}

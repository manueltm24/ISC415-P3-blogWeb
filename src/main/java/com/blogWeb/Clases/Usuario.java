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
public class Usuario {

    private int id;
    private String username;
    private String nombre;
    private String password;
    private boolean administrador;
    private boolean autor;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isAutor() {
        return autor;
    }

    public void setAutor(boolean autor) {
        this.autor = autor;
    }

    //QUERYS

    public static void insertarUsuario(Usuario est){

        Connection con = null;
        try {

            String query = "INSERT INTO USUARIO(id, username, nombre, password, administrador, autor) values(?,?,?,?,?,?)";
            con = ConexionDB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, est.getId());
            prepareStatement.setString(2, est.getUsername());
            prepareStatement.setString(3, est.getNombre());
            prepareStatement.setString(4, est.getPassword());
            prepareStatement.setBoolean(5, est.isAdministrador());
            prepareStatement.setBoolean(6, est.isAutor());
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


    public static List<Usuario> buscarListadoUsuarios(){
        ArrayList<Usuario> listadoUsuario = new ArrayList<Usuario>();

        Usuario nuevoUsuario;
        Connection con = null;
        try {

            String query = "SELECT * FROM USUARIO";
            con = ConexionDB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                nuevoUsuario = new Usuario();
                nuevoUsuario.setId(rs.getInt("id"));
                nuevoUsuario.setUsername(rs.getString("username"));
                nuevoUsuario.setNombre(rs.getString("nombre"));
                nuevoUsuario.setPassword(rs.getString("password"));
                nuevoUsuario.setAutor(rs.getBoolean("autor"));
                nuevoUsuario.setAdministrador(rs.getBoolean("administrador"));
                listadoUsuario.add(nuevoUsuario);

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
        return listadoUsuario;

    }

    public static boolean existeAdmin(){
        List<Usuario> listadoUsuario = new ArrayList<Usuario>();
        listadoUsuario=buscarListadoUsuarios();

        for(int i=0;i<listadoUsuario.size();i++){
            if(listadoUsuario.get(i).getUsername().equals("admin")){
                return true;
            }
        }
        return false;

    }

    public static int ultimoIdUsuario(){
        int ultimoIndice=0;
        List<Usuario> listadoUsuarios = buscarListadoUsuarios();

        if(buscarListadoUsuarios().size()==0){
            ultimoIndice=1;
            return ultimoIndice;
        }
        for(int i=0;i<buscarListadoUsuarios().size();i++){
            if(listadoUsuarios.get(i).getId()>ultimoIndice){
                ultimoIndice=listadoUsuarios.get(i).getId();

            }
        }
        return ultimoIndice;
    }


}

package com.blogWeb.DataBase;

import com.blogWeb.Clases.Usuario;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mt on 04/06/17.
 */
public class BootstrapServices {
    /**
     *
     * @throws SQLException
     */
    public static void startDb() throws SQLException {
        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    /**
     *
     * @throws SQLException
     */
    public static void stopDb() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
    }

    public static void crearTablas() throws  SQLException{
       crearTablaEtiqueta();
       crearTablaUsuario();
       crearTablaArticulo();
       crearTablaComentario();
        crearTablaArticuloEtiquetas();

       Usuario usuarioAdmin = new Usuario();
       usuarioAdmin.setId(1);
       usuarioAdmin.setNombre("Administrador");
       usuarioAdmin.setUsername("admin");
       usuarioAdmin.setPassword("admin");
       usuarioAdmin.setAdministrador(true);
       usuarioAdmin.setAutor(true);

//       Usuario.insertarUsuario(usuarioAdmin);


    }



    private static void crearTablaEtiqueta() throws  SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS ETIQUETA\n" +
                "(\n" +
                "  id INTEGER PRIMARY KEY NOT NULL,\n" +
                "   etiqueta VARCHAR(255) \n" +
                ");";


        Connection con = ConexionDB.getInstancia().getConexion();
        Statement statement = con.createStatement();
        statement.execute(sql);
        statement.close();
        con.close();
    }

    private static void crearTablaUsuario() throws  SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS USUARIO\n" +
                "(\n" +
                "  id INTEGER PRIMARY KEY NOT NULL,\n" +
                "  username VARCHAR(20) NOT NULL,\n" +
                "  nombre VARCHAR(30) NOT NULL,\n" +
                "  password VARCHAR(20) NOT NULL,\n" +
                "  administrador BIT NOT NULL,\n" +
                "  autor BIT NOT NULL,\n" +
                ");";


        Connection con = ConexionDB.getInstancia().getConexion();
        Statement statement = con.createStatement();
        statement.execute(sql);
        statement.close();
        con.close();
    }

    private static void crearTablaArticulo() throws  SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS ARTICULO\n" +
                "(\n" +
                "  id INTEGER PRIMARY KEY NOT NULL,\n" +
                "  titulo VARCHAR(100) NOT NULL,\n" +
                "  cuerpo VARCHAR(1000) NOT NULL,\n" +
                "  autor INTEGER NOT NULL,\n" +
                "  fecha VARCHAR(50) NOT NULL ,\n" +
                //FALTA ANADIR LISTA DE COMENTARIOS Y LISTA DE ETIQUETAS
                ");";


        Connection con = ConexionDB.getInstancia().getConexion();
        Statement statement = con.createStatement();
        statement.execute(sql);
        statement.close();
        con.close();
    }

    private static void crearTablaComentario() throws  SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS COMENTARIO\n" +
                "(\n" +
                "  id INTEGER PRIMARY KEY NOT NULL,\n" +
                "  comentario VARCHAR(500) NOT NULL,\n" +
                "  autorId INTEGER NOT NULL,\n" +
                "  articuloId INTEGER NOT NULL,\n" +
                //FALTA EL ATRIVUTO ARTIULOOO Y USUARIO!!!!!!!
                ");";


        Connection con = ConexionDB.getInstancia().getConexion();
        Statement statement = con.createStatement();
        statement.execute(sql);
        statement.close();
        con.close();
    }


    private static void crearTablaArticuloEtiquetas() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS ARTICULO_ETIQUETAS\n" +
                "(\n" +
                "  id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
                "  articuloId INTEGER NOT NULL REFERENCES ARTICULO (id),\n" +
                "  etiquetaId INTEGER NOT NULL REFERENCES ETIQUETA (id)\n" +
                //FALTA EL ATRIVUTO ARTIULOOO Y USUARIO!!!!!!!
                ");";


        Connection con = ConexionDB.getInstancia().getConexion();
        Statement statement = con.createStatement();
        statement.execute(sql);
        statement.close();
        con.close();
    }
}

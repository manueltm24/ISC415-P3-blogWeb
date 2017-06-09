package com.blogWeb;

import com.blogWeb.Clases.Articulo;
import com.blogWeb.Clases.Comentario;
import com.blogWeb.Clases.Usuario;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.SQLException;
import java.util.*;

import static com.blogWeb.DataBase.BootstrapServices.crearTablas;
import static com.blogWeb.DataBase.BootstrapServices.startDb;
import static spark.Spark.*;

/**
 * Created by mt on 04/06/17.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    static boolean estaLogueado=estaLogueado(false);
    static Usuario usuarioLogueado = new Usuario();

    public static void main(String[] args) throws SQLException {
        logger.info("Iniciando aplicacion");

        logger.info("Creando el folder estatico");
        staticFileLocation("/public/");
        logger.info("Comenzando la base de datos");
        startDb(); //ABRE EL PUERTO DE LA BASE DE DATOS
        logger.info("Creando las tablas");
        crearTablas(); //CREA TODAS LAS TABLAS NECESARIASs

        logger.info("Crear el usuario admin por defecto");
        //crearUsuarioAdmin();

        logger.info("Creando la configuracion del template de freemarker");
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(Main.class, "/templates/");
        FreeMarkerEngine freeMarkerEngine= new FreeMarkerEngine(configuration);

        get("", (request, response) -> {
            response.redirect("/home");
            return null;
        });

        get("/crearUsuario/", (request,response)-> {

            Map<String, Object> attributes = new HashMap<>();


            return new ModelAndView(null, "/crearUsuario.ftl");
        }, freeMarkerEngine );

        post("/crearUsuario/", (request,response)-> {

            Map<String, Object> attributes = new HashMap<>();
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setId(Usuario.ultimoIdUsuario()+1);
            nuevoUsuario.setNombre(request.queryMap().get("Nom").value());
            nuevoUsuario.setUsername(request.queryMap().get("User").value());
            nuevoUsuario.setPassword(request.queryMap().get("Pass").value());
            nuevoUsuario.setAdministrador(false);
            nuevoUsuario.setAutor(true);

            Usuario.insertarUsuario(nuevoUsuario);

            List<Articulo> nuevaLista = Articulo.listadoArticulos();
            Collections.reverse(nuevaLista);
            attributes.put("articulos",nuevaLista);
            attributes.put("usuarioLogueado", usuarioLogueado);
            attributes.put("estaLogueado", estaLogueado);

            response.redirect("/home");



            return new ModelAndView(null, "/crearUsuario.ftl");
        }, freeMarkerEngine );

//!/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        get("/home", (request,response)-> {

            Map<String, Object> attributes = new HashMap<>();
            List<Articulo> nuevaLista = Articulo.listadoArticulos();
            Collections.reverse(nuevaLista);
            attributes.put("articulos",nuevaLista);
            attributes.put("usuarioLogueado", usuarioLogueado);
            attributes.put("estaLogueado", estaLogueado);

            return new ModelAndView(attributes, "/index.ftl");
        }, freeMarkerEngine );

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        post("/home", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();
            boolean existe = false;
            List<Usuario> listadoUsuarioBD = Usuario.buscarListadoUsuarios();
            usuarioLogueado.setUsername(request.queryParams("User"));
            usuarioLogueado.setPassword(request.queryParams("Pass"));

            for(int i=0;i<listadoUsuarioBD.size();i++){
                if(usuarioLogueado.getUsername().equals(listadoUsuarioBD.get(i).getUsername()) && usuarioLogueado.getPassword().equals(listadoUsuarioBD.get(i).getPassword())){
                    existe=true;
                    if(existe){
                        estaLogueado=estaLogueado(true);
                        usuarioLogueado = listadoUsuarioBD.get(i);
                    }

                    //FALTA CREAR CONDICION SINO EXISTE*************************************

                }
            }
            List<Articulo> nuevaLista = Articulo.listadoArticulos();
            Collections.reverse(nuevaLista);
            attributes.put("articulos",nuevaLista);
            attributes.put("usuarioLogueado", usuarioLogueado);
            attributes.put("estaLogueado", estaLogueado);

            return new ModelAndView(attributes, "index.ftl");
        }, freeMarkerEngine);

///!/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //RUTA PARA CERRAR SESION
        get("/home/:cerrarSesion", (request,response)-> {

            Map<String, Object> attributes = new HashMap<>();
            estaLogueado=false;
            usuarioLogueado=new Usuario();


            response.redirect("/home");

            return new ModelAndView(null, "/index.ftl");
        }, freeMarkerEngine );

//!/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        get("/crearArticulo/:idUsuario", (request,response)-> {

            int usuarioId=Integer.parseInt(request.params("idUsuario"));
            Usuario usuarioLogueado = new Usuario();

            //OBTENGO QUE USUARIO CREARA EL ARTICULO
            for(int i=0;i<Usuario.buscarListadoUsuarios().size();i++){
                if(usuarioId == Usuario.buscarListadoUsuarios().get(i).getId()){
                    usuarioLogueado=Usuario.buscarListadoUsuarios().get(i);

                }
            }
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("usuario",usuarioLogueado);
            return new ModelAndView(attributes, "/crearArticulo.ftl");
        }, freeMarkerEngine );

//!////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //FILTRO PARA DETENER USUARIOS QUE NO TIENEN PERMISOS PARA CREAR USUARIO
        before("/crearArticulo/:idUsuario", (request, response) ->{
            int usuarioId=Integer.parseInt(request.params("idUsuario"));
            Usuario usuarioLogueado = new Usuario();

            for(int i=0;i<Usuario.buscarListadoUsuarios().size();i++){
                if(usuarioId == Usuario.buscarListadoUsuarios().get(i).getId()){
                    usuarioLogueado=Usuario.buscarListadoUsuarios().get(i);
                }
            }
            if(!(usuarioLogueado.isAdministrador() || usuarioLogueado.isAutor())){
                halt(401,"No tiene permisos para crear articulo!");
            }


        } );

//!/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        post("/crearArticulo/:idUsuario", (request,response)-> {

            Map<String, Object> attributes = new HashMap<>();
            Articulo nuevoArticulo = new Articulo();
            nuevoArticulo.setTitulo(request.queryParams("Titu"));
            nuevoArticulo.setCuerpo(request.queryParams("Cuer"));
            nuevoArticulo.setId(Articulo.ultimoIdArticulo() + 1);
            nuevoArticulo.setFecha(new Date().toString());


            int usuarioId=Integer.parseInt(request.params("idUsuario"));
            Usuario usuarioLogueado = new Usuario();
            for(int i=0;i<Usuario.buscarListadoUsuarios().size();i++){
                if(usuarioId == Usuario.buscarListadoUsuarios().get(i).getId()){
                    usuarioLogueado=Usuario.buscarListadoUsuarios().get(i);

                }
            }
            nuevoArticulo.setAutor(usuarioLogueado.getId());

            attributes.put("usuario",usuarioLogueado);
            Articulo.insertarArticulo(nuevoArticulo);

            response.redirect("/home");

            return new ModelAndView(attributes, "/crearArticulo.ftl");
        }, freeMarkerEngine );

//!/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        get("/administrarArticulo/:idArticulo", (request,response)-> {
            int articuloSeleccionId = Integer.parseInt(request.params("idArticulo"));
            Articulo articuloSeleccionado = new Articulo();


            //BUSCO EL ARTICULO QUE SELECCIONE
            for(int i=0;i<Articulo.listadoArticulos().size();i++){
                if(articuloSeleccionId == Articulo.listadoArticulos().get(i).getId()){
                    articuloSeleccionado=Articulo.listadoArticulos().get(i);

                }
            }

            Map<String, Object> attributes = new HashMap<>();

            attributes.put("estaLogueado", estaLogueado);
            attributes.put("usuarioLogueado", usuarioLogueado);
            attributes.put("articuloSeleccionado", articuloSeleccionado);
            attributes.put("comentarios", Comentario.buscarListadoComentariosArticulo(articuloSeleccionId));

            return new ModelAndView(attributes, "/administrarArticulo.ftl");
        }, freeMarkerEngine );

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        post("/crearComentario/:idArticulo", (request, response) -> {
            int idArticulo = Integer.parseInt(request.params("idArticulo"));
            Map<String, Object> attributes = new HashMap<>();
            Comentario nuevoComentario=new Comentario();

            nuevoComentario.setId(Comentario.ultimoIdComentario()+1);
            nuevoComentario.setComentario(request.queryParams("Coment"));
            nuevoComentario.setArticuloId(idArticulo);
            nuevoComentario.setAutorId(usuarioLogueado.getId());
            Comentario.insertarComentario(nuevoComentario);

            response.redirect("/home"); //ARREGLAR QUE AL CREAR MANDA A INDEX **********************************************

            return new ModelAndView(attributes, "/crearComentario.ftl");
        }, freeMarkerEngine);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        get("/eliminarArticulo/:idArticulo", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();
            int ArticuloId=Integer.parseInt(request.params("idArticulo"));

            Articulo.eliminarArticulo(ArticuloId);
            //Comentario.eliminarComentario(ComentarioId);
            response.redirect("/home"); //ARREGLAR QUE AL ELIMINAR MANDA A INDEX **********************************************

            return new ModelAndView(attributes, "/eliminarArticulo.ftl");
        }, freeMarkerEngine);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //FILTRO PARA DETENER USUARIO QUE NO SEA AUTOR PARA ELIMINAR O ADMINISTRADOR
        before("/eliminarArticulo/:idArticulo", (request, response) ->{
            int ArticuloId=Integer.parseInt(request.params("idArticulo"));
            Articulo articuloBuscado = new Articulo();

            for(int i=0;i<Articulo.listadoArticulos().size();i++){
                if(Articulo.listadoArticulos().get(i).getId() == ArticuloId){
                    articuloBuscado = Articulo.listadoArticulos().get(i);
                }

            }

            if(!(usuarioLogueado.getId()== articuloBuscado.getAutor() || usuarioLogueado.isAdministrador())){
                halt(401,"No tiene permisos para eliminar articulo!");
            }
        } );
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        get("/modificarArticulo/:idArticulo", (request, response) -> {
            int idArticulo = Integer.parseInt(request.params("idArticulo"));
            Map<String, Object> attributes = new HashMap<>();

            attributes.put("articulo",Articulo.listadoArticulosEspecifico(idArticulo));

            return new ModelAndView(attributes, "/modificarArticulo.ftl");
        }, freeMarkerEngine);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        post("/modificarArticulo/:idArticulo", (request, response) -> {


            int idArticulo = Integer.parseInt(request.params("idArticulo"));
            Map<String, Object> attributes = new HashMap<>();

            Articulo.modificarArticulo(idArticulo, request.queryMap().value("Titu"), request.queryMap().value("Cuer"));

            attributes.put("articulo",Articulo.listadoArticulosEspecifico(idArticulo));
            response.redirect("/home");

            return new ModelAndView(attributes, "/modificarArticulo.ftl");
        }, freeMarkerEngine);

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //FILTRO PARA DETENER USUARIO QUE NO SEA AUTOR PARA MODIFICAR O ADMINISTRADOR
        before("/modificarArticulo/:idArticulo", (request, response) ->{
            int ArticuloId=Integer.parseInt(request.params("idArticulo"));
            Articulo articuloBuscado = new Articulo();

            for(int i=0;i<Articulo.listadoArticulos().size();i++){
                if(Articulo.listadoArticulos().get(i).getId() == ArticuloId){
                    articuloBuscado = Articulo.listadoArticulos().get(i);
                }

            }

            if(!(usuarioLogueado.getId()== articuloBuscado.getAutor() || usuarioLogueado.isAdministrador())){
                halt(401,"No tiene permisos para modificar articulo!");
            }
        } );
    }
    private static boolean estaLogueado(boolean estaLogueado){
        return estaLogueado;
    }

    /*
    private static void crearUsuarioAdmin() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Gustavo");
        usuario.setUsername("admin");
        usuario.setPassword("admin");
        usuario.setAdministrador(true);
        usuario.setAutor(true);

        logger.info("Verificando si el usuario ya se introdujo. El usuario por defecto");
        Usuario.buscarListadoUsuarios().stream().forEach(usuario1 -> {
            if (usuario1.getId() != usuario.getId())
                Usuario.insertarUsuario(usuario);
        });
    }*/
}


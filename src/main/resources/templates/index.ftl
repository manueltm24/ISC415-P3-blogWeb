<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog Home</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/blog-home.css" rel="stylesheet">

    <!-- Temporary navbar container fix -->
    <style>
        .navbar-toggler {
            z-index: 1;
        }

        @media (max-width: 576px) {
            nav > .container {
                width: 100%;
            }
        }
    </style>

</head>

<body>

<!-- Navigation -->
<nav class="navbar fixed-top navbar-toggleable-md navbar-inverse bg-inverse">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarExample" aria-controls="navbarExample" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="container">
        <a class="navbar-brand" href="#"> <img width="75" height="75" src="/logopucmm.png"> Blog PUCMM Programacion Web 2017</a>
    </div>
</nav>

<!-- Page Content -->
<div class="container">

    <div class="row">
        <!-- Blog Entries Column -->
        <div class="col-md-8">
            <h1 class="my-4">&nbsp <small> </small></h1> <!-- ESPACIO VACIO -->
            <!-- Blog Post -->

        <#list articulos as articulo>
            <div class="card mb-4">
                <div class="card-block">
                    <h2 class="card-title">${articulo.getTitulo()}</h2>
                    <p class="card-text">${articulo.getResumen()}</p>
                    <a href="/administrarArticulo/${articulo.getId()}" class="btn btn-primary">Leer mas &rarr;</a>
                </div>
                <div class="card-footer text-muted">
                    ${articulo.getFecha()} <a href="#">${articulo.buscarNombreAutor(articulo.getAutor())}</a>
                </div>
            </div>
        </#list>

            <!-- Pagination -->
            <ul class="pagination justify-content-center mb-4">
                <li class="page-item"><a class="page-link" href="#">&larr; Older</a></li>
                <li class="page-item disabled"><a class="page-link" href="#">Newer &rarr;</a></li>
            </ul>

        </div>

        <!-- Sidebar Widgets Column -->
       <div class="col-md-3">

       <#if estaLogueado==false>
           <h1 class="my-4">&nbsp <small> </small></h1> <!-- ESPACIO VACIO -->
           <div class="card my-4">
               <h5 class="card-header">Iniciar sesion</h5>
               <div class="card-block">
                   <form action="/home" method="post" class="form-bottom">
                       <div class="form-group">
                           <input class="form-username form-control" type="text" name="User" value="" id="Username"
                                  placeholder="Username">
                       </div>
                       <div class="form-group">
                           <input class="form-username form-control" type="password" name="Pass" value="" id="Password"
                                  placeholder="Password">
                       </div>
                       <div class="input-group">
                           <button class="btn btn-primary" type="submit">Ingresar</button>

                       </div>
                   </form>
               </div>
           </div>
       </#if>

       <#if estaLogueado==true>
           <h1 class="my-4">&nbsp <small> </small></h1> <!-- ESPACIO VACIO -->
           <div class="card my-4">
               <h5 class="card-header">Bienvenido: </h5>
               <div class="card-block">
                   <div class="input-group">
                       <label>${usuarioLogueado.getNombre()}</label>
                   </div>
                   <div class="input-group">
                       <form method="get" action="/home/cerrarSesion">
                           <button class="btn btn-primary" type="submit">CERRAR SESION</button>
                       </form>
                   </div>
               </div>
           </div>
           <div class="card my-4">
               <div class="card-header">
                   <button type="button" class="btn btn-default">
                       <a href="/crearArticulo/${usuarioLogueado.getId()}">Crear Nuevo Articulo</a>
                   </button>
               </div>
               <div class="card-header">
                   <#if usuarioLogueado.getUsername() == "admin">
                       <button type="button" class="btn btn-default">
                           <a href="/crearUsuario/">Crear Nuevo Usuario</a>
                       </button>
                   </#if>
               </div>
           </div>
       </#if>
       </div>
    </div>
    <!-- /.row -->
</div>
<!-- /.container -->

<!-- Footer -->
<footer class="py-5 bg-inverse">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
    </div>
    <!-- /.container -->
</footer>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/tether/tether.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog Home - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/blog-home.css" rel="stylesheet">

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
            <!-- ARTICULO -->
            <div class="card mb-4">
                <div class="card-block">
                    <h2 class="card-title">${articuloSeleccionado.getTitulo()}</h2>
                    <p class="card-text">${articuloSeleccionado.getCuerpo()}</p>
                </div>
                <div class="card-footer text-muted">

                    <button type="button" class="btn btn-default">
                        <a href="/eliminarArticulo/${articuloSeleccionado.getId()}">Eliminar</a>
                    </button>
                    <button type="button" class="btn btn-default">
                        <a href="/modificarArticulo/${articuloSeleccionado.getId()}">Modificar</a>
                    </button>

                    ${articuloSeleccionado.getFecha()} by <a href="#">${articuloSeleccionado.buscarNombreAutor(articuloSeleccionado.getAutor())}</a>

                </div>
            </div>
            <div class="card mb-4">
                <div class="card-block">
                    <h2 class="card-title">Comentarios</h2>
                    <p class="card-text">


                    <#list listadoComentario as comentario>
                        <div class="card mb-4">
                            <div class="card-block">
                                <p class="card-text">${comentario.getComentario()}</p>
                            </div>
                        <!--LOGICA DE ELIMINAR-->
                        <!--
                        <div class="card-footer text-muted">Comentario hecho por: <a
                                href="#">${articuloSeleccionado.buscarNombreAutor(usuarioLogueado.getId())}</a>
                                <!--
                                <button type="button" class="btn btn-default">
                                    <a href="/eliminarComentario/${articuloSeleccionado.buscarNombreAutor(articuloSeleccionado.getAutor())}/${comentario.getId()}">Eliminar</a>
                                </button>
                                -->

                            </div>

            </div>
                        </#list>



                    </p>
        </div>
        <#if estaLogueado ==true>

        <div class="card mb-4">
            <!--<img class="card-img-top img-fluid" src="http://placehold.it/750x300" alt="Card image cap">-->
            <div class="card-block">
                <form method="post" action="/crearComentario/${articuloSeleccionado.getId()}">
                <h2 class="card-title">Nuevo comentario:</h2>
                <p class="card-text">
                    <input class="form-username form-control" style="width:500px;height:250px" type="text" name="Coment"
                           value="" id="Comentario" placeholder="Inserte aqui su comentario.!">

                </p>
                    <button class="btn btn-success" type="submit">Sumbit</button>
                </form>
            </div>
        </div>

        </#if>

            </div>
            <!-- COMENTARIOS -->

            <!-- Pagination -->
            <ul class="pagination justify-content-center mb-4">
                <li class="page-item"><a class="page-link" href="#">&larr; Older</a></li>
                <li class="page-item disabled"><a class="page-link" href="#">Newer &rarr;</a></li>
            </ul>

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
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/tether/tether.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>

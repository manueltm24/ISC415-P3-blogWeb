<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">


    <title>Blog Home</title>

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
        <a class="navbar-brand" href="/home"> <img width="75" height="75" src="/logopucmm.png"> Blog PUCMM Programacion Web 2017</a>
    </div>
</nav>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="my-4">&nbsp <small> </small></h1> <!-- ESPACIO VACIO -->

            <!-- Blog Post -->

            <div class="card mb-4">
                <!--<img class="card-img-top img-fluid" src="http://placehold.it/750x300" alt="Card image cap">-->
                <div class="card-block">
                    <h2 class="card-title">${articulo.getTitulo()}</h2>
                    <p class="card-text">${articulo.getCuerpo()}</p>

                </div>
                <div class="card-footer text-muted">
                    Posted on January 1, 2017 by <a href="#">${articulo.buscarNombreAutor(articulo.getAutor())}</a>
                </div>
            </div>
            <div class="card mb-4">
                <!--<img class="card-img-top img-fluid" src="http://placehold.it/750x300" alt="Card image cap">-->
                <div class="card-block">
                    <h2 class="card-title">Comentarios</h2>
                    <p class="card-text">

                        <!--AQUI VA LA LISTA DE COMENTARIOS -->
        <#list comentarios as comentario>
            <div class="card mb-4">
                <!--<img class="card-img-top img-fluid" src="http://placehold.it/750x300" alt="Card image cap">-->
                <div class="card-block">
                    <h2 class="card-title">${articuloSeleccionado.getTitulo()}</h2>
            <p class="card-text">${articulo.getCuerpo()}</p>
            <a href="/administrarArticulo/${articulo.getId()}" class="btn btn-primary">Read More &rarr;</a>
        </div>
            <div class="card-footer text-muted">
            ${articulo.getFecha()} <a href="#">${articulo.buscarNombreAutor(articulo.getAutor())}</a>
            </div>
        </div>
        </#list>

                    </p>
                </div>
            </div>

            <div class="card mb-4">
                <!--<img class="card-img-top img-fluid" src="http://placehold.it/750x300" alt="Card image cap">-->
                <div class="card-block">
                    <h2 class="card-title">Nuevo comentario:</h2>
                    <p class="card-text">
                        <input style="width:500px;height:250px" type="text" name="Comentario" value="" id="Comentario" placeholder="Inserte aqui su comentario.!">

                    </p>
                    <button class="btn btn-success" type="submit" >Summit</button>
                </div>
            </div>

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

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
                <div class="card-block">
                    <h2 class="card-title">Nuevo Articulo</h2>
                    <p class="card-text">
                    <form action="/modificarArticulo/${articulo.getId()}" method="post">
                        <div class="form-group">
                            <label for="Titulo">Titulo&nbsp&nbsp&nbsp</label>
                            <input style="width:500px" type="text" name="Titu" id="Titulo" value="${articulo.getTitulo()}"><br>
                        </div>

                        <div class="form-group">
                            <label for="Cuerpo">Cuerpo</label>
                            <input class="form-username form-control" style="width:500px;height:250px" type="text"
                                   name="Cuer" id="Cuerpo" value="${articulo.getCuerpo()}"><br>
                        </div>

                        <!-- todo Aqui falta las etiquetas -->

                        <button class="btn btn-success" type="submit" >Guardar</button>
                    </form>
                    </p>

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

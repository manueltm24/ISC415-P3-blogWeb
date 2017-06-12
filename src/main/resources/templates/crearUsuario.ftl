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

            <h1 class="my-4">&nbsp</small></h1>

            <!-- Blog Post -->
            <div class="card mb-4">
                <div class="card-block">
                    <h2 class="card-title">Nuevo Usuario</h2>
                    <form action="/crearUsuario/" method="post" >
                        <p class="card-text">
                            <label for="Nombre">Nombre: </label>
                            <input class="form-username form-control" type="text" name="Nom" id="Nombre"
                                   placeholder="Nombre del usuario" required><br>
                            <label for="Username">Username: </label>
                            <input class="form-username form-control" type="text" name="User" id="Username"
                                   placeholder="Nombre de usuario" required><br>
                            <label for="Password">Password: </label>
                            <input class="form-username form-control" type="password" name="Pass" id="Password"
                                   placeholder="Contrasena del usuario" required><br>
                            <label for="Administrador">Administrador: </label>
                            <input type="checkbox" name="Adm" value="true">
                            <input type="hidden" name="Adm" value="false">
                            <label for="Autor">Autor: </label>
                            <input type="checkbox" name="Aut" value="true">
                            <input type="hidden" name="Aut" value="false">
                        </p>
                        <button class="btn btn-primary" type="submit">Guardar</button>
                        <button class="btn btn-danger" type="button" onclick="location.href = '/home';">Cancelar
                        </button>
                    </form>
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
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var elements = document.getElementsByTagName("INPUT");
        for (var i = 0; i < elements.length; i++) {
            elements[i].oninvalid = function (e) {
                e.target.setCustomValidity("");
                if (!e.target.validity.valid) {
                    e.target.setCustomValidity("Campo no puede estar vacio.");
                }
            };
            elements[i].oninput = function (e) {
                e.target.setCustomValidity("");
            };
        }
    })
</script>
</html>

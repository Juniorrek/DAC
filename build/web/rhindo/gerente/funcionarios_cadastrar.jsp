<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Cadastrar funcionário</title>

    <%@ include file="../../links_css.html" %>

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/DAC/rhindo/pagina_inicial.jsp">RH-INDO</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <!-- /.dropdown -->
                <!-- user inf -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="../meus_dados.jsp"><i class="fa fa-user fa-fw"></i> Meus dados</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="/DAC/rhindo/login.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="/DAC/rhindo/pagina_inicial.jsp"><i class="fa fa-home fa-fw"></i> Página inicial</a>
                        </li>
                        <%
                            out.println("<li><a href=\"funcionarios.jsp\" class\"active\"><i class=\"fa fa-users fa-fw\"></i> Funcionários</a></li>");
                            out.println("<li><a href=\"departamentos.jsp\"><i class=\"fa fa-building fa-fw\"></i> Departamentos</a></li>");
                            out.println("<li><a href=\"cargos.jsp\"><i class=\"fa fa-briefcase fa-fw\"></i> Cargos</a></li>");
                            out.println("<li><a href=\"fechamento_folha.jsp\"><i class=\"fa fa-book fa-fw\"></i> Fechamento da folha</a></li>");
                            out.println("<li><a href=\"relatorios.jsp\"><i class=\"fa fa-file-text fa-fw\"></i> Relatórios</a></li>");
                        %>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Cadastrar funcionário</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Formulário de cadastro
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <form>
                                <fieldset>
                                    <legend>Dados pessoais</legend>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group">
                                                <label>Nome:</label>
                                                <input class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>CPF:</label>
                                                <input class="form-control">
                                                <p class="help-block">Somente números.</p>
                                            </div>
                                        </div>
                                        <!-- /.col-lg-6 (nested) -->
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>RG:</label>
                                                <input class="form-control">
                                                <p class="help-block">Somente números.</p>
                                            </div>
                                        </div>
                                        <!-- /.col-lg-6 (nested) -->
                                        <div class="col-lg-4">
                                            <div class="form-group">
                                                <label>Celular:</label>
                                                <input class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-lg-4">
                                            <div class="form-group">
                                                <label>Email:</label>
                                                <input class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-lg-4">
                                            <div class="form-group">
                                                <label>Perfil:</label>
                                                <select class="form-control">
                                                    <option>Gerente de RH</option>
                                                    <option>Gerente de Departamento</option>
                                                    <option>Funcionário</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.row (nested) -->
                                </fieldset>
                                <fieldset>
                                    <legend>Endereço</legend>
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label>CEP:</label>
                                                <input class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Rua:</label>
                                                <input class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label>Número:</label>
                                                <input class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-lg-5">
                                            <div class="form-group">
                                                <label>Bairro:</label>
                                                <input class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-lg-5">
                                            <div class="form-group">
                                                <label>Cidade:</label>
                                                <input class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-lg-2">
                                            <div class="form-group">
                                                <label>Estado:</label>
                                                <select class="form-control">
                                                    <option>AC</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.row (nested) -->
                                </fieldset>
                                <div style="text-align: center;">
                                    <a href="funcionarios.jsp" type="submit" class="btn btn-danger">Cancelar</a>
                                    <button type="submit" class="btn btn-success">Cadastrar</button>
                                </div>
                            </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../../vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../../vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../../vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../../dist/js/sb-admin-2.js"></script>
</body>

</html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="Ana Paula Santos, Bianca Santos, David Junior">

    <title>Página Inicial</title>

    <%@ include file="links_css.html" %>

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
                <div class="logo">
                    <img src="../vendor/bootstrap/img/login_atoa.ico">
                </div>
                <a class="navbar-brand" href="pagina_inicial.jsp">AT-OA</a>
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
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> Meus dados</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
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
                            <a href="pagina_inicial.jsp" class="active"><i class="fa fa-home fa-fw"></i> Página Inicial</a>
                        </li>
                        <%
                            //String perfil = (String)session.getAttribute("perfil");
                            //if(perfil.equals("gerente")) {
                                out.println("<li><a href=\"gerente/atividades.jsp\"><i class=\"fa fa-users fa-fw\"></i> Atividades</a></li>");
                                out.println("<li><a href=\"gerente/fechar_atividade.jsp\"><i class=\"fa fa-building fa-fw\"></i> Fechamento de atividades</a></li>");
                                out.println("<li><a href=\"gerente/relatorios.jsp\"><i class=\"fa fa-file-text fa-fw\"></i> Relatórios</a></li>");
                            //} else {
                                out.println("<li><a href=\"funcionario/atividades.jsp\"><i class=\"fa fa-clock-o fa-fw\"></i> Atividades</a></li>");
                                out.println("<li><a href=\"funcionario/lista_atividades.jsp\"><i class=\"fa fa-list-alt fa-fw\"></i> Lista de atividades</a></li>");
                            //}
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
                    <h2 class="page-header">Sistema de ATi​ vidades O​rdenadamente A​lcançáveis</h2>
                    <p class="lead">O Sistema de Atividades obtém a lista de funcionários do departamento através de solicitação ao Sistema de RH.</p>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <%@ include file="scripts_js.html" %>

</body>

</html>
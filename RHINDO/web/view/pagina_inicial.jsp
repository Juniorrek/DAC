<%@page import="model.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Página inicial</title>

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
                <a class="navbar-brand" href="/RHINDO/view/pagina_inicial.jsp">RH-INDO</a>
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
                        <li><a href="/RHINDO/Login?action=formUpdate"><i class="fa fa-user fa-fw"></i> Meus dados</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="/RHINDO/view/login.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
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
                            <a href="/RHINDO/view/pagina_inicial.jsp" class="active"><i class="fa fa-home fa-fw"></i> Página inicial</a>
                        </li>
                        <%
                            Funcionario logado = (Funcionario)session.getAttribute("logado");
                            if("Gerente de RH".equals(logado.getPerfil())) {
                        %>
                                <li><a href="/RHINDO/Funcionarios?action=select"><i class="fa fa-users fa-fw"></i> Funcionários</a></li>
                                <li><a href="/RHINDO/Departamentos?action=select"><i class="fa fa-building fa-fw"></i> Departamentos</a></li>
                                <li><a href="/RHINDO/Cargos?action=select"><i class="fa fa-briefcase fa-fw"></i> Cargos</a></li>
                                <li><a href="/RHINDO/view/gerente/fechamento_folha.jsp"><i class="fa fa-book fa-fw"></i> Fechamento da folha</a></li>
                                <li><a href="/RHINDO/view/gerente/relatorios.jsp"><i class="fa fa-file-text fa-fw"></i> Relatórios</a></li>
                        <%
                            } else { 
                        %>
                                <li><a href="/RHINDO/view/funcionario/horas_trabalhadas.jsp"><i class="fa fa-clock-o fa-fw"></i> Horas trabalhas</a></li>
                                <li><a href="/RHINDO/view/funcionario/holerite.jsp"><i class="fa fa-list-alt fa-fw"></i> Holerite</a></li>
                        <%
                            }
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
                    <h2 class="page-header">Sistema de RH IN​contestavelmente D​esenhado para O​rganização</h2>
                    <p class="lead">O Sistema de RH obtém as horas trabalhadas funcionário através de solicitação ao Sistema de Atividades.</p>
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
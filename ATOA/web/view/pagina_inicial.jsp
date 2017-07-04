<%@page import="model.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <c:if test="${empty logado}">
        <c:redirect url ="/view/login.jsp"/>
    </c:if>
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
                <a class="navbar-brand" href="/ATOA/view/pagina_inicial.jsp">AT-OA</a>
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
                        <li><a href="/ATOA/Login?action=editar"><i class="fa fa-user fa-fw"></i> Meus dados</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="/ATOA/Login?action=logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
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
                            <a href="/ATOA/view/pagina_inicial.jsp" class="active"><i class="fa fa-home fa-fw"></i> Página Inicial</a>
                        </li>
                        <%
                            Funcionario logado = (Funcionario)session.getAttribute("logado");
                            if("Gerente de Departamento".equals(logado.getPerfil())) {
                        %>
                                <li><a href="/ATOA/Tipos?action=carregar"><i class="fa fa-users fa-fw"></i> Tipos de atividades</a></li>
                                <li><a href="/ATOA/Atividades?action=carregarCorrecoes"><i class="fa fa-check fa-fw"></i> Aprovar correções</a></li>
                                <li><a href="/ATOA/Atividades?action=Formfechar"><i class="fa fa-building fa-fw"></i> Fechamento de atividades</a></li>
                                <li><a href="/ATOA/Relatorios?action=form"><i class="fa fa-file-text fa-fw"></i> Relatórios</a></li>
                        <%
                            } else { 
                        %>
                                <li><a href="/ATOA/Atividades?action=carregar"><i class="fa fa-clock-o fa-fw"></i> Atividades</a></li>
                                <li><a href="/ATOA/Atividades?action=carregarMes"><i class="fa fa-list-alt fa-fw"></i> Atividades do mês</a></li>
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
                    <h2 class="page-header">Sistema de ATi​ vidades O​rdenadamente A​lcançáveis</h2>
                    <p class="lead">O Sistema de Atividades obtém a lista de funcionários do departamento através de solicitação ao Sistema de RH.</p>
                    <c:if test="${not empty notificacao}">
                        <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">NOTIFICAÇÃO</h4>
                                    </div>
                                    <div class="modal-body">
                                        O departamento tem atividades para serem fechadas !!!
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
                                    </div>
                                </div>
                                <!-- /.modal-content -->
                            </div>
                            <!-- /.modal-dialog -->
                        </div>
                    </c:if>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <%@ include file="scripts_js.html" %>
    <c:if test="${not empty notificacao && logado.perfil == 'Gerente de Departamento'}">
        <script>
            $("#modal").modal("show");
        </script>
    </c:if>
</body>

</html>
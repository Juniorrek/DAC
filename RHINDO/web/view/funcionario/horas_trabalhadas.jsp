<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Horas trabalhadas</title>

    <%@ include file="../links_css.html" %>

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
                        <li><a href="/RHINDO/Login?action=editar"><i class="fa fa-user fa-fw"></i> Meus dados</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="/RHINDO/Login?action=logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
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
                            <a href="/RHINDO/view/pagina_inicial.jsp"><i class="fa fa-home fa-fw"></i> Página inicial</a>
                        </li>
                        <li><a href="/RHINDO/view/funcionario/horas_trabalhadas.jsp"><i class="fa fa-clock-o fa-fw"></i> Horas trabalhas</a></li>
                                <li><a href="/RHINDO/Holerite?action=obter"><i class="fa fa-list-alt fa-fw"></i> Holerite</a></li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Horas Trabalhadas</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <form action="/RHINDO/Folha?action=horas_trabalhadas" method="post" target="_blank">
                                    <div class="col-lg-2">
                                        <div class="form-group ${erroDe ? 'has-error' : ''}">
                                            <label class="control-label">${erroAte ? 'De é obrigatório:' : 'De:'}</label>
                                            <input type="date" name="de">
                                        </div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="form-group ${erroAte ? 'has-error' : ''}">
                                            <label class="control-label">${erroAte ? 'Até é obrigatório:' : 'Até:'}</label>
                                            <input type="date" name="ate">
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <label style="display:none;">Tipo de relatório:</label><br>
                                            <button type="submit" class="btn btn-info">Gerar</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- /.row (nested) -->
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

    <%@ include file="../scripts_js.html" %>

</body>

</html>
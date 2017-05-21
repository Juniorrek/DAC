<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Cargos</title>

    <!-- DataTables CSS -->
    <link href="/RHINDO/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="/RHINDO/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

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
                        <li><a href="/RHINDO/view/pagina_inicial.jsp"><i class="fa fa-home fa-fw"></i> Página inicial</a></li>
                        <li><a href="/RHINDO/Funcionarios?action=select"><i class="fa fa-users fa-fw"></i> Funcionários</a></li>
                        <li><a href="/RHINDO/Departamentos?action=select"><i class="fa fa-building fa-fw"></i> Departamentos</a></li>
                        <li><a href="/RHINDO/Cargos?action=select" class="active"><i class="fa fa-briefcase fa-fw"></i> Cargos</a></li>
                        <li><a href="/RHINDO/view/gerente/fechamento_folha.jsp"><i class="fa fa-book fa-fw"></i> Fechamento da folha</a></li>
                        <li><a href="/RHINDO/view/gerente/relatorios.jsp"><i class="fa fa-file-text fa-fw"></i> Relatórios</a></li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
        <c:choose>
            <c:when test = "${param.action == 'select'}">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Cargos</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Cargos cadastrados no sistema
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <a type="button" href="/RHINDO/Cargos?action=formInsert" class="btn btn-success" style="margin-bottom: 10px;">Cadastrar novo</a>
                                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Nome</th>
                                            <th>Salário</th>
                                            <th>Requisitos</th>
                                            <th>Carga de trabalho min./mês</th>
                                            <th>% desconto</th>
                                            <th>Ação</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${cargos}" var="cargo">
                                            <tr>
                                                <td>${cargo.nome}</td>
                                                <td>${cargo.salario}</td>
                                                <td>${cargo.requisitos}</td>
                                                <td>${cargo.imposto_desconto}</td>
                                                <td>${cargo.carga_trabalho_minima_mes}</td>
                                                <td><a href="/RHINDO/Cargos?action=formUpdate&id=${cargo.id}" type="button" class="btn btn-info">Editar</a>
                                                    <button type="button" class="btn btn-danger"  onclick="excluir(${cargo.id})" style="margin-left: 10px;" data-toggle="modal" data-target="#myModal">Exluir</button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <!-- /.table-responsive -->
                                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <h4 class="modal-title" id="myModalLabel">EXCLUSÃO</h4>
                                            </div>
                                            <div class="modal-body">
                                                Tem certeza que deseja excluir?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                                <a href="" type="button" class="btn btn-danger" id="excluir">Excluir</a>
                                            </div>
                                        </div>
                                        <!-- /.modal-content -->
                                    </div>
                                    <!-- /.modal-dialog -->
                                </div>
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
            </c:when>
            <c:when test = "${param.action == 'formInsert'}">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Cadastrar cargo</h1>
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
                                <form action ="/RHINDO/Cargos?action=insert" method="POST">
                                    <div class="row">
                                        <div class="col-lg-4">
                                            <div class="form-group">
                                                <label>Nome:</label>
                                                <input class="form-control" name="nome">
                                            </div>
                                        </div>
                                        <div class="col-lg-2">
                                            <div class="form-group">
                                                <label>Salário:</label>
                                                <input class="form-control" name="salario">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Requisitos:</label>
                                                <textarea class="form-control" name="requisitos"></textarea>
                                            </div>
                                        </div>
                                        <div class="col-lg-4">
                                            <div class="form-group">
                                                <label>Carga de trabalho min./mês</label>
                                                <input class="form-control" name="carga_trabalho_minima_mes">
                                            </div>
                                        </div>
                                        <div class="col-lg-2">
                                            <div class="form-group">
                                                <label>% desconto:</label>
                                                <input class="form-control" name="imposto_desconto">
                                            </div>
                                        </div>
                                        <!-- /.col-lg-6 (nested) -->
                                    </div>
                                    <!-- /.row (nested) -->
                                    <div style="text-align: center;">
                                        <a href="/RHINDO/Cargos?action=select" type="submit" class="btn btn-danger">Cancelar</a>
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
            </c:when>
            <c:when test = "${param.action == 'formUpdate'}">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Editar cargo</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Formulário de edição
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <form action="/RHINDO/Cargos?action=update" method="POST">
                                    <div class="row">
                                        <div class="col-lg-4" style="display: none;">
                                            <div class="form-group">
                                                <label>Id:</label>
                                                <input class="form-control" value="${cargo.getId()}" name="id">
                                            </div>
                                        </div>
                                        <div class="col-lg-4">
                                            <div class="form-group">
                                                <label>Nome:</label>
                                                <input class="form-control" value="${cargo.getNome()}" name="nome">
                                            </div>
                                        </div>
                                        <div class="col-lg-2">
                                            <div class="form-group">
                                                <label>Salário:</label>
                                                <input class="form-control" value="${cargo.getSalario()}" name="salario">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Requisitos:</label>
                                                <textarea class="form-control" name="requisitos">${cargo.getRequisitos()}</textarea>
                                            </div>
                                        </div>
                                        <div class="col-lg-4">
                                            <div class="form-group">
                                                <label>Carga de trabalho min./mês</label>
                                                <input class="form-control" value="${cargo.getCarga_trabalho_minima_mes()}" name="carga_trabalho_minima_mes">
                                            </div>
                                        </div>
                                        <div class="col-lg-2">
                                            <div class="form-group">
                                                <label>% desconto:</label>
                                                <input class="form-control" value="${cargo.getImposto_desconto()}" name="imposto_desconto">
                                            </div>
                                        </div>
                                        <!-- /.col-lg-6 (nested) -->
                                    </div>
                                    <!-- /.row (nested) -->
                                    <div style="text-align: center;">
                                        <a href="/RHINDO/Cargos?action=select" type="submit" class="btn btn-danger">Cancelar</a>
                                        <button type="submit" class="btn btn-success">Editar</button>
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
            </c:when>
        </c:choose>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <%@ include file="../scripts_js.html" %>
    
    <!-- DataTables JavaScript -->
    <script src="/RHINDO/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="/RHINDO/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="/RHINDO/vendor/datatables-responsive/dataTables.responsive.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        function excluir(id) {
            $("#excluir").attr("href", "/RHINDO/Cargos?action=delete&id=" + id);
        }
        
        $(document).ready(function() {
            $('#dataTables-example').DataTable({
                responsive: true,
                language: idiomabr
            });
        });
        var idiomabr = {
            "sEmptyTable": "Nenhum registro encontrado",
            "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
            "sInfoFiltered": "(Filtrados de _MAX_ registros)",
            "sInfoPostFix": "",
            "sInfoThousands": ".",
            "sLengthMenu": "_MENU_ resultados por página",
            "sLoadingRecords": "Carregando...",
            "sProcessing": "Processando...",
            "sZeroRecords": "Nenhum registro encontrado",
            "sSearch": "Pesquisar",
            "oPaginate": {
                "sNext": "Próximo",
                "sPrevious": "Anterior",
                "sFirst": "Primeiro",
                "sLast": "Último"
            },
            "oAria": {
                "sSortAscending": ": Ordenar colunas de forma ascendente",
                "sSortDescending": ": Ordenar colunas de forma descendente"
            }
        };
    </script>
</body>

</html>
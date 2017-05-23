<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Atividades</title>

    <!-- DataTables CSS -->
    <link href="/ATOA/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="/ATOA/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

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
                        <li><a href="/ATOA/Login?action=formUpdate"><i class="fa fa-user fa-fw"></i> Meus dados</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="/ATOA/view/login.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <%@ include file="side_bar.jsp" %>
        </nav>

        <div id="page-wrapper">
        <c:choose>
            <c:when test = "${param.action == 'select'}">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Atividades</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Atividades cadastradas no sistema
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <a href="/ATOA/Atividades?action=formInsert" type="button" class="btn btn-success" style="margin-bottom: 10px;">Cadastrar nova</a>
                                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Nome</th>
                                            <th>Descrição</th>
                                            <th>Inicio</th>
                                            <th>Fim</th>
                                            <th>Funcionário</th>
                                            <th>Ação</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${atividades}" var="atividade">
                                            <tr>
                                                <td>${atividade.nome}</td>
                                                <td>${atividade.descricao}</td>
                                                <td>${atividade.inicio}</td>
                                                <td>${atividade.fim}</td>
                                                <td>${atividade.funcionario.nome}</td>
                                                <td><a href="/ATOA/Atividades?action=formUpdate&id=${atividade.id}" type="button" class="btn btn-info">Editar</a>
                                                    <button type="button" class="btn btn-danger"  onclick="excluir(${atividade.id})" style="margin-left: 10px;" data-toggle="modal" data-target="#myModal">Exluir</button>
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
                        <h1 class="page-header">Cadastrar atividade</h1>
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
                                <form action ="/ATOA/Atividades?action=insert" method="POST">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Nome:</label>
                                                <input class="form-control" name="nome">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Descrição:</label>
                                                <input class="form-control" name="descricao">
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.row (nested) -->
                                    <div style="text-align: center;">
                                        <a href="/ATOA/Atividades?action=select" type="submit" class="btn btn-danger">Cancelar</a>
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
                        <h1 class="page-header">Editar atividade</h1>
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
                                <form action="/ATOA/Atividades?action=update" method="POST">
                                    <div class="row">
                                        <div class="col-lg-6" style="display: none;">
                                            <div class="form-group">
                                                <label>Id:</label>
                                                <input class="form-control" value="${atividade.getId()}" name="id">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Nome:</label>
                                                <input class="form-control" value="${atividade.getNome()}" name="nome">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Descrição:</label>
                                                <input class="form-control" value="${atividade.getDescricao()}" name="descricao">
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.row (nested) -->
                                    <div style="text-align: center;">
                                        <a href="/ATOA/Atividades?action=select" type="submit" class="btn btn-danger">Cancelar</a>
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
    <script src="/ATOA/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="/ATOA/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="/ATOA/vendor/datatables-responsive/dataTables.responsive.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        function excluir(id) {
            $("#excluir").attr("href", "/ATOA/Atividades?action=delete&id=" + id);
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
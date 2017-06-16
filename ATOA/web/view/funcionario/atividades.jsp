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
                        <li><a href="/ATOA/Login?action=editar"><i class="fa fa-user fa-fw"></i> Meus dados</a>
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

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li><a href="/ATOA/view/pagina_inicial.jsp"><i class="fa fa-home fa-fw"></i> Página Inicial</a></li>
                        <li><a href="/ATOA/Atividades?action=carregar"><i class="fa fa-clock-o fa-fw"></i> Atividades</a></li>
                                <li><a href="/ATOA/view/funcionario/lista_atividades.jsp"><i class="fa fa-list-alt fa-fw"></i> Lista de atividades</a></li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
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
                                Atividades
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <button type="button" class="btn btn-success" style="margin-bottom: 10px;" data-toggle="modal" data-target="#modalCriar" id="iniciar">Iniciar tarefa</button>
                                <div class="modal fade" id="modalCriar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <h4 class="modal-title" id="myModalLabel">Formulário</h4>
                                            </div>
                                            <form action ="/ATOA/Atividades?action=iniciar" method="POST">
                                                <div class="modal-body">
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
                                                    <div class="row">
                                                        <div class="col-lg-4">
                                                            <div class="form-group">
                                                                <label>Tipo:</label>
                                                                <select class="form-control" name="tipo">
                                                                <c:forEach items="${tipos}" var="tipo">
                                                                    <option value="${tipo.id}">${tipo.nome}</option>
                                                                </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <div class="form-group">
                                                                <label>Início:</label>
                                                                <input type="date" class="form-control" name="descricao">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <div class="form-group">
                                                                <label>Fim:</label>
                                                                <input type="date" class="form-control" name="nome">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                                    <button type="submit" class="btn btn-success">Cadastrar</button>
                                                </div>
                                            </form>
                                        </div>
                                        <!-- /.modal-content -->
                                    </div>
                                    <!-- /.modal-dialog -->
                                </div>
                                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Nome</th>
                                            <th>Descrição</th>
                                            <th>Início</th>
                                            <th>Fim</th>
                                            <th>Ação</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${atividades}" var="atividade">
                                            <tr>
                                                <td style="display: none;">${atividade.id}</td>
                                                <td>${atividade.nome}</td>
                                                <td>${atividade.descricao}</td>
                                                <td>${atividade.tipo.nome}</td>
                                                <td>${atividade.inicio}</td>
                                                <td>${atividade.fim}</td>
                                                <td>${atividade.status}</td>
                                                <td><button type="button" class="btn btn-info" onclick="editar(${tipo.id})" style="margin-left: 10px;" data-toggle="modal" data-target="#modalEditar">Editar</button>
                                                    <button type="button" class="btn btn-danger"  onclick="deletar(${tipo.id})" style="margin-left: 10px;" data-toggle="modal" data-target="#modalDeletar">Deletar</button>
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
        /*function excluir(id) {
            $("#excluir").attr("href", "/ATOA/Atividades?action=delete&id=" + id);
        }*/
        $('#iniciar').one("click", function() {
            console.log("aa");
        });
        
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
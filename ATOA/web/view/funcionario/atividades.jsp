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
                        <li><a href="/ATOA/view/pagina_inicial.jsp"><i class="fa fa-home fa-fw"></i> Página Inicial</a></li>
                        <li><a href="/ATOA/Atividades?action=carregar"><i class="fa fa-clock-o fa-fw"></i> Atividades</a></li>
                        <li><a href="/ATOA/Atividades?action=carregarMes"><i class="fa fa-list-alt fa-fw"></i> Atividades do mês</a></li>
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
                                <button type="button" class="btn btn-success" style="margin-bottom: 10px;" data-toggle="modal" data-target="#modalCriar" id="iniciar">Iniciar atividade</button>
                                <div class="modal fade" id="modalCriar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <h4 class="modal-title" id="myModalLabel">Formulário</h4>
                                            </div>
                                            <form action ="/ATOA/Atividades?action=criar" method="POST">
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
                                                                <label>Tipo:</label>
                                                                <select class="form-control" name="tipo">
                                                                <c:forEach items="${tipos}" var="tipo">
                                                                    <option value="${tipo.id}">${tipo.nome}</option>
                                                                </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="form-group">
                                                                <label>Descrição:</label>
                                                                <input class="form-control" name="descricao">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                                    <button type="submit" class="btn btn-success">Iniciar</button>
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
                                            <th style="display: none;">ID</th>
                                            <th>Nome</th>
                                            <th>Descrição</th>
                                            <th>Tipo</th>
                                            <th>Início</th>
                                            <th>Fim</th>
                                            <th>Status</th>
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
                                                <td>
                                                    <c:if test = "${empty atividade.fim || atividade.status == 'FINALIZADA'}">
                                                        <button type="button" class="btn btn-info" id="corrigir" style="margin-left: 10px;" data-toggle="modal" data-target="#modalCorrigir">Corrigir</button>
                                                    </c:if>
                                                    <c:if test = "${empty atividade.fim}">
                                                        <button type="button" class="btn btn-danger"  onclick="finalizar(${atividade.id})" style="margin-left: 10px;" data-toggle="modal" data-target="#modalFinalizar">Finalizar</button>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <!-- /.table-responsive -->
                                <div class="modal fade" id="modalCorrigir" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <h4 class="modal-title" id="myModalLabel">Formulário de correção</h4>
                                            </div>
                                            <h4 style="margin-left: 15px;">Sobre a correção:</h4>
                                            <p style="margin-left: 15px;">Será feito um pedido de correção que deverá ser aprovado pelo gerente do departamento.</p>
                                            <form action ="/ATOA/Atividades?action=solicitarCorrigir" method="POST">
                                                <div class="modal-body">
                                                    <div class="row">
                                                        <div class="col-lg-6">
                                                            <input type="hidden" class="form-control" name="id" >
                                                            <div class="form-group">
                                                                <label>Nome:</label>
                                                                <input class="form-control" name="nome">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>Tipo:</label>
                                                                <select class="form-control" name="tipo">
                                                                <c:forEach items="${tipos}" var="tipo">
                                                                    <option value="${tipo.id}">${tipo.nome}</option>
                                                                </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="form-group">
                                                                <label>Descrição:</label>
                                                                <input class="form-control" name="descricao">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>Início:</label>
                                                                <input type="datetime-local" class="form-control" name="inicio">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>Fim:</label>
                                                                <input type="datetime-local" class="form-control" name="fim">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>Status:</label>
                                                                <select class="form-control" name="status">
                                                                    <option value="EM ANDAMENTO">EM ANDAMENTO</option>
                                                                    <option value="FINALIZADA">FINALIZADA</option>
                                                                    <option value="PENDENTE" disabled>PENDENTE</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                                    <button type="submit" class="btn btn-success">Solicitar correção</button>
                                                </div>
                                            </form>
                                        </div>
                                        <!-- /.modal-content -->
                                    </div>
                                    <!-- /.modal-dialog -->
                                </div>
                                <div class="modal fade" id="modalFinalizar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <h4 class="modal-title" id="myModalLabel">FINALIZAR</h4>
                                            </div>
                                            <div class="modal-body">
                                                Tem certeza que deseja finalizar?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                                <a href="" type="button" class="btn btn-danger" id="finalizar">Finalizar</a>
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
        function finalizar(id) {
            $("#finalizar").attr("href", "/ATOA/Atividades?action=finalizar&id=" + id);
        }
        
        var table;
        $(document).ready(function() {
            table = $('#dataTables-example').DataTable({
                responsive: true,
                language: idiomabr,
                aaSorting: [[0,'desc']]
            });
        });
        $("#dataTables-example tbody").on( 'click', 'button[id="corrigir"]', function () {
            //THANKS FOR THE LORD https://stackoverflow.com/a/38515622/8173621
            var current_row = $(this).parents('tr');
            if (current_row.hasClass('child')) {//Check if the current row is a child row
                current_row = current_row.prev();//If it is, then point to the row before it (its 'parent')
            }
            var data = table.row(current_row).data();//At this point, current_row refers to a valid row in the table, whether is a child row (collapsed by the DataTable's responsiveness) or a 'normal' row
            $("#modalCorrigir input[name='id']").val(data[0]);
            $("#modalCorrigir input[name='nome']").val(data[1]);
            $("#modalCorrigir input[name='descricao']").val(data[2]);
            var val = $("#modalCorrigir select[name='tipo']").find("option:contains("+data[3]+")").val();
            $("#modalCorrigir select[name='tipo']").val(val);
            $("#modalCorrigir input[name='inicio']").val(data[4].replace(" ", "T").substr(0, 16));
            if (data[5] !== "") {
                $("#modalCorrigir input[name='fim']").prop('disabled', false);
                $("#modalCorrigir input[name='fim']").val(data[5].replace(" ", "T").substr(0, 16));
            } else {
                $("#modalCorrigir input[name='fim']").val("");
                $("#modalCorrigir input[name='fim']").prop('disabled', true);
            }
            val = $("#modalCorrigir select[name='status']").find("option:contains("+data[6]+")").val();
            $("#modalCorrigir select[name='status']").val(val);
        } );
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
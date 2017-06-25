<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Corrigir atividades</title>

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

            <%@ include file="side_bar.jsp" %>
        </nav>

        <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Correção de atividades aguardando aprovação</h1>
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
                                            <th>Funcionario</th>
                                            <th style="display: none;">ID2</th>
                                            <th style="display: none;">Nome2</th>
                                            <th style="display: none;">Descrição2</th>
                                            <th style="display: none;">Tipo2</th>
                                            <th style="display: none;">Início2</th>
                                            <th style="display: none;">Fim2</th>
                                            <th style="display: none;">Funcionario2</th>
                                            <th style="display: none;">Status2</th>
                                            <th>Ação</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${correcoes}" var="correcao">
                                            <tr>
                                                <td style="display: none;">${correcao.antes.id}</td>
                                                <td>${correcao.antes.nome}</td>
                                                <td>${correcao.antes.descricao}</td>
                                                <td>${correcao.antes.tipo.nome}</td>
                                                <td>${correcao.antes.inicio}</td>
                                                <td>${correcao.antes.fim}</td>
                                                <td>${correcao.antes.funcionario.nome}</td>
                                                <td>${correcao.antes.status}</td>
                                                <td style="display: none;">${correcao.depois.id}</td>
                                                <td style="display: none;">${correcao.depois.nome}</td>
                                                <td style="display: none;">${correcao.depois.descricao}</td>
                                                <td style="display: none;">${correcao.depois.tipo.nome}</td>
                                                <td style="display: none;">${correcao.depois.inicio}</td>
                                                <td style="display: none;">${correcao.depois.fim}</td>
                                                <td style="display: none;">${correcao.depois.funcionario.nome}</td>
                                                <td style="display: none;">${correcao.depois.status}</td>
                                                <td><button type="button" class="btn btn-info" id="vizualizar" style="margin-left: 10px;" data-toggle="modal" data-target="#modalVizualizar">Vizualizar</button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <!-- /.table-responsive -->
                                <div class="modal fade" id="modalVizualizar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <h4 class="modal-title" id="myModalLabel">Formulário de correção</h4>
                                            </div>
                                                <div class="modal-body">
                                                    <h1>ANTES</h1>
                                                    <div class="row">
                                                        <div class="col-lg-6">
                                                            <input type="hidden" class="form-control" name="id1" >
                                                            <div class="form-group">
                                                                <label>Nome:</label>
                                                                <input class="form-control" name="nome1" disabled>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>Tipo:</label>
                                                                <select class="form-control" name="tipo1" disabled>
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
                                                                <input class="form-control" name="descricao1" disabled>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>Início:</label>
                                                                <input type="datetime-local" class="form-control" name="inicio1" disabled>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>Fim:</label>
                                                                <input type="datetime-local" class="form-control" name="fim1" disabled>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>Status:</label>
                                                                <select class="form-control" name="status1" disabled>
                                                                    <option value="EM ANDAMENTO">EM ANDAMENTO</option>
                                                                    <option value="FINALIZADA">FINALIZADA</option>
                                                                    <option value="PENDENTE">PENDENTE</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <h1>DEPOIS</h1>
                                                    <div class="row">
                                                        <div class="col-lg-6">
                                                            <input type="hidden" class="form-control" name="id2">
                                                            <div class="form-group">
                                                                <label>Nome:</label>
                                                                <input class="form-control" name="nome2" disabled>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>Tipo:</label>
                                                                <select class="form-control" name="tipo2" disabled>
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
                                                                <input class="form-control" name="descricao2" disabled>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>Início:</label>
                                                                <input type="datetime-local" class="form-control" name="inicio2" disabled>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>Fim:</label>
                                                                <input type="datetime-local" class="form-control" name="fim2" disabled>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>Status:</label>
                                                                <select class="form-control" name="status2" disabled>
                                                                    <option value="EM ANDAMENTO">EM ANDAMENTO</option>
                                                                    <option value="FINALIZADA">FINALIZADA</option>
                                                                    <option value="PENDENTE">PENDENTE</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
                                                    <a href="" type="button" class="btn btn-danger" id="reprovar">Reprovar</a>
                                                    <a href="" type="button" class="btn btn-success" id="aprovar">Aprovar</a>
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
        var table;
        $(document).ready(function() {
            table = $('#dataTables-example').DataTable({
                responsive: true,
                language: idiomabr,
                aaSorting: [[0,'desc']]
            });
        });
        $("#dataTables-example tbody").on( 'click', 'button[id="vizualizar"]', function () {
            //THANKS FOR THE LORD https://stackoverflow.com/a/38515622/8173621
            var current_row = $(this).parents('tr');
            if (current_row.hasClass('child')) {//Check if the current row is a child row
                current_row = current_row.prev();//If it is, then point to the row before it (its 'parent')
            }
            var data = table.row(current_row).data();//At this point, current_row refers to a valid row in the table, whether is a child row (collapsed by the DataTable's responsiveness) or a 'normal' row
            $("#modalVizualizar input[name='id1']").val(data[0]);
            $("#modalVizualizar input[name='nome1']").val(data[1]);
            $("#modalVizualizar input[name='descricao1']").val(data[2]);
            var val = $("#modalVizualizar select[name='tipo1']").find("option:contains("+data[3]+")").val();
            $("#modalVizualizar select[name='tipo1']").val(val);
            $("#modalVizualizar input[name='inicio1']").val(data[4].replace(" ", "T").substr(0, 16));
            if (data[5] !== "") {
                $("#modalVizualizar input[name='fim1']").prop('disabled', true);
                $("#modalVizualizar input[name='fim1']").val(data[5].replace(" ", "T").substr(0, 16));
            } else {
                $("#modalVizualizar input[name='fim1']").val("");
                $("#modalVizualizar input[name='fim1']").prop('disabled', true);
            }
            val = $("#modalVizualizar select[name='status1']").find("option:contains("+data[7]+")").val();
            $("#modalVizualizar select[name='status1']").val(val);
            
            $("#modalVizualizar input[name='id2']").val(data[8]);
            $("#modalVizualizar input[name='nome2']").val(data[9]);
            $("#modalVizualizar input[name='descricao2']").val(data[10]);
            var val = $("#modalVizualizar select[name='tipo2']").find("option:contains("+data[11]+")").val();
            $("#modalVizualizar select[name='tipo2']").val(val);
            $("#modalVizualizar input[name='inicio2']").val(data[12].replace(" ", "T").substr(0, 16));
            if (data[13] !== "") {
                $("#modalVizualizar input[name='fim2']").prop('disabled', true);
                $("#modalVizualizar input[name='fim2']").val(data[13].replace(" ", "T").substr(0, 16));
            } else {
                $("#modalVizualizar input[name='fim2']").val("");
                $("#modalVizualizar input[name='fim2']").prop('disabled', true);
            }
            val = $("#modalVizualizar select[name='status2']").find("option:contains("+data[15]+")").val();
            $("#modalVizualizar select[name='status2']").val(val);
            
            $("#reprovar").attr("href", "/ATOA/Atividades?action=reprovar&id=" + data[0]);
            $("#aprovar").attr("href", "/ATOA/Atividades?action=aprovar&id=" + data[0]);
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
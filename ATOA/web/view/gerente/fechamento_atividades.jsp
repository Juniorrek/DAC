<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Fechamento de atividades</title>
    
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
                    <h1 class="page-header">Fechamento de atividades</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-4">
                                    <div class="form-group">
                                        <label>Por/funcionários:</label>
                                        <label class="radio-inline">
                                            <input type="radio" name="opt" id="optionsRadiosInline1" value="1" checked="">Todos
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="opt" id="optionsRadiosInline2" value="2">Especifico
                                        </label>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div id="opt1" class="desc">
                                        <div class="form-group">
                                            <form action="/ATOA/Atividades?action=fechar" method="post">
                                                <a href="/ATOA/Atividades?action=fechar" type="button" class="btn btn-info">Fechar</a>
                                            </form>
                                        </div>
                                    </div>
                                    <div id="opt2" class="desc form-group" hidden>
                                        <form action="/ATOA/Atividades?action=fechar" method="post">
                                            <div class="form-group">
                                                <button type="submit" class="btn btn-info">Fechar</button>
                                            </div>
                                            <select id="funcs" class="form-control" name="especifico">
                                            <c:forEach items="${funcionarios}" var="funcionario">
                                                <option value="${funcionario.cpf}">${funcionario.nome}</option>
                                            </c:forEach>
                                            </select>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- /.row (nested) -->
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Atividades
                                </div>
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
                                            <th>Funcionário</th>
                                            <th>Status</th>
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
                                            <td>${atividade.funcionario.nome}</td>
                                            <td>${atividade.status}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                </div>
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
    <script src="/ATOA/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="/ATOA/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="/ATOA/vendor/datatables-responsive/dataTables.responsive.js"></script>
    <script>
        var table;
        $(document).ready(function() {
            table = $('#dataTables-example').DataTable({
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
        
        $("input[name='opt']").click(function() {
            var test = $(this).val();

            $("div.desc").hide();
            $("#opt" + test).show();

            if (test === '2') {
                table.column(6).search( $("#funcs option:selected").text() ).draw();
            } else {
                $("input[class='form-control input-sm']").val("");
                table.column(6).search("").draw();
            }
        });
        
        $("#funcs").change(function() {
            table.column(6).search( $("#funcs option:selected").text() ).draw();
        });
    </script>
</body>

</html>
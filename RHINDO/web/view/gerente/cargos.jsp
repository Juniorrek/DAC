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
    <c:if test="${empty logado}">
        <c:redirect url ="/view/login.jsp"/>
    </c:if>
    <c:if test="${logado.perfil != 'Gerente de RH'}">
        <c:redirect url ="/view/pagina_inicial.jsp"/>
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
                        <li><a href="/RHINDO/view/pagina_inicial.jsp"><i class="fa fa-home fa-fw"></i> Página inicial</a></li>
                        <li><a href="/RHINDO/Funcionarios?action=carregar"><i class="fa fa-users fa-fw"></i> Funcionários</a></li>
                        <li><a href="/RHINDO/Departamentos?action=carregar"><i class="fa fa-building fa-fw"></i> Departamentos</a></li>
                        <li><a href="/RHINDO/Cargos?action=carregar" class="active"><i class="fa fa-briefcase fa-fw"></i> Cargos</a></li>
                        <li><a href="/RHINDO/view/gerente/fechamento_folha.jsp"><i class="fa fa-book fa-fw"></i> Fechamento da folha</a></li>
                        <li><a href="/RHINDO/view/gerente/relatorios.jsp"><i class="fa fa-file-text fa-fw"></i> Relatórios</a></li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
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
                            <button type="button" class="btn btn-success" style="margin-bottom: 10px;" data-toggle="modal" data-target="#modalCriar">Cadastrar novo</button>
                            <div class="modal fade" id="modalCriar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel">Formulário de cadastro</h4>
                                        </div>
                                        <form action ="/RHINDO/Cargos?action=criar" method="POST" id="formCriar">
                                            <div class="modal-body">
                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="form-group ${erroNome ? 'has-error' : ''}">
                                                            <label class="control-label">${erroNome ? 'Nome é obrigatório:' : 'Nome:'}</label>
                                                            <input class="form-control" name="nome">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group ${erroSalario ? 'has-error' : ''}">
                                                            <label class="control-label">${erroSalario ? 'Salário é obrigatório:' : 'Salário:'}</label>
                                                            <input class="form-control salario" name="salario">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label>Requisitos:</label>
                                                            <textarea class="form-control" name="requisitos"></textarea>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <div class="form-group ${erroCarga_trabalho_minima_mes ? 'has-error' : ''}">
                                                            <label class="control-label">${erroCarga_trabalho_minima_mes ? 'Carga de trabalho min./mês é obrigatória:' : 'Carga de trabalho min./mês:'}</label>
                                                            <input class="form-control horas" name="carga_trabalho_minima_mes">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2">
                                                        <div class="form-group ${erroDesconto_impostos_gerais ? 'has-error' : ''}">
                                                            <label class="control-label">${erroDesconto_impostos_gerais ? '% desconto é obrigatório:' : '% desconto:'}</label>
                                                            <input class="form-control desconto" name="desconto_impostos_gerais">
                                                        </div>
                                                    </div>
                                                    <!-- /.col-lg-6 (nested) -->
                                                </div>
                                                <!-- /.row (nested) -->
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
                                        <th style="display: none;">ID</th>
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
                                            <td style="display: none;">${cargo.id}</td>
                                            <td>${cargo.nome}</td>
                                            <td class="tdsal">R$ ${cargo.salario}</td>
                                            <td>${cargo.requisitos}</td>
                                            <td class="tdhor">${cargo.carga_trabalho_minima_mes} hrs</td>
                                            <td class="tddes">${cargo.desconto_impostos_gerais} %</td>
                                            <td>
                                                <button type="button" class="btn btn-info" id="editar" style="margin-left: 10px;" data-toggle="modal" data-target="#modalEditar">Editar</button>
                                                <button type="button" class="btn btn-danger"  onclick="deletar(${cargo.id})" style="margin-left: 10px;" data-toggle="modal" data-target="#modalDeletar">Deletar</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                            <div class="modal fade" id="modalEditar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel">Formulário de edição</h4>
                                        </div>
                                        <form action ="/RHINDO/Cargos?action=editar" method="POST" id="formEditar">
                                            <div class="modal-body">
                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <input type="hidden" class="form-control" name="id" >
                                                        <div class="form-group ${erroNome2 ? 'has-error' : ''}">
                                                            <label class="control-label">${erroNome2 ? 'Nome é obrigatório:' : 'Nome:'}</label>
                                                            <input class="form-control" name="nome">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group ${erroSalario2 ? 'has-error' : ''}">
                                                            <label class="control-label">${erroSalario2 ? 'Salário é obrigatório:' : 'Salário:'}</label>
                                                            <input class="form-control salario" name="salario">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label>Requisitos:</label>
                                                            <textarea class="form-control" name="requisitos"></textarea>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <div class="form-group ${erroCarga_trabalho_minima_mes2 ? 'has-error' : ''}">
                                                            <label class="control-label">${erroCarga_trabalho_minima_mes2 ? 'Carga de trabalho min./mês é obrigatória:' : 'Carga de trabalho min./mês'}</label>
                                                            <input class="form-control horas" name="carga_trabalho_minima_mes">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2">
                                                        <div class="form-group ${erroDesconto_impostos_gerais2 ? 'has-error' : ''}">
                                                            <label class="control-label">${erroDesconto_impostos_gerais2 ? '% desconto é obrigatório:' : '% desconto:'}</label>
                                                            <input class="form-control desconto" name="desconto_impostos_gerais">
                                                        </div>
                                                    </div>
                                                    <!-- /.col-lg-6 (nested) -->
                                                </div>
                                                <!-- /.row (nested) -->
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                                <button type="submit" class="btn btn-success">Editar</button>
                                            </div>
                                        </form>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
                            <div class="modal fade" id="modalDeletar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel">EXCLUSÃO</h4>
                                        </div>
                                        <div class="modal-body">
                                            Tem certeza que deseja deletar?
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                            <a href="" type="button" class="btn btn-danger" id="deletar">Deletar</a>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
                            <div class="modal fade" id="modalMsg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel">MENSAGEM</h4>
                                        </div>
                                        <div class="modal-body">
                                            ${msg}
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
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
    <script src="/RHINDO/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="/RHINDO/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="/RHINDO/vendor/datatables-responsive/dataTables.responsive.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        function deletar(id) {
            $("#deletar").attr("href", "/RHINDO/Cargos?action=deletar&id=" + id);
        }
        
        var table;
        $(document).ready(function() {
            table = $('#dataTables-example').DataTable({
                responsive: true,
                language: idiomabr
            });
        });
        $("#dataTables-example tbody").on( 'click', 'button[id="editar"]', function () {
            //THANKS FOR THE LORD https://stackoverflow.com/a/38515622/8173621
            var current_row = $(this).parents('tr');
            if (current_row.hasClass('child')) {//Check if the current row is a child row
                current_row = current_row.prev();//If it is, then point to the row before it (its 'parent')
            }
            var data = table.row(current_row).data();//At this point, current_row refers to a valid row in the table, whether is a child row (collapsed by the DataTable's responsiveness) or a 'normal' row
            $("#modalEditar input[name='id']").val(data[0]);
            $("#modalEditar input[name='nome']").val(data[1]);
            $("#modalEditar input[name='salario']").val(data[2]);
            $("#modalEditar textarea[name='requisitos']").val(data[3]);
            $("#modalEditar input[name='carga_trabalho_minima_mes']").val(data[4]);
            $("#modalEditar input[name='desconto_impostos_gerais']").val(data[5]);
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
    
    <script src="/RHINDO/jquery.maskMoney.min.js"></script>
    <script src="/RHINDO/jquery.maskedinput.min.js"></script>
    <script src="/RHINDO/jquery-validation-1.16.0/dist/jquery.validate.min.js"></script>
    <script>
        $(".salario").maskMoney({"prefix" : "R$ ", "decimal" : ",", "thousands" : "."});
        $(".desconto").mask("9?99 %");
        $(".horas").mask("9?9999999999999 hr(s)", { placeholder: "" });
        $("#formCriar").validate({
            rules: {
                nome: "required",
                salario: "required",
                carga_trabalho_minima_mes: "required",
                desconto_impostos_gerais: "required"
            },
            messages: {
                nome: "Digite o nome !!!",
                salario: "Digite o salário !!!",
                carga_trabalho_minima_mes: "Digite a carga !!!",
                desconto_impostos_gerais: "Digite o desconto !!!"
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
        $("#formEditar").validate({
            rules: {
                nome: "required",
                salario: "required",
                carga_trabalho_minima_mes: "required",
                desconto_impostos_gerais: "required"
            },
            messages: {
                nome: "Digite o nome !!!",
                salario: "Digite o salário !!!",
                carga_trabalho_minima_mes: "Digite a carga !!!",
                desconto_impostos_gerais: "Digite o desconto !!!"
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
    </script>
    <script>
        $("#dataTables-example tbody td").each(function() {
           if ($(this).attr('class') === "tdsal") {
               $(this).html($(this).html().replace(".", ","));
               
               //if ($(this).html().substr())
               var a = $(this).html();
               b = $(this).html().substr(a.length - 3, a.length);
               if (b[1] === ",") {
                   $(this).html($(this).html().concat("0"));
               }
           }
        });
    </script>
    <c:if test="${erroNome || erroSalario || erroCarga_trabalho_minima_mes || erroDesconto_impostos_gerais}">
        <script>$("#modalCriar").modal('show');</script>
    </c:if>
    <c:if test="${not empty msg}">
        <script>$("#modalMsg").modal('show');</script>
    </c:if>
</body>

</html>
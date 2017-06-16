<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<head>

    <meta charset="iso-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Funcionários</title>

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
                        <li><a href="/RHINDO/Login?action=editar"><i class="fa fa-user fa-fw"></i> Meus dados</a>
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
                        <li><a href="/RHINDO/Funcionarios?action=carregar" class="active"><i class="fa fa-users fa-fw"></i> Funcionários</a></li>
                        <li><a href="/RHINDO/Departamentos?action=carregar"><i class="fa fa-building fa-fw"></i> Departamentos</a></li>
                        <li><a href="/RHINDO/Cargos?action=carregar"><i class="fa fa-briefcase fa-fw"></i> Cargos</a></li>
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
                    <h1 class="page-header">Funcionários</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Funcionários cadastrados no sistema
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
                                        <form action ="/RHINDO/Funcionarios?action=criar" method="POST">
                                            <div class="modal-body">
                                                <fieldset>
                                                    <legend>Dados pessoais</legend>
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="form-group">
                                                                <label>Nome:</label>
                                                                <input class="form-control" name="nome">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>CPF:</label>
                                                                <input class="form-control" name="cpf">
                                                                <p class="help-block">Somente números.</p>
                                                            </div>
                                                        </div>
                                                        <!-- /.col-lg-6 (nested) -->
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>RG:</label>
                                                                <input class="form-control" name="rg">
                                                                <p class="help-block">Somente números.</p>
                                                            </div>
                                                        </div>
                                                        <!-- /.col-lg-6 (nested) -->
                                                        <div class="col-lg-5">
                                                            <div class="form-group">
                                                                <label>Celular:</label>
                                                                <input class="form-control" name="celular">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-7">
                                                            <div class="form-group">
                                                                <label>Email:</label>
                                                                <input class="form-control" name="email">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- /.row (nested) -->
                                                </fieldset>
                                                <fieldset>
                                                    <legend>Endereço</legend>
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <div class="form-group">
                                                                <label>CEP:</label>
                                                                <input class="form-control" name="cep">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>Rua:</label>
                                                                <input class="form-control" name="rua">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <div class="form-group">
                                                                <label>Número:</label>
                                                                <input class="form-control" name="numero">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-5">
                                                            <div class="form-group">
                                                                <label>Bairro:</label>
                                                                <input class="form-control" name="bairro">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-5">
                                                            <div class="form-group">
                                                                <label>Cidade:</label>
                                                                <input class="form-control" name="cidade">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2">
                                                            <div class="form-group">
                                                                <label>Estado:</label>
                                                                <select class="form-control" name="estado">
                                                                    <option value="AC">AC</option>
                                                                    <option value="AL">AL</option>
                                                                    <option value="AP">AP</option>
                                                                    <option value="AM">AM</option>
                                                                    <option value="BA">BA</option>
                                                                    <option value="CE">CE</option>
                                                                    <option value="DF">DF</option>
                                                                    <option value="ES">ES</option>
                                                                    <option value="GO">GO</option>
                                                                    <option value="MA">MA</option>
                                                                    <option value="MT">MT</option>
                                                                    <option value="MS">MS</option>
                                                                    <option value="MG">MG</option>
                                                                    <option value="PA">PA</option>
                                                                    <option value="PB">PB</option>
                                                                    <option value="PR">PR</option>
                                                                    <option value="PE">PE</option>
                                                                    <option value="PI">PI</option>
                                                                    <option value="RJ">RJ</option>
                                                                    <option value="RN">RN</option>
                                                                    <option value="RS">RS</option>
                                                                    <option value="RO">RO</option>
                                                                    <option value="RR">RR</option>
                                                                    <option value="SC">SC</option>
                                                                    <option value="SP">SP</option>
                                                                    <option value="SE">SE</option>
                                                                    <option value="TO">TO</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- /.row (nested) -->
                                                </fieldset>
                                                <fieldset>
                                                    <legend>Dados profissionais</legend>
                                                    <div class="row">
                                                        <div class="col-lg-4">
                                                            <div class="form-group">
                                                                <label>Departamento:</label>
                                                                <select class="form-control" name="departamento">
                                                                <c:forEach items="${departamentos}" var="departamento">
                                                                    <option value="${departamento.id}">${departamento.nome}</option>
                                                                </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <div class="form-group">
                                                                <label>Cargo:</label>
                                                                <select class="form-control" name="cargo">
                                                                <c:forEach items="${cargos}" var="cargo">
                                                                    <option value="${cargo.id}">${cargo.nome}</option>
                                                                </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <div class="form-group">
                                                                <label>Perfil:</label>
                                                                <select class="form-control" name="perfil">
                                                                    <option value="Gerente de RH">Gerente de RH</option>
                                                                    <option value="Funcionário">Funcionário</option>
                                                                    <option value="Gerente de Departamento">Gerente de Departamento</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- /.row (nested) -->
                                                </fieldset>
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
                                        <th>CPF</th>
                                        <th>RG</th>
                                        <th>Celular</th>
                                        <th>Email</th>
                                        <th>Rua</th>
                                        <th>Nº</th>
                                        <th>Bairro</th>
                                        <th>CEP</th>
                                        <th>Cidade</th>
                                        <th>Estado</th>
                                        <th>Perfil</th>
                                        <th>Departamento</th>
                                        <th>Cargo</th>
                                        <th>Ação</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${funcionarios}" var="funcionario">
                                    <tr>
                                        <td>${funcionario.nome}</td>
                                        <td>${funcionario.cpf}</td>
                                        <td>${funcionario.rg}</td>
                                        <td>${funcionario.celular}</td>
                                        <td>${funcionario.email}</td>
                                        <td>${funcionario.rua}</td>
                                        <td>${funcionario.numero}</td>
                                        <td>${funcionario.bairro}</td>
                                        <td>${funcionario.cep}</td>
                                        <td>${funcionario.cidade}</td>
                                        <td>${funcionario.estado}</td>
                                        <td>${funcionario.perfil}</td>
                                        <td>${funcionario.departamento.nome}</td>
                                        <td>${funcionario.cargo.nome}</td>
                                        <td>
                                            <button type="button" class="btn btn-info" id="editar" style="margin-left: 10px;" data-toggle="modal" data-target="#modalEditar">Editar</button>
                                            <button type="button" class="btn btn-danger"  onclick="deletar('${funcionario.cpf}')" style="margin-left: 10px;" data-toggle="modal" data-target="#modalDeletar">Deletar</button>
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
                                        <form action ="/RHINDO/Funcionarios?action=editar" method="POST">
                                            <div class="modal-body">
                                                <fieldset>
                                                    <legend>Dados pessoais</legend>
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="form-group">
                                                                <label>Nome:</label>
                                                                <input class="form-control" name="nome">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>CPF:</label>
                                                                <input class="form-control" name="cpf">
                                                                <p class="help-block">Somente números.</p>
                                                            </div>
                                                        </div>
                                                        <!-- /.col-lg-6 (nested) -->
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>RG:</label>
                                                                <input class="form-control" name="rg">
                                                                <p class="help-block">Somente números.</p>
                                                            </div>
                                                        </div>
                                                        <!-- /.col-lg-6 (nested) -->
                                                        <div class="col-lg-5">
                                                            <div class="form-group">
                                                                <label>Celular:</label>
                                                                <input class="form-control" name="celular">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-7">
                                                            <div class="form-group">
                                                                <label>Email:</label>
                                                                <input class="form-control" name="email">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- /.row (nested) -->
                                                </fieldset>
                                                <fieldset>
                                                    <legend>Endereço</legend>
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <div class="form-group">
                                                                <label>CEP:</label>
                                                                <input class="form-control" name="cep">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <label>Rua:</label>
                                                                <input class="form-control" name="rua">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <div class="form-group">
                                                                <label>Número:</label>
                                                                <input class="form-control" name="numero">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-5">
                                                            <div class="form-group">
                                                                <label>Bairro:</label>
                                                                <input class="form-control" name="bairro">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-5">
                                                            <div class="form-group">
                                                                <label>Cidade:</label>
                                                                <input class="form-control" name="cidade">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2">
                                                            <div class="form-group">
                                                                <label>Estado:</label>
                                                                <select class="form-control" name="estado">
                                                                    <option value="AC">AC</option>
                                                                    <option value="AL">AL</option>
                                                                    <option value="AP">AP</option>
                                                                    <option value="AM">AM</option>
                                                                    <option value="BA">BA</option>
                                                                    <option value="CE">CE</option>
                                                                    <option value="DF">DF</option>
                                                                    <option value="ES">ES</option>
                                                                    <option value="GO">GO</option>
                                                                    <option value="MA">MA</option>
                                                                    <option value="MT">MT</option>
                                                                    <option value="MS">MS</option>
                                                                    <option value="MG">MG</option>
                                                                    <option value="PA">PA</option>
                                                                    <option value="PB">PB</option>
                                                                    <option value="PR">PR</option>
                                                                    <option value="PE">PE</option>
                                                                    <option value="PI">PI</option>
                                                                    <option value="RJ">RJ</option>
                                                                    <option value="RN">RN</option>
                                                                    <option value="RS">RS</option>
                                                                    <option value="RO">RO</option>
                                                                    <option value="RR">RR</option>
                                                                    <option value="SC">SC</option>
                                                                    <option value="SP">SP</option>
                                                                    <option value="SE">SE</option>
                                                                    <option value="TO">TO</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- /.row (nested) -->
                                                </fieldset>
                                                <fieldset>
                                                    <legend>Dados profissionais</legend>
                                                    <div class="row">
                                                        <div class="col-lg-4">
                                                            <div class="form-group">
                                                                <label>Departamento:</label>
                                                                <select class="form-control" name="departamento">
                                                                <c:forEach items="${departamentos}" var="departamento">
                                                                    <option value="${departamento.id}">${departamento.nome}</option>
                                                                </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <div class="form-group">
                                                                <label>Cargo:</label>
                                                                <select class="form-control" name="cargo">
                                                                <c:forEach items="${cargos}" var="cargo">
                                                                    <option value="${cargo.id}">${cargo.nome}</option>
                                                                </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <div class="form-group">
                                                                <label>Perfil:</label>
                                                                <select class="form-control" name="perfil">
                                                                    <option value="Gerente de RH">Gerente de RH</option>
                                                                    <option value="Funcionário">Funcionário</option>
                                                                    <option value="Gerente de Departamento">Gerente de Departamento</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- /.row (nested) -->
                                                </fieldset>
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
        function deletar(cpf) {
            $("#deletar").attr("href", "/RHINDO/Funcionarios?action=deletar&cpf=" + cpf);
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
            $("#modalEditar input[name='nome']").val(data[0]);
            $("#modalEditar input[name='cpf']").val(data[1]);
            $("#modalEditar input[name='rg']").val(data[2]);
            $("#modalEditar input[name='celular']").val(data[3]);
            $("#modalEditar input[name='email']").val(data[4]);
            $("#modalEditar input[name='rua']").val(data[5]);
            $("#modalEditar input[name='numero']").val(data[6]);
            $("#modalEditar input[name='bairro']").val(data[7]);
            $("#modalEditar input[name='cep']").val(data[8]);
            $("#modalEditar input[name='cidade']").val(data[9]);
            $("#modalEditar select[name='estado']").val(data[10]);
            $("#modalEditar select[name='perfil']").val(data[11]);
            var val = $("#modalEditar select[name='departamento']").find("option:contains("+data[12]+")").val();
            $("#modalEditar select[name='departamento']").val(val);
            var val = $("#modalEditar select[name='cargo']").find("option:contains("+data[13]+")").val();
            $("#modalEditar select[name='cargo']").val(val);
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
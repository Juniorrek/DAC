<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Funcionários</title>

    <!-- DataTables CSS -->
    <link href="../../vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="../../vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

    <%@ include file="../../links_css.html" %>
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
                <a class="navbar-brand" href="/DAC/rhindo/pagina_inicial.jsp">RH-INDO</a>
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
                        <li><a href="../meus_dados.jsp"><i class="fa fa-user fa-fw"></i> Meus dados</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="/DAC/rhindo/login.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
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
                            <a href="/DAC/rhindo/pagina_inicial.jsp"><i class="fa fa-home fa-fw"></i> Página inicial</a>
                        </li>
                        <%
                            out.println("<li><a href=\"funcionarios.jsp\" class\"active\"><i class=\"fa fa-users fa-fw\"></i> Funcionários</a></li>");
                            out.println("<li><a href=\"departamentos.jsp\"><i class=\"fa fa-building fa-fw\"></i> Departamentos</a></li>");
                            out.println("<li><a href=\"cargos.jsp\"><i class=\"fa fa-briefcase fa-fw\"></i> Cargos</a></li>");
                            out.println("<li><a href=\"fechamento_folha.jsp\"><i class=\"fa fa-book fa-fw\"></i> Fechamento da folha</a></li>");
                            out.println("<li><a href=\"relatorios.jsp\"><i class=\"fa fa-file-text fa-fw\"></i> Relatórios</a></li>");
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
                            <a type="button" href="funcionarios_cadastrar.jsp" class="btn btn-success" style="margin-bottom: 10px;">Cadastrar novo***</a>
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
                                        <th>Ação</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>João Silva</td>
                                        <td>123.456.123.12</td>
                                        <td>12.642-32</td>
                                        <td>9824-1435</td>
                                        <td>joaos@gmail.com</td>
                                        <td>Rua 3</td>
                                        <td>12</td>
                                        <td>Guarapari</td>
                                        <td>81.316-247</td>
                                        <td>Estiva</td>
                                        <td>PR</td>
                                        <td>Gerente de RH</td>
                                        <td><a href="funcionarios_editar.jsp" type="button" class="btn btn-info">Editar</a><button type="button" class="btn btn-danger" style="margin-left: 10px;">Exluir</button></td>
                                    </tr>
                                    <tr>
                                        <td>Pedro Silva</td>
                                        <td>123.456.123.12</td>
                                        <td>12.642-32</td>
                                        <td>9824-1435</td>
                                        <td>joaos@gmail.com</td>
                                        <td>Rua 3</td>
                                        <td>12</td>
                                        <td>Guarapari</td>
                                        <td>81.316-247</td>
                                        <td>Estiva</td>
                                        <td>PR</td>
                                        <td>Gerente de RH</td>
                                        <td><a href="funcionarios_editar.jsp" type="button" class="btn btn-info">Editar</a><button type="button" class="btn btn-danger" style="margin-left: 10px;">Exluir</button></td>
                                    </tr>
                                    <tr>
                                        <td>Alberto Silva</td>
                                        <td>123.456.123.12</td>
                                        <td>12.642-32</td>
                                        <td>9824-1435</td>
                                        <td>joaos@gmail.com</td>
                                        <td>Rua 3</td>
                                        <td>12</td>
                                        <td>Guarapari</td>
                                        <td>81.316-247</td>
                                        <td>Estiva</td>
                                        <td>PR</td>
                                        <td>Gerente de Departamento</td>
                                        <td><a href="funcionarios_editar.jsp" type="button" class="btn btn-info">Editar</a><button type="button" class="btn btn-danger" style="margin-left: 10px;">Exluir</button></td>
                                    </tr>
                                    <tr>
                                        <td>Silvia Silva</td>
                                        <td>123.456.123.12</td>
                                        <td>12.642-32</td>
                                        <td>9824-1435</td>
                                        <td>joaos@gmail.com</td>
                                        <td>Rua 3</td>
                                        <td>12</td>
                                        <td>Guarapari</td>
                                        <td>81.316-247</td>
                                        <td>Estiva</td>
                                        <td>PR</td>
                                        <td>Funcionário</td>
                                        <td><a href="funcionarios_editar.jsp" type="button" class="btn btn-info">Editar</a><button type="button" class="btn btn-danger" style="margin-left: 10px;">Exluir</button></td>
                                    </tr>
                                    <tr>
                                        <td>Carla Silva</td>
                                        <td>123.456.123.12</td>
                                        <td>12.642-32</td>
                                        <td>9824-1435</td>
                                        <td>joaos@gmail.com</td>
                                        <td>Rua 3</td>
                                        <td>12</td>
                                        <td>Guarapari</td>
                                        <td>81.316-247</td>
                                        <td>Estiva</td>
                                        <td>PR</td>
                                        <td>Funcionário</td>
                                        <td><a href="funcionarios_editar.jsp" type="button" class="btn btn-info">Editar</a><button type="button" class="btn btn-danger" style="margin-left: 10px;">Exluir</button></td>
                                    </tr>
                                    <tr>
                                        <td>Requião Silva</td>
                                        <td>123.456.123.12</td>
                                        <td>12.642-32</td>
                                        <td>9824-1435</td>
                                        <td>joaos@gmail.com</td>
                                        <td>Rua 3</td>
                                        <td>12</td>
                                        <td>Guarapari</td>
                                        <td>81.316-247</td>
                                        <td>Estiva</td>
                                        <td>PR</td>
                                        <td>Gerente de RH</td>
                                        <td><a href="funcionarios_editar.jsp" type="button" class="btn btn-info">Editar</a><button type="button" class="btn btn-danger" style="margin-left: 10px;">Exluir</button></td>
                                    </tr>
                                    <tr>
                                        <td>Bella Silva</td>
                                        <td>123.456.123.12</td>
                                        <td>12.642-32</td>
                                        <td>9824-1435</td>
                                        <td>joaos@gmail.com</td>
                                        <td>Rua 3</td>
                                        <td>12</td>
                                        <td>Guarapari</td>
                                        <td>81.316-247</td>
                                        <td>Estiva</td>
                                        <td>PR</td>
                                        <td>Funcionário</td>
                                        <td><a href="funcionarios_editar.jsp" type="button" class="btn btn-info">Editar</a><button type="button" class="btn btn-danger" style="margin-left: 10px;">Exluir</button></td>
                                    </tr>
                                    <tr>
                                        <td>Elizabeth Silva</td>
                                        <td>123.456.123.12</td>
                                        <td>12.642-32</td>
                                        <td>9824-1435</td>
                                        <td>joaos@gmail.com</td>
                                        <td>Rua 3</td>
                                        <td>12</td>
                                        <td>Guarapari</td>
                                        <td>81.316-247</td>
                                        <td>Estiva</td>
                                        <td>PR</td>
                                        <td>Funcionário</td>
                                        <td><a href="funcionarios_editar.jsp" type="button" class="btn btn-info">Editar</a><button type="button" class="btn btn-danger" style="margin-left: 10px;">Exluir</button></td>
                                    </tr>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
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

    <!-- jQuery -->
    <script src="../../vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../../vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../../vendor/metisMenu/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="../../vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="../../vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="../../vendor/datatables-responsive/dataTables.responsive.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../../dist/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
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
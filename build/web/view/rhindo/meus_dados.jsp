<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="rhindo.model.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Meus dados</title>

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
                <a class="navbar-brand" href="/DAC/view/rhindo/pagina_inicial.jsp">RH-INDO</a>
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
                        <li><a href="/DAC/CRUD?action=meusDados"><i class="fa fa-user fa-fw"></i> Meus dados</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
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
                            <a href="/DAC/view/rhindo/pagina_inicial.jsp"><i class="fa fa-home fa-fw"></i> Página inicial</a>
                        </li>
                        <%
                            Funcionario funcionario = (Funcionario)session.getAttribute("funcionario");
                            if("Gerente de RH".equals(funcionario.getPerfil())) {
                        %>
                                <li><a href="/DAC/CRUD?tabela=funcionario&action=listar"><i class="fa fa-users fa-fw"></i> Funcionários</a></li>
                                <li><a href="gerente/departamentos.jsp"><i class="fa fa-building fa-fw"></i> Departamentos</a></li>
                                <li><a href="gerente/cargos.jsp"><i class="fa fa-briefcase fa-fw"></i> Cargos</a></li>
                                <li><a href="gerente/fechamento_folha.jsp"><i class="fa fa-book fa-fw"></i> Fechamento da folha</a></li>
                                <li><a href="gerente/relatorios.jsp"><i class="fa fa-file-text fa-fw"></i> Relatórios</a></li>
                        <%
                            } else { 
                        %>
                                <li><a href="funcionario/horas_trabalhadas.jsp"><i class="fa fa-clock-o fa-fw"></i> Horas trabalhas</a></li>
                                <li><a href="funcionario/holerite.jsp"><i class="fa fa-list-alt fa-fw"></i> Holerite</a></li>
                        <%
                            }
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
                    <h1 class="page-header">Meus dados</h1>
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
                            <form>
                                <fieldset>
                                    <legend>Dados pessoais</legend>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group">
                                                <label>Nome:</label>
                                                <input class="form-control"  value="${funcionario.getNome()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>CPF:</label>
                                                <input class="form-control" value="${funcionario.getCpf()}">
                                                <p class="help-block">Somente números.</p>
                                            </div>
                                        </div>
                                        <!-- /.col-lg-6 (nested) -->
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>RG:</label>
                                                <input class="form-control"  value="${funcionario.getRg()}">
                                                <p class="help-block">Somente números.</p>
                                            </div>
                                        </div>
                                        <!-- /.col-lg-6 (nested) -->
                                        <div class="col-lg-5">
                                            <div class="form-group">
                                                <label>Celular:</label>
                                                <input class="form-control"  value="${funcionario.getCelular()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-7">
                                            <div class="form-group">
                                                <label>Email:</label>
                                                <input class="form-control"  value="${funcionario.getEmail()}">
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
                                                <input class="form-control"  value="${funcionario.getCep()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Rua:</label>
                                                <input class="form-control"  value="${funcionario.getRua()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label>Número:</label>
                                                <input class="form-control"  value="${funcionario.getNumero()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-5">
                                            <div class="form-group">
                                                <label>Bairro:</label>
                                                <input class="form-control"  value="${funcionario.getBairro()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-5">
                                            <div class="form-group">
                                                <label>Cidade:</label>
                                                <input class="form-control"  value="${funcionario.getCidade()}">
                                            </div>
                                        </div>
                                        <div class="col-lg-2">
                                            <div class="form-group">
                                                <label>Estado:</label>
                                                <select class="form-control">
                                                    <option value="AC" ${funcionario.getEstado() == "AC" ? 'selected' : ''}>AC</option>
                                                    <option value="AL" ${funcionario.getEstado() == "AL" ? 'selected' : ''}>AL</option>
                                                    <option value="AP" ${funcionario.getEstado() == "AP" ? 'selected' : ''}>AP</option>
                                                    <option value="AM" ${funcionario.getEstado() == "AM" ? 'selected' : ''}>AM</option>
                                                    <option value="BA" ${funcionario.getEstado() == "BA" ? 'selected' : ''}>BA</option>
                                                    <option value="CE" ${funcionario.getEstado() == "CE" ? 'selected' : ''}>CE</option>
                                                    <option value="DF" ${funcionario.getEstado() == "DF" ? 'selected' : ''}>DF</option>
                                                    <option value="ES" ${funcionario.getEstado() == "ES" ? 'selected' : ''}>ES</option>
                                                    <option value="GO" ${funcionario.getEstado() == "GO" ? 'selected' : ''}>GO</option>
                                                    <option value="MA" ${funcionario.getEstado() == "MA" ? 'selected' : ''}>MA</option>
                                                    <option value="MT" ${funcionario.getEstado() == "MT" ? 'selected' : ''}>MT</option>
                                                    <option value="MS" ${funcionario.getEstado() == "MS" ? 'selected' : ''}>MS</option>
                                                    <option value="MG" ${funcionario.getEstado() == "MG" ? 'selected' : ''}>MG</option>
                                                    <option value="PA" ${funcionario.getEstado() == "PA" ? 'selected' : ''}>PA</option>
                                                    <option value="PB" ${funcionario.getEstado() == "PB" ? 'selected' : ''}>PB</option>
                                                    <option value="PR" ${funcionario.getEstado() == "PR" ? 'selected' : ''}>PR</option>
                                                    <option value="PE" ${funcionario.getEstado() == "PE" ? 'selected' : ''}>PE</option>
                                                    <option value="PI" ${funcionario.getEstado() == "PI" ? 'selected' : ''}>PI</option>
                                                    <option value="RJ" ${funcionario.getEstado() == "RJ" ? 'selected' : ''}>RJ</option>
                                                    <option value="RN" ${funcionario.getEstado() == "RN" ? 'selected' : ''}>RN</option>
                                                    <option value="RS" ${funcionario.getEstado() == "RS" ? 'selected' : ''}>RS</option>
                                                    <option value="RO" ${funcionario.getEstado() == "RO" ? 'selected' : ''}>RO</option>
                                                    <option value="RR" ${funcionario.getEstado() == "RR" ? 'selected' : ''}>RR</option>
                                                    <option value="SC" ${funcionario.getEstado() == "SC" ? 'selected' : ''}>SC</option>
                                                    <option value="SP" ${funcionario.getEstado() == "SP" ? 'selected' : ''}>SP</option>
                                                    <option value="SE" ${funcionario.getEstado() == "SE" ? 'selected' : ''}>SE</option>
                                                    <option value="TO" ${funcionario.getEstado() == "TO" ? 'selected' : ''}>TO</option>
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
                                                <select class="form-control">
                                                <c:forEach items="${departamentos}" var="departamento">
                                                    <option value="${departamento.nome}"}>${departamento.nome}</option>
                                                </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-4">
                                            <div class="form-group">
                                                <label>Cargo:</label>
                                                <select class="form-control">
                                                <c:forEach items="${cargos}" var="cargo">
                                                    <option value="${cargo.nome}"}>${cargo.nome}</option>
                                                </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-4">
                                            <div class="form-group">
                                                <label>Perfil:</label>
                                                <select class="form-control">
                                                    <option value="Gerente de RH" ${funcionario.getPerfil() == "Gerente de RH" ? 'selected' : ''}>Gerente de RH</option>
                                                    <option value="Funcionário" ${funcionario.getPerfil() == "Funcionário" ? 'selected' : ''}>Funcionário</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.row (nested) -->
                                </fieldset>
                                <button type="submit" class="btn btn-primary" style="float:right;">Salvar</button>
                            </form>
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
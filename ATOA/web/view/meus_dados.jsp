<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Funcionario"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Meus dados</title>

    <%@ include file="links_css.html" %>

</head>

<body>
    <c:if test="${empty logado}">
        <c:redirect url ="/view/login.jsp"/>
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
                        <li>
                            <a href="/ATOA/view/pagina_inicial.jsp"><i class="fa fa-home fa-fw"></i> P�gina inicial</a>
                        </li>
                        <%
                            Funcionario logado = (Funcionario)session.getAttribute("logado");
                            if("Gerente de Departamento".equals(logado.getPerfil())) {
                        %>
                                <li><a href="/ATOA/Tipos?action=carregar"><i class="fa fa-users fa-fw"></i> Tipos de atividades</a></li>
                                <li><a href="/ATOA/Atividades?action=carregarCorrecoes"><i class="fa fa-check fa-fw"></i> Aprovar corre��es</a></li>
                                <li><a href="/ATOA/Atividades?action=Formfechar"><i class="fa fa-building fa-fw"></i> Fechamento de atividades</a></li>
                                <li><a href="/ATOA/Relatorios?action=form"><i class="fa fa-file-text fa-fw"></i> Relat�rios</a></li>
                        <%
                            } else { 
                        %>
                                <li><a href="/ATOA/Atividades?action=carregar"><i class="fa fa-clock-o fa-fw"></i> Atividades</a></li>
                                <li><a href="/ATOA/Atividades?action=carregarMes"><i class="fa fa-list-alt fa-fw"></i> Atividades do m�s</a></li>
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
                            Formul�rio de edi��o
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <form action ="/ATOA/Login?action=edit" method="POST" id="formEditar">
                                <fieldset>
                                    <legend>Dados pessoais</legend>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group ${not erroNome ? '' : 'has-error'}">
                                                <label class="control-label">${not erroNome ? 'Nome:' : 'Nome � obrigat�rio:'}</label>
                                                <input class="form-control"  value="${logado.getNome()}" name="nome">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>CPF:</label>
                                                <input class="form-control" value="${logado.getCpf()}" id="cpf" name="cpf" disabled>
                                                <p class="help-block">Somente n�meros.</p>
                                            </div>
                                        </div>
                                        <!-- /.col-lg-6 (nested) -->
                                        <div class="col-lg-6">
                                            <div class="form-group ${not erroRg ? '' : 'has-error'}">
                                                <label class="control-label">${not erroRg ? 'RG:' : 'RG � obrigat�rio:'}</label>
                                                <input class="form-control"  value="${logado.getRg()}" id="rg" name="rg">
                                                <p class="help-block">Somente n�meros.</p>
                                            </div>
                                        </div>
                                        <!-- /.col-lg-6 (nested) -->
                                        <div class="col-lg-5">
                                            <div class="form-group">
                                                <label>Celular:</label>
                                                <input class="form-control"  value="${logado.getCelular()}" id="celular" name="celular">
                                            </div>
                                        </div>
                                        <div class="col-lg-7">
                                            <div class="form-group ${not erroEmail ? '' : 'has-error'}">
                                                <label class="control-label">${not erroEmail ? 'Email:' : 'Email � obrigat�rio:'}</label>
                                                <input class="form-control"  value="${logado.getEmail()}" name="email">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group ${not erroSenha ? '' : 'has-error'}">
                                                <label class="control-label">${not erroSenha ? 'Senha:' : 'Senha e confirma��o diferente !!!'}</label>
                                                <input class="form-control"  type="password" name="senha" id="senha">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group ${not erroSenha ? '' : 'has-error'}">
                                                <label class="control-label">Confirma��o de senha:</label>
                                                <input class="form-control"  type="password" name="confirmacao" id="confirmacao">
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.row (nested) -->
                                </fieldset>
                                <fieldset>
                                    <legend>Endere�o</legend>
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label>CEP:</label>
                                                <input class="form-control"  value="${logado.getCep()}" id="cep" name="cep">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Rua:</label>
                                                <input class="form-control"  value="${logado.getRua()}" name="rua">
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="form-group">
                                                <label>N�mero:</label>
                                                <input class="form-control"  value="${logado.getNumero()}" name="numero" type="number" required>
                                            </div>
                                        </div>
                                        <div class="col-lg-5">
                                            <div class="form-group">
                                                <label>Bairro:</label>
                                                <input class="form-control"  value="${logado.getBairro()}" name="bairro">
                                            </div>
                                        </div>
                                        <div class="col-lg-5">
                                            <div class="form-group">
                                                <label>Cidade:</label>
                                                <input class="form-control"  value="${logado.getCidade()}" name="cidade">
                                            </div>
                                        </div>
                                        <div class="col-lg-2">
                                            <div class="form-group">
                                                <label>Estado:</label>
                                                <select class="form-control" name="estado">
                                                    <option value="AC" ${logado.getEstado() == "AC" ? 'selected' : ''}>AC</option>
                                                    <option value="AL" ${logado.getEstado() == "AL" ? 'selected' : ''}>AL</option>
                                                    <option value="AP" ${logado.getEstado() == "AP" ? 'selected' : ''}>AP</option>
                                                    <option value="AM" ${logado.getEstado() == "AM" ? 'selected' : ''}>AM</option>
                                                    <option value="BA" ${logado.getEstado() == "BA" ? 'selected' : ''}>BA</option>
                                                    <option value="CE" ${logado.getEstado() == "CE" ? 'selected' : ''}>CE</option>
                                                    <option value="DF" ${logado.getEstado() == "DF" ? 'selected' : ''}>DF</option>
                                                    <option value="ES" ${logado.getEstado() == "ES" ? 'selected' : ''}>ES</option>
                                                    <option value="GO" ${logado.getEstado() == "GO" ? 'selected' : ''}>GO</option>
                                                    <option value="MA" ${logado.getEstado() == "MA" ? 'selected' : ''}>MA</option>
                                                    <option value="MT" ${logado.getEstado() == "MT" ? 'selected' : ''}>MT</option>
                                                    <option value="MS" ${logado.getEstado() == "MS" ? 'selected' : ''}>MS</option>
                                                    <option value="MG" ${logado.getEstado() == "MG" ? 'selected' : ''}>MG</option>
                                                    <option value="PA" ${logado.getEstado() == "PA" ? 'selected' : ''}>PA</option>
                                                    <option value="PB" ${logado.getEstado() == "PB" ? 'selected' : ''}>PB</option>
                                                    <option value="PR" ${logado.getEstado() == "PR" ? 'selected' : ''}>PR</option>
                                                    <option value="PE" ${logado.getEstado() == "PE" ? 'selected' : ''}>PE</option>
                                                    <option value="PI" ${logado.getEstado() == "PI" ? 'selected' : ''}>PI</option>
                                                    <option value="RJ" ${logado.getEstado() == "RJ" ? 'selected' : ''}>RJ</option>
                                                    <option value="RN" ${logado.getEstado() == "RN" ? 'selected' : ''}>RN</option>
                                                    <option value="RS" ${logado.getEstado() == "RS" ? 'selected' : ''}>RS</option>
                                                    <option value="RO" ${logado.getEstado() == "RO" ? 'selected' : ''}>RO</option>
                                                    <option value="RR" ${logado.getEstado() == "RR" ? 'selected' : ''}>RR</option>
                                                    <option value="SC" ${logado.getEstado() == "SC" ? 'selected' : ''}>SC</option>
                                                    <option value="SP" ${logado.getEstado() == "SP" ? 'selected' : ''}>SP</option>
                                                    <option value="SE" ${logado.getEstado() == "SE" ? 'selected' : ''}>SE</option>
                                                    <option value="TO" ${logado.getEstado() == "TO" ? 'selected' : ''}>TO</option>
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
                                                <select class="form-control" name="departamento" disabled>
                                                <c:forEach items="${departamentos}" var="departamento">
                                                    <option value="${departamento.id}" ${logado.getDepartamento().getNome().equals(departamento.nome) ? 'selected' : ''}>${departamento.nome}</option>
                                                </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-4">
                                            <div class="form-group">
                                                <label>Cargo:</label>
                                                <select class="form-control" name="cargo" disabled>
                                                <c:forEach items="${cargos}" var="cargo">
                                                    <option value="${cargo.id}" ${logado.getCargo().getNome().equals(cargo.nome) ? 'selected' : ''}>${cargo.nome}</option>
                                                </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-4">
                                            <div class="form-group">
                                                <label>Perfil:</label>
                                                <select class="form-control" name="perfil" disabled>
                                                    <option value="Gerente de Departamento" ${logado.getPerfil() == "Gerente de Departamento" ? 'selected' : ''}>Gerente de Departamento</option>
                                                    <option value="Funcion�rio" ${logado.getPerfil() == "Funcion�rio" ? 'selected' : ''}>Funcion�rio</option>
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
    <!-- /#wrapper -->

    <%@ include file="scripts_js.html" %>
    <script src="/RHINDO/jquery.maskedinput.min.js"></script>
    <script src="/ATOA/jquery-validation-1.16.0/dist/jquery.validate.min.js"></script>
    <script>
        $("#cpf").mask("999.999.999-99");
        $("#rg").mask("99.999.999-9");
        $("#celular").mask("(99) 9999-9999?9");
        $("#cep").mask("99.999-999");
        $("#formEditar").validate({
            rules: {
                senha: {
                    equalTo: "#confirmacao"
                },
                nome: "required",
                rg : "required",
                email:  {
                    required: true,
                    email: true
                }
            },
            messages: {
                senha: "Senha e confirma��o diferentes !!!",
                nome: "Nome � obrigat�rio !!!",
                rg : "RG � obrigat�rio !!!",
                email : {
                    required: "Email � obrigat�rio !!!",
                    email: "Email inv�lido !!!"
                }
            }
        });
    </script>
    <c:if test="${not empty msg}">
        <script>$("#modalMsg").modal('show');</script>
    </c:if>
</body>

</html>
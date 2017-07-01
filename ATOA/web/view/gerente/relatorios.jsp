<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Relatórios</title>

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

            
            <%@ include file="side_bar.jsp" %>
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Relatórios</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-7">
                                    <div class="form-group">
                                        <label>Tipo de relatório:</label>
                                        <label class="radio-inline">
                                            <input type="radio" name="opt" id="optionsRadiosInline1" value="1" checked="">Consolidado por funcionário
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="opt" id="optionsRadiosInline2" value="2">Consolidado por dia
                                        </label>
                                    </div>
                                </div>
                                <div class="col-lg-5">
                                    <div id="opt1" class="desc form-group">
                                        <form action="/ATOA/Relatorios?action=funcionario" method="post" target="_blank">
                                            <select id="funcs" class="form-group form-control" name="especifico">
                                            <c:forEach items="${funcionarios}" var="funcionario">
                                                <option value="${funcionario.cpf}">${funcionario.nome}</option>
                                            </c:forEach>
                                            </select>
                                            <div class="form-group">
                                                <button type="submit" class="btn btn-info">Gerar</button>
                                            </div>
                                        </form>
                                    </div>
                                    <div id="opt2" class="desc form-group ${erroDia ? 'has-error' : ''}" hidden>
                                        <c:if test="${erroDia}">
                                            <label class="control-label">${erroDia}</label>
                                        </c:if>
                                        <form action="/ATOA/Relatorios?action=dia" method="post" target="_blank" id="form">
                                            <input type="date" class="form-group form-control" name="dia">
                                            <div class="form-group">
                                                <button type="submit" class="btn btn-info">Gerar</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- /.row (nested) -->
                            <!--<div class="panel panel-default">
                                <div class="panel-heading">
                                    Relatório
                                </div>
                                <div class="panel-body">
                                    
                                </div>
                            </div>-->
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
    <script src="/ATOA/jquery-validation-1.16.0/dist/jquery.validate.min.js"></script>
    <script>
        $("input[name='opt']").click(function() {
            var test = $(this).val();

            $("div.desc").hide();
            $("#opt" + test).show();
        });
        $("#form").validate({
            rules: {
                dia: "required"
            },
            messages: {
                dia: "Dia é obrigatório !!!"
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
    </script>
</body>

</html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="Ana Paula Santos, Bianca Santos, David Junior">

    <title>Login</title>

    <%@ include file="links_css.html" %>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"> <span class="glyphicon glyphicon-log-in"></span> Login - Portal ATOA<img id="login" src="../vendor/bootstrap/img/logo_atoa1.ico"></h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="/ATOA/Login?action=login" method="post">
                            <fieldset>
                                <%
                                String erro = (String) request.getAttribute("erro");

                                if("Não encontrado".equals(erro)) {
                                %>
                                    <div class="form-group has-error">
                                        <label class="control-label" for="inputError">Funcionário não cadastrado</label>
                                        <input class="form-control" placeholder="CPF" name="cpf" type="cpf" autofocus>
                                    </div>
                                <%
                                } else {
                                %>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="CPF" name="cpf" type="cpf" autofocus>
                                    </div>
                                <%
                                    }
                                %>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit" value="Entrar" class="btn btn-lg btn-success btn-block">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="scripts_js.html" %>

</body>

</html>

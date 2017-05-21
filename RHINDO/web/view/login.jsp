<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Login</title>

    <%@ include file="links_css.html" %>

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Digite seu CPF</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="/RHINDO/Login?action=login" method="POST">
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
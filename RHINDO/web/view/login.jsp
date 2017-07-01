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
                        <h3 class="panel-title">Digite seu CPF e senha</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="/RHINDO/Login?action=login" method="POST">
                            <fieldset>
                                <div class="form-group ${not empty erroCpf ? 'has-error' : ''}">
                                    <c:if test="${not empty erroCpf}">
                                        <label class="control-label">${erroCpf}</label>
                                    </c:if>
                                    <input class="form-control" placeholder="CPF" id="cpf" name="cpf" type="cpf" value="${cpf}" autofocus>
                                </div>
                                <div class="form-group ${not empty erroSenha ? 'has-error' : ''}">
                                    <c:if test="${not empty erroSenha}">
                                        <label class="control-label">${erroSenha}</label>
                                    </c:if>
                                    <input class="form-control" placeholder="Senha" name="senha" type="password" autofocus>
                                </div>
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
    <script src="/RHINDO/jquery.maskedinput.min.js"></script>
    <script>
        $("#cpf").mask("999.999.999-99");
    </script>
</body>

</html>
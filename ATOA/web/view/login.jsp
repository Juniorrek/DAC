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
                        <form role="form" action="/ATOA/Login?action=login" method="post" id="formLogin">
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
    <script src="/ATOA/jquery.maskedinput.min.js"></script>
    <script src="/ATOA/jquery-validation-1.16.0/dist/jquery.validate.min.js"></script>
    <script>
        $("#cpf").mask("999.999.999-99");
        $("#formLogin").validate({
            rules: {
                cpf: "required",
                senha: "required"
            },
            messages: {
                cpf: "Digite seu CPF !!!",
                senha: "Digite sua senha !!!"
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
    </script>
</body>

</html>

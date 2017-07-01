package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import facade.Facade;
import model.Cargo;
import model.Departamento;
import model.Funcionario;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if("login".equals(action)) {
            String cpf = request.getParameter("cpf").replace(".", "").replace("-", "");
            String senha = request.getParameter("senha");
            
            boolean erro = false;
            if ("".equals(cpf)) {
                erro = true;
                request.setAttribute("erroCpf", "Digite seu CPF !!!");
            } 
            if ("".equals(senha)) {
                erro = true;
                request.setAttribute("erroSenha", "Digite sua senha !!!");
            }
            //
            if (erro) {
                request.setAttribute("cpf", cpf);
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/login.jsp");
                requestDispatcher.forward(request, response);
            } else {
            
                Funcionario funcionario = Facade.carregarFuncionario(cpf, senha);
                //Funcionário encontrado
                if(funcionario != null && ("Gerente de Departamento".equals(funcionario.getPerfil()) || "Funcionário".equals(funcionario.getPerfil()))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("logado", funcionario);
                    if (funcionario.getDepartamento().isNotificacao()) {
                        session.setAttribute("notificacao", true);
                    }
                    response.sendRedirect("view/pagina_inicial.jsp");
                } else {
                    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/login.jsp");
                    request.setAttribute("erroCpf", "CPF e senha não cadastrados !!!");
                    requestDispatcher.forward(request, response);
                }
            }
        } else if ("editar".equals(action)) {
            List<Departamento> departamentos = Facade.carregarDepartamento();
            List<Cargo> cargos = Facade.carregarCargo();
            
            request.setAttribute("departamentos", departamentos);
            request.setAttribute("cargos", cargos);
            
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/meus_dados.jsp");
            requestDispatcher.forward(request, response);
        } else if ("edit".equals(action)) {
            boolean erro = false;
            if (!request.getParameter("senha").equals(request.getParameter("confirmacao"))) {
                erro = true;
                request.setAttribute("erroSenha", true);
            }
            //
            if (erro) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/meus_dados.jsp");
                requestDispatcher.forward(request, response);
            } else {
                Departamento departamento = null;
                Cargo cargo = null;
                String perfil = null;
                if (request.getParameter("departamento") != null) {
                    departamento = Facade.carregarDepartamento(Integer.parseInt(request.getParameter("departamento")));
                    cargo = Facade.carregarCargo(Integer.parseInt(request.getParameter("cargo")));
                    perfil = request.getParameter("perfil");
                }

                HttpSession session = request.getSession();
                Funcionario logado = (Funcionario)session.getAttribute("logado");
                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(logado.getCpf());
                funcionario.setNome(request.getParameter("nome"));
                funcionario.setRg(request.getParameter("rg").replace(".", "").replace("-", ""));
                funcionario.setCelular(request.getParameter("celular").replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
                funcionario.setEmail(request.getParameter("email"));
                funcionario.setRua(request.getParameter("rua"));
                funcionario.setNumero(Integer.parseInt(request.getParameter("numero")));
                funcionario.setBairro(request.getParameter("bairro"));
                funcionario.setCep(request.getParameter("cep").replace(".", "").replace("-", ""));
                funcionario.setCidade(request.getParameter("cidade"));
                funcionario.setEstado(request.getParameter("estado"));
                funcionario.setDepartamento(departamento);
                funcionario.setCargo(cargo);
                funcionario.setPerfil(perfil);
                funcionario.setSenha(request.getParameter("senha"));

                Facade.editarFuncionario(funcionario);
                funcionario = Facade.carregarFuncionario(funcionario.getCpf());

                session.setAttribute("logado", funcionario);

                response.sendRedirect("/ATOA/Login?action=editar");
            }
        } else if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session!=null)
               session.invalidate(); 
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/login.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

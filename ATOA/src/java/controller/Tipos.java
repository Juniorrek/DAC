package controller;

import facade.Facade;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Funcionario;
import model.Tipo;

@WebServlet(name = "Tipos", urlPatterns = {"/Tipos"})
public class Tipos extends HttpServlet {

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
        HttpSession sessionValida = request.getSession();
        Funcionario valida = (Funcionario) sessionValida.getAttribute("logado");
        if (valida == null) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/login.jsp");             
            requestDispatcher.forward(request, response);
        } else if (!"Gerente de Departamento".equals(valida.getPerfil())) {
           RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
           requestDispatcher.forward(request, response); 
        }
        String action = request.getParameter("action");
        
        if ("criar".equals(action)) {
            boolean erro = false;
            if ("".equals(request.getParameter("nome"))) {
                erro = true;
                request.setAttribute("erroNome", true);
            }
            //
            if (erro) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Tipos?action=carregar");
                requestDispatcher.forward(request, response);
            } else {
                Tipo tipo = new Tipo();
                tipo.setNome(request.getParameter("nome"));
                tipo.setDescricao(request.getParameter("descricao"));

                HttpSession session = request.getSession();
                Funcionario logado = (Funcionario) session.getAttribute("logado");

                String retorno = Facade.criarTipo(tipo, logado.getDepartamento());

                if (retorno != null) {
                    request.setAttribute("msg", retorno.toString());
                }
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Tipos?action=carregar");             
                requestDispatcher.forward(request, response);
            }
        } else if ("carregar".equals(action)) {
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            List<Tipo> tipos = Facade.carregarTipo(logado.getDepartamento());

            request.setAttribute("tipos", tipos);

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/tipos.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("editar".equals(action)) {
            boolean erro = false;
            if ("".equals(request.getParameter("nome"))) {
                erro = true;
                request.setAttribute("erroNome2", true);
            }
            //
            if (erro) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Tipos?action=carregar");
                requestDispatcher.forward(request, response);
            } else {
                Tipo tipo = new Tipo();
                tipo.setId(Integer.parseInt(request.getParameter("id")));
                tipo.setNome(request.getParameter("nome"));
                tipo.setDescricao(request.getParameter("descricao"));

                String retorno = Facade.editarTipo(tipo);

                if (retorno != null) {
                    request.setAttribute("msg", retorno.toString());
                }
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Tipos?action=carregar");             
                requestDispatcher.forward(request, response);
            }
        } else if ("deletar".equals(action)) {
            String retorno = Facade.deletarTipo(Integer.parseInt(request.getParameter("id")));

            if (retorno != null) {
                request.setAttribute("msg", retorno.toString());
            }
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Tipos?action=carregar");             
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

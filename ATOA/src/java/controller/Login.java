/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author Fornalha
 */
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
            try {
                String cpf = request.getParameter("cpf");
                Funcionario funcionario = null;
                
                funcionario = Facade.getFuncionario(cpf);
                //Funcionário encontrado
                if(funcionario != null && ("Gerente de Departamento".equals(funcionario.getPerfil()) || "Funcionário".equals(funcionario.getPerfil()))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("logado", funcionario);
                    response.sendRedirect("view/pagina_inicial.jsp");
                } else {
                    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/login.jsp");
                    request.setAttribute("erro", "Não encontrado");
                    requestDispatcher.forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if("formUpdate".equals(action)) {
            try {
                List<Departamento> departamentos = Facade.getDepartamentos();
                List<Cargo> cargos = Facade.getCargos();
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/meus_dados.jsp");
                
                request.setAttribute("departamentos", departamentos);
                request.setAttribute("cargos", cargos);
                
                requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if("update".equals(action)) {
            try {
                Funcionario funcionario = new Funcionario(0,
                        request.getParameter("nome"),
                        request.getParameter("cpf"),
                        request.getParameter("rg"),
                        request.getParameter("celular"),
                        request.getParameter("email"),
                        request.getParameter("rua"),
                        Integer.parseInt(request.getParameter("numero")),
                        request.getParameter("bairro"),
                        request.getParameter("cep"),
                        request.getParameter("cidade"),
                        request.getParameter("estado"),
                        request.getParameter("perfil"),
                        Facade.getDepartamento(Integer.parseInt(request.getParameter("id_departamento"))),
                        Facade.getCargo(Integer.parseInt(request.getParameter("id_cargo"))));
                Facade.updateFuncionario(funcionario);
                
                HttpSession session = request.getSession();
                session.setAttribute("logado", funcionario);
                
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Login?action=formUpdate");
                requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
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

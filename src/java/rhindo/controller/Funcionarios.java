/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhindo.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import rhindo.facade.Facade;
import rhindo.model.Cargo;
import rhindo.model.Departamento;
import rhindo.model.Funcionario;

/**
 *
 * @author Fornalha
 */
@WebServlet(name = "Funcionarios", urlPatterns = {"/Funcionarios"})
public class Funcionarios extends HttpServlet {

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
        
        if("select".equals(action)) {
            try {
                List<Funcionario> funcionarios = Facade.getFuncionarios();
                
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/rhindo/gerente/funcionarios.jsp");
                
                request.setAttribute("funcionarios", funcionarios);
                request.setAttribute("action", "listar");
                requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Funcionarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if("formInsert".equals(action)) {
            try {
                List<Departamento> departamentos = Facade.getDepartamentos();
                List<Cargo> cargos = Facade.getCargos();
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/rhindo/gerente/funcionarios.jsp");
                
                request.setAttribute("action", "formInsert");
                request.setAttribute("departamentos", departamentos);
                request.setAttribute("cargos", cargos);
                
                requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Funcionarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if("insert".equals(action)) {
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
                Facade.insertFuncionario(funcionario);
                
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Funcionarios?action=select");
                requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if("formUpdate".equals(action)) {
            try {
                List<Departamento> departamentos = Facade.getDepartamentos();
                List<Cargo> cargos = Facade.getCargos();
                Funcionario funcionario = Facade.getFuncionario(request.getParameter("cpf"));
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/rhindo/gerente/funcionarios.jsp");
                
                request.setAttribute("action", "formUpdate");
                request.setAttribute("funcionario", funcionario);
                request.setAttribute("departamentos", departamentos);
                request.setAttribute("cargos", cargos);
                
                requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
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
                
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Funcionarios?action=select");
                requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if("delete".equals(action)) {
            try {
                Funcionario funcionario = Facade.getFuncionario(request.getParameter("cpf"));
                Facade.deleteFuncionario(funcionario);
                
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Funcionarios?action=select");
                requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
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

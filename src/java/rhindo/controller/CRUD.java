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
@WebServlet(name = "CRUD", urlPatterns = {"/CRUD"})
public class CRUD extends HttpServlet {

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
        
        if("listarFuncionarios".equals(action)) {
            try {
                List<Funcionario> funcionarios = Facade.getFuncionarios();
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/rhindo/gerente/funcionarios.jsp");

                request.setAttribute("funcionarios", funcionarios);
                requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("meusDados".equals(action)) {
            try {
                List<Departamento> departamentos = Facade.getDepartamentos();
                List<Cargo> cargos = Facade.getCargos();
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/rhindo/meus_dados.jsp");
                
                request.setAttribute("departamentos", departamentos);
                request.setAttribute("cargos", cargos);
                requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import facade.Facade;
import model.Cargo;

/**
 *
 * @author Fornalha
 */
@WebServlet(name = "Cargos", urlPatterns = {"/Cargos"})
public class Cargos extends HttpServlet {

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
                List<Cargo> cargos = Facade.getCargos();
                
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/cargos.jsp");
                
                request.setAttribute("cargos", cargos);
                request.setAttribute("action", "listar");
                requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Cargos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if("formInsert".equals(action)) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/cargos.jsp");             
            request.setAttribute("action", "formCadastro");
            requestDispatcher.forward(request, response);
        } else if("insert".equals(action)) {
            try {
                Cargo cargo = new Cargo(0, request.getParameter("nome"), Float.parseFloat(request.getParameter("salario")), request.getParameter("requisitos"), Integer.parseInt(request.getParameter("imposto_desconto")), Integer.parseInt(request.getParameter("carga_trabalho_minima_mes")));
                
                Facade.insertCargo(cargo);
                
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Cargos?action=select");
                requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if("formUpdate".equals(action)) {
            try {
                Cargo cargo = Facade.getCargo(Integer.parseInt(request.getParameter("id")));
                
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/cargos.jsp");
                request.setAttribute("action", "formUpdate");
                request.setAttribute("cargo", cargo);
                requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if("update".equals(action)) {
            try {
                Cargo cargo = new Cargo(Integer.parseInt(request.getParameter("id")), request.getParameter("nome"), Float.parseFloat(request.getParameter("salario")), request.getParameter("requisitos"), Integer.parseInt(request.getParameter("imposto_desconto")), Integer.parseInt(request.getParameter("carga_trabalho_minima_mes")));
                
                Facade.updateCargo(cargo);
                
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Cargos?action=select");
                requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if("delete".equals(action)) {
            try {
                Cargo cargo = Facade.getCargo(Integer.parseInt(request.getParameter("id")));
                
                Facade.deleteCargo(cargo);
                
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Cargos?action=select");
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

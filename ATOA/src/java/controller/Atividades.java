/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.Facade;
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
import model.Atividade;
import model.Departamento;
import model.Funcionario;

/**
 *
 * @author Fornalha
 */
@WebServlet(name = "Atividades", urlPatterns = {"/Atividades"})
public class Atividades extends HttpServlet {

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
        HttpSession session = request.getSession();
        Funcionario logado = (Funcionario)session.getAttribute("logado");
        if ("Gerente de Departamento".equals(logado.getPerfil())) {
            if ("select".equals(action)) {
                try { 
                    List<Atividade> atividades = Facade.getAtividades(logado.getDepartamento().getId());
                    
                    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/atividades.jsp");

                    request.setAttribute("atividades", atividades);
                    System.out.println(atividades);
                    //request.setAttribute("action", "listar");
                    requestDispatcher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(Atividades.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if("formInsert".equals(action)) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/atividades.jsp");             
                request.setAttribute("action", "formCadastro");
                requestDispatcher.forward(request, response);
            } else if("insert".equals(action)) {
                try {
                    Atividade atividade = new Atividade(0, request.getParameter("nome"), request.getParameter("descricao"), null, null, new Departamento(logado.getDepartamento().getId(), null, null), null);

                    Facade.insertAtividade(atividade);

                    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Atividades?action=select");
                    requestDispatcher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(Atividades.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if("formUpdate".equals(action)) {
                try {
                    Atividade atividade = Facade.getAtividade(Integer.parseInt(request.getParameter("id")));

                    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/atividades.jsp");
                    request.setAttribute("action", "formUpdate");
                    request.setAttribute("atividade", atividade);
                    requestDispatcher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(Atividades.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if("update".equals(action)) {
                try {
                    Atividade atividade = new Atividade(Integer.parseInt(request.getParameter("id")), request.getParameter("nome"), request.getParameter("descricao"), null, null, null, null);

                    Facade.updateAtividade(atividade);

                    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Atividades?action=select");
                    requestDispatcher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(Atividades.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if("delete".equals(action)) {
                try {
                    Atividade atividade = Facade.getAtividade(Integer.parseInt(request.getParameter("id")));

                    Facade.deleteAtividade(atividade);

                    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Atividades?action=select");
                    requestDispatcher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(Atividades.class.getName()).log(Level.SEVERE, null, ex);
                }
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ConnectionFactory;
import facade.Facade;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Funcionario;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Fornalha
 */
@WebServlet(name = "Folha", urlPatterns = {"/Folha"})
public class Folha extends HttpServlet {

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
        
        if ("fechar".equals(action)) {
            HttpSession sessionValida = request.getSession();
            Funcionario valida = (Funcionario) sessionValida.getAttribute("logado");
            if (valida == null) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/login.jsp");             
                requestDispatcher.forward(request, response);
            } else if (!"Gerente de RH".equals(valida.getPerfil())) {
               RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
               requestDispatcher.forward(request, response); 
            }
            String mes = request.getParameter("mes");
            
            request.setAttribute("modal", Facade.fecharFolha(Integer.parseInt(mes)));
            
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/fechamento_folha.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("horas_trabalhadas".equals(action)) {
            HttpSession sessionValida = request.getSession();
            Funcionario valida = (Funcionario) sessionValida.getAttribute("logado");
            if (valida == null) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/login.jsp");             
                requestDispatcher.forward(request, response);
            } else if (!"Funcionário".equals(valida.getPerfil())) {
               RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
               requestDispatcher.forward(request, response); 
            }
            boolean erro = false;
            if ("".equals(request.getParameter("de"))) {
                erro = true;
                request.setAttribute("erroDe", true);
            } 
            if ("".equals(request.getParameter("ate"))) {
                erro = true;
                request.setAttribute("erroAte", true);
            }
            //
            if (erro) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/funcionario/horas_trabalhadas.jsp");
                requestDispatcher.forward(request, response);
            } else {
                String de = request.getParameter("de");
                String ate = request.getParameter("ate");
                HttpSession session = request.getSession();
                Funcionario logado = (Funcionario) session.getAttribute("logado");

                List<model.Folha> listaa = Facade.horasTrabalhadas(de, ate, logado);

                Connection con = new ConnectionFactory().getConnection();
                try {
                    String jasper = request.getContextPath() + "/horas_intervalos_datas.jasper";
                    String host = "http://" + request.getServerName() + ":" + request.getServerPort();

                    URL jasperURL = new URL(host + jasper);

                    HashMap params = new HashMap();
                    JRBeanCollectionDataSource lista = new JRBeanCollectionDataSource(listaa);
                    params.put("lista", lista);

                    byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, new JREmptyDataSource());

                    if (bytes != null) {
                        response.setContentType("application/pdf");

                        OutputStream ops = response.getOutputStream();

                        ops.write(bytes);
                    }
                } catch (JRException e) {
                    System.out.println("Erro no jasper : " + e.getMessage());
                } finally {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (Exception e) {

                        }
                    }
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

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
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
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
 * @author david
 */
@WebServlet(name = "Holerite", urlPatterns = {"/Holerite"})
public class Holerite extends HttpServlet {

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
        } else if (!"Funcionário".equals(valida.getPerfil())) {
           RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
           requestDispatcher.forward(request, response); 
        }
        String action = request.getParameter("action");
        
        if ("obter".equals(action)) {
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            Timestamp dataAtual = new Timestamp(System.currentTimeMillis());
            long timestamp = dataAtual.getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(timestamp);
            
            model.Holerite holerite = Facade.obterHolerite(logado, cal.get(Calendar.MONTH) + 1);
            request.setAttribute("holerite", holerite);
            
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/funcionario/holerite.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("pdf".equals(action)) {
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            Timestamp dataAtual = new Timestamp(System.currentTimeMillis());
            long timestamp = dataAtual.getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(timestamp);
            
            model.Holerite holerite = Facade.obterHolerite(logado, cal.get(Calendar.MONTH) + 1);
            if (holerite == null) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Holerite?action=obter");             
                requestDispatcher.forward(request, response);
            }
            
            Connection con = new ConnectionFactory().getConnection();
            try {
                String jasper = request.getContextPath() + "/holerite.jasper";
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();

                URL jasperURL = new URL(host + jasper);

                HashMap params = new HashMap();
                params.put("cpf", holerite.getCpf());
                params.put("mes", holerite.getMes());
                params.put("nome", holerite.getNome());
                params.put("horas_trabalhadas", holerite.getHoras_trabalhadas());
                params.put("salario_bruto", holerite.getSalario_bruto());
                params.put("salario_liquido", holerite.getSalario_liquido());

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

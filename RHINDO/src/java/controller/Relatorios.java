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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import model.Folha;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author david
 */
@WebServlet(name = "Relatorios", urlPatterns = {"/Relatorios"})
public class Relatorios extends HttpServlet {

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
        
        if ("funcionarios".equals(action)){
            Connection con = new ConnectionFactory().getConnection();
            try {
                String jasper = request.getContextPath() + "/funcionarios.jasper";
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();

                URL jasperURL = new URL(host + jasper);

                HashMap params = new HashMap();

                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);

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
        } else if ("hrsdep".equals(action)) {//esse e o outro no atoa para pegar as mais atualizadas? ou só na folha
            String mes = request.getParameter("mes");
            
            List<model.Folha> listaa = Facade.hrsTrabalhadasDepMes(Integer.parseInt(mes));
            
            Connection con = new ConnectionFactory().getConnection();
            try {
                String jasper = request.getContextPath() + "/horas_trabalhadas_dep_mes.jasper";
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
        } else if ("funcncumpriu".equals(action)) {
            String mes = request.getParameter("mes");
            
            List<model.Folha> listaa = Facade.funcncumpriu(Integer.parseInt(mes));
            
            Connection con = new ConnectionFactory().getConnection();
            try {
                String jasper = request.getContextPath() + "/funcncumpriu.jasper";
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

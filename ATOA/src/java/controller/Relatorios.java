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
import java.sql.DriverManager;
import java.sql.SQLException;
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
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.JRException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Fornalha
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
        
        if ("form".equals(action)) {
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            List<Funcionario> funcionarios = Facade.carregarFuncionarioDep(logado.getDepartamento());
            
            request.setAttribute("funcionarios", funcionarios);
            
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/relatorios.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("funcionario".equals(action)) {
            String cpf = request.getParameter("especifico");
            Funcionario funcionario = Facade.carregarFuncionario(cpf);
            
            Connection con = new ConnectionFactory().getConnection();
            try {
                String jasper = request.getContextPath() + "/atividades_funcionario.jasper";
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();

                URL jasperURL = new URL(host + jasper);

                HashMap params = new HashMap();
                params.put("CPF", funcionario.getCpf());
                params.put("Nome", funcionario.getNome());

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
        } else if ("dia".equals(action)) {
            boolean erro = false;
            if ("".equals(request.getParameter("dia"))) {
                erro = true;
                request.setAttribute("erroDia", "Seleciona uma data !!!");
            }
            //
            if (erro) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Relatorios?action=form");
                requestDispatcher.forward(request, response);
            } else {
                String dia = request.getParameter("dia");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                try {
                    cal.setTime(sdf.parse(dia));
                } catch (ParseException ex) {
                    Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
                }
                HttpSession session = request.getSession();
                Funcionario logado = (Funcionario) session.getAttribute("logado");

                Connection con = new ConnectionFactory().getConnection();
                try {
                    String jasper = request.getContextPath() + "/atividades_dia.jasper";
                    String host = "http://" + request.getServerName() + ":" + request.getServerPort();

                    URL jasperURL = new URL(host + jasper);

                    HashMap params = new HashMap();
                    params.put("Dia", cal.get(Calendar.DAY_OF_MONTH));
                    params.put("Mes", cal.get(Calendar.MONTH) + 1);
                    params.put("Ano", cal.get(Calendar.YEAR));
                    params.put("Departamento", logado.getDepartamento().getId());

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

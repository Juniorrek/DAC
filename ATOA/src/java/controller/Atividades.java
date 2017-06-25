/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.Facade;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
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
import model.Funcionario;
import model.Tipo;

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
        
        if ("criar".equals(action)) {
            Atividade atividade = new Atividade();
            atividade.setNome(request.getParameter("nome"));
            atividade.setTipo(Facade.carregarTipo(Integer.parseInt(request.getParameter("tipo"))));
            atividade.setDescricao(request.getParameter("descricao"));
            atividade.setInicio(new Timestamp(System.currentTimeMillis()));
            
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            atividade.setFuncionario(logado);
            
            Facade.criarAtividade(atividade);

            response.sendRedirect("/ATOA/Atividades?action=carregar");
        } else if ("carregar".equals(action)) {
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            List<Atividade> atividades = Facade.carregarAtividade(logado);
            List<Tipo> tipos = Facade.carregarTipo(logado.getDepartamento());

            request.setAttribute("tipos", tipos);
            request.setAttribute("atividades", atividades);

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/funcionario/atividades.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("solicitarCorrigir".equals(action)) {
            Atividade atividade = new Atividade();
            atividade.setId(Integer.parseInt(request.getParameter("id")));
            atividade.setNome(request.getParameter("nome"));
            atividade.setTipo(Facade.carregarTipo(Integer.parseInt(request.getParameter("tipo"))));
            atividade.setDescricao(request.getParameter("descricao"));
            
            try{
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date parsedDate = dateFormat.parse(request.getParameter("inicio").replace("T", " "));
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                atividade.setInicio(timestamp);
                if (request.getParameter("fim") != null) {
                    parsedDate = dateFormat.parse(request.getParameter("fim").replace("T", " "));
                    Timestamp timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
                    atividade.setFim(timestamp2);
                } else {
                    Timestamp timestamp2 = null;
                    atividade.setFim(timestamp2);
                }
            } catch (ParseException ex) {
                Logger.getLogger(Atividades.class.getName()).log(Level.SEVERE, null, ex);
            }
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            atividade.setFuncionario(logado);
            switch (request.getParameter("status")) {
                case "EM ANDAMENTO":
                case "PENDENTE":
                    atividade.setFim(null);
                    break;
                case "FINALIZADA":
                    if (atividade.getFim() == null) {
                        atividade.setFim(new Timestamp(System.currentTimeMillis()));
                    }
                    break;
            }
            atividade.setStatus(request.getParameter("status"));
            
            Facade.solicitarCorrigirAtividade(atividade);
            
            response.sendRedirect("/ATOA/Atividades?action=carregar");
        } else if ("carregarCorrecoes".equals(action)) {
            List<Map<String, Atividade>> correcoes = Facade.carregarCorrecoes();
            
            request.setAttribute("correcoes", correcoes);
            
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            List<Tipo> tipos = Facade.carregarTipo(logado.getDepartamento());

            request.setAttribute("tipos", tipos);

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/corrigir.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("reprovar".equals(action)) {
            Facade.reprovarCorrecao(Integer.parseInt(request.getParameter("id")));
            
            response.sendRedirect("/ATOA/Atividades?action=carregarCorrecoes");
        } else if ("aprovar".equals(action)) {
            Facade.aprovarCorrecao(Integer.parseInt(request.getParameter("id")));
            
            response.sendRedirect("/ATOA/Atividades?action=carregarCorrecoes");
        } else if ("finalizar".equals(action)) {
            Facade.finalizarAtividade(Integer.parseInt(request.getParameter("id")));

            response.sendRedirect("/ATOA/Atividades?action=carregar");
        } else if ("carregarMes".equals(action)) {
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            List<Atividade> atividades = Facade.carregarAtividadeMes(logado);
            
            request.setAttribute("atividades", atividades);
            
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/funcionario/atividades_mes.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("Formfechar".equals(action)) {
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            List<Atividade> atividades = Facade.carregarAtividadeDep(logado);
            List<Funcionario> funcionarios = Facade.carregarFuncionarioDep(logado.getDepartamento());
            
            request.setAttribute("atividades", atividades);
            request.setAttribute("funcionarios", funcionarios);
            
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/fechamento_atividades.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("fechar".equals(action)) {
            String especifico = request.getParameter("especifico");
            
            if (especifico == null) {
                Facade.fecharAtividade();
            } else {
                Facade.fecharAtividade(especifico);
            }
            
            response.sendRedirect("/ATOA/Atividades?action=Formfechar");
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

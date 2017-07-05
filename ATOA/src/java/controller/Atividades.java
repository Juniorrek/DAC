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
        HttpSession sessionValida = request.getSession();
        Funcionario valida = (Funcionario) sessionValida.getAttribute("logado");
        if (valida == null) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/login.jsp");             
            requestDispatcher.forward(request, response);
        }
        String action = request.getParameter("action");
        
        if ("criar".equals(action)) {
            if (!"Funcionário".equals(valida.getPerfil())) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
                requestDispatcher.forward(request, response); 
            }
            boolean erro = false;
            if ("".equals(request.getParameter("nome"))) {
                erro = true;
                request.setAttribute("erroNome", true);
            }
            //
            if (erro) {
                //REDIRECT N PRESERVA ATTRIBUTO
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Atividades?action=carregar");
                requestDispatcher.forward(request, response);
            } else {
                Atividade atividade = new Atividade();
                atividade.setNome(request.getParameter("nome"));
                atividade.setTipo(Facade.carregarTipo(Integer.parseInt(request.getParameter("tipo"))));
                atividade.setDescricao(request.getParameter("descricao"));
                atividade.setInicio(new Timestamp(System.currentTimeMillis()));

                HttpSession session = request.getSession();
                Funcionario logado = (Funcionario) session.getAttribute("logado");
                atividade.setFuncionario(logado);

                String retorno = Facade.criarAtividade(atividade);

                //response.sendRedirect("/ATOA/Atividades?action=carregar");
                if (retorno != null) {
                    request.setAttribute("msg", retorno.toString());
                }
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Atividades?action=carregar");             
                requestDispatcher.forward(request, response);
            }
        } else if ("carregar".equals(action)) {
            if (!"Funcionário".equals(valida.getPerfil())) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
                requestDispatcher.forward(request, response); 
            }
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            List<Atividade> atividades = Facade.carregarAtividade(logado);
            List<Tipo> tipos = Facade.carregarTipo(logado.getDepartamento());

            request.setAttribute("tipos", tipos);
            request.setAttribute("atividades", atividades);

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/funcionario/atividades.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("solicitarCorrigir".equals(action)) {
            if (!"Funcionário".equals(valida.getPerfil())) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
                requestDispatcher.forward(request, response); 
            }
            boolean erro = false;
            if ("".equals(request.getParameter("nome"))) {
                erro = true;
                request.setAttribute("erroNome2", true);
            }
            if ("".equals(request.getParameter("inicio"))) {
                erro = true;
                request.setAttribute("erroInicio2", true);
            }
            //
            if (erro) {
                //REDIRECT N PRESERVA ATTRIBUTO
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Atividades?action=carregar");
                requestDispatcher.forward(request, response);
            } else {
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
                atividade.setStatus(request.getParameter("status"));
                if (!"FINALIZADA".equals(atividade.getStatus())) {
                    atividade.setFim(null);
                } else {
                    if (atividade.getFim() == null) {
                        atividade.setFim(new Timestamp(System.currentTimeMillis()));
                    }
                }
                
                String retorno = Facade.solicitarCorrigirAtividade(atividade);

                //response.sendRedirect("/ATOA/Atividades?action=carregar");
                if (retorno != null) {
                    request.setAttribute("msg", retorno.toString());
                }
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Atividades?action=carregar");             
                requestDispatcher.forward(request, response);
            }
        } else if ("carregarCorrecoes".equals(action)) {
            if (!"Gerente de Departamento".equals(valida.getPerfil())) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
                requestDispatcher.forward(request, response); 
            }
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            List<Map<String, Atividade>> correcoes = Facade.carregarCorrecoes(logado);
            
            request.setAttribute("correcoes", correcoes);
            
            List<Tipo> tipos = Facade.carregarTipo(logado.getDepartamento());

            request.setAttribute("tipos", tipos);

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/corrigir.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("reprovar".equals(action)) {
            if (!"Gerente de Departamento".equals(valida.getPerfil())) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
                requestDispatcher.forward(request, response); 
            }
            String retorno = Facade.reprovarCorrecao(Integer.parseInt(request.getParameter("id")));
            
            //response.sendRedirect("/ATOA/Atividades?action=carregarCorrecoes");
            if (retorno != null) {
                request.setAttribute("msg", retorno.toString());
            }
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Atividades?action=carregarCorrecoes");             
            requestDispatcher.forward(request, response);
        } else if ("aprovar".equals(action)) {
            if (!"Gerente de Departamento".equals(valida.getPerfil())) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
                requestDispatcher.forward(request, response); 
            }
            String retorno = Facade.aprovarCorrecao(Integer.parseInt(request.getParameter("id")));
            
            if (retorno != null) {
                request.setAttribute("msg", retorno.toString());
            }
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Atividades?action=carregarCorrecoes");             
            requestDispatcher.forward(request, response);
        } else if ("finalizar".equals(action)) {
            if (!"Funcionário".equals(valida.getPerfil())) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
                requestDispatcher.forward(request, response); 
            }
            String retorno = Facade.finalizarAtividade(Integer.parseInt(request.getParameter("id")));

            //response.sendRedirect("/ATOA/Atividades?action=carregar");
            if (retorno != null) {
                request.setAttribute("msg", retorno.toString());
            }
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Atividades?action=carregar");             
            requestDispatcher.forward(request, response);
        } else if ("carregarMes".equals(action)) {
            if (!"Funcionário".equals(valida.getPerfil())) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
                requestDispatcher.forward(request, response); 
            }
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            List<Atividade> atividades = Facade.carregarAtividadeMes(logado);
            
            request.setAttribute("atividades", atividades);
            
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/funcionario/atividades_mes.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("Formfechar".equals(action)) {
            if (!"Gerente de Departamento".equals(valida.getPerfil())) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
                requestDispatcher.forward(request, response); 
            }
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            List<Atividade> atividades = Facade.carregarAtividadeDep(logado);
            List<Funcionario> funcionarios = Facade.carregarFuncionarioDep(logado.getDepartamento());
            
            request.setAttribute("atividades", atividades);
            request.setAttribute("funcionarios", funcionarios);
            
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/fechamento_atividades.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("fechar".equals(action)) {
            if (!"Gerente de Departamento".equals(valida.getPerfil())) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
                requestDispatcher.forward(request, response); 
            }
            HttpSession session = request.getSession();
            Funcionario logado = (Funcionario) session.getAttribute("logado");
            String especifico = request.getParameter("especifico");
            
            String retorno = null;
            if (especifico == null) {
                retorno = Facade.fecharAtividade(logado);
            } else {
                retorno = Facade.fecharAtividade(especifico);
            }
            
            if (retorno != null) {
                request.setAttribute("msg", retorno.toString());
            }
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Atividades?action=Formfechar");             
            requestDispatcher.forward(request, response);
            //response.sendRedirect("/ATOA/Atividades?action=Formfechar");
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

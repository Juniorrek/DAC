package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facade.Facade;
import model.Departamento;

@WebServlet(name = "Departamentos", urlPatterns = {"/Departamentos"})
public class Departamentos extends HttpServlet {

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
            Departamento departamento = new Departamento();
            departamento.setNome(request.getParameter("nome"));
            departamento.setLocalizacao(request.getParameter("localizacao"));

            Facade.criarDepartamento(departamento);

            response.sendRedirect("/RHINDO/Departamentos?action=carregar");
        } else if ("carregar".equals(action)) {
            List<Departamento> departamentos = Facade.carregarDepartamento();

            request.setAttribute("departamentos", departamentos);

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/departamentos.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("editar".equals(action)) {
            Departamento departamento = new Departamento();
            departamento.setId(Integer.parseInt(request.getParameter("id")));
            departamento.setNome(request.getParameter("nome"));
            departamento.setLocalizacao(request.getParameter("localizacao"));
            
            Facade.editarDepartamento(departamento);
            
            response.sendRedirect("/RHINDO/Departamentos?action=carregar");
        } else if ("deletar".equals(action)) {
            Facade.deletarDepartamento(Integer.parseInt(request.getParameter("id")));

            response.sendRedirect("/RHINDO/Departamentos?action=carregar");
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

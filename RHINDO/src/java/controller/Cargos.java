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
import model.Cargo;

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
        
        if ("criar".equals(action)) {
            Cargo cargo = new Cargo();
            cargo.setNome(request.getParameter("nome"));
            cargo.setSalario(Float.parseFloat(request.getParameter("salario")));
            cargo.setRequisitos(request.getParameter("requisitos"));
            cargo.setCarga_trabalho_minima_mes(Integer.parseInt(request.getParameter("carga_trabalho_minima_mes")));
            cargo.setDesconto_impostos_gerais(Integer.parseInt(request.getParameter("desconto_impostos_gerais")));

            Facade.criarCargo(cargo);

            response.sendRedirect("/RHINDO/Cargos?action=carregar");
        } else if ("carregar".equals(action)) {
            List<Cargo> cargos = Facade.carregarCargo();
            request.setAttribute("cargos", cargos);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/cargos.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("editar".equals(action)) {
            Cargo cargo = new Cargo();
            cargo.setId(Integer.parseInt(request.getParameter("id")));
            cargo.setNome(request.getParameter("nome"));
            cargo.setSalario(Float.parseFloat(request.getParameter("salario")));
            cargo.setRequisitos(request.getParameter("requisitos"));
            cargo.setCarga_trabalho_minima_mes(Integer.parseInt(request.getParameter("carga_trabalho_minima_mes")));
            cargo.setDesconto_impostos_gerais(Integer.parseInt(request.getParameter("desconto_impostos_gerais")));
            
            Facade.editarCargo(cargo);
            
            response.sendRedirect("/RHINDO/Cargos?action=carregar");
        } else if ("deletar".equals(action)) {
            Facade.deletarCargo(Integer.parseInt(request.getParameter("id")));

            response.sendRedirect("/RHINDO/Cargos?action=carregar");
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

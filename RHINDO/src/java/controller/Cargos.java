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
import javax.servlet.http.HttpSession;
import model.Cargo;
import model.Funcionario;

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
        HttpSession sessionValida = request.getSession();
        Funcionario valida = (Funcionario) sessionValida.getAttribute("logado");
        if (valida == null) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/login.jsp");             
            requestDispatcher.forward(request, response);
        } else if (!"Gerente de RH".equals(valida.getPerfil())) {
           RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/pagina_inicial.jsp");             
           requestDispatcher.forward(request, response); 
        }
        String action = request.getParameter("action");
        
        if ("criar".equals(action)) {
            boolean erro = false;
            if ("".equals(request.getParameter("nome"))) {
                erro = true;
                request.setAttribute("erroNome", true);
            }
            if ("".equals(request.getParameter("salario"))) {
                erro = true;
                request.setAttribute("erroSalario", true);
            }
            if ("".equals(request.getParameter("carga_trabalho_minima_mes"))) {
                erro = true;
                request.setAttribute("erroCarga_trabalho_minima_mes", true);
            }
            if ("".equals(request.getParameter("desconto_impostos_gerais"))) {
                erro = true;
                request.setAttribute("erroDesconto_impostos_gerais", true);
            }
            //
            if (erro) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Cargos?action=carregar");
                requestDispatcher.forward(request, response);
            } else {
                Cargo cargo = new Cargo();
                cargo.setNome(request.getParameter("nome"));
                cargo.setSalario(Float.parseFloat(request.getParameter("salario").replace("R$ ", "").replace(".", "").replace(",", ".")));
                cargo.setRequisitos(request.getParameter("requisitos"));
                cargo.setCarga_trabalho_minima_mes(Integer.parseInt(request.getParameter("carga_trabalho_minima_mes")));
                cargo.setDesconto_impostos_gerais(Integer.parseInt(request.getParameter("desconto_impostos_gerais")));

                String retorno = Facade.criarCargo(cargo);

                if (retorno != null) {
                    request.setAttribute("msg", retorno.toString());
                }
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Cargos?action=carregar");             
                requestDispatcher.forward(request, response);
            }
        } else if ("carregar".equals(action)) {
            List<Cargo> cargos = Facade.carregarCargo();
            request.setAttribute("cargos", cargos);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/cargos.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("editar".equals(action)) {
            boolean erro = false;
            if ("".equals(request.getParameter("nome"))) {
                erro = true;
                request.setAttribute("erroNome2", true);
            }
            if ("".equals(request.getParameter("salario"))) {
                erro = true;
                request.setAttribute("erroSalario2", true);
            }
            if ("".equals(request.getParameter("carga_trabalho_minima_mes"))) {
                erro = true;
                request.setAttribute("erroCarga_trabalho_minima_mes2", true);
            }
            if ("".equals(request.getParameter("desconto_impostos_gerais"))) {
                erro = true;
                request.setAttribute("erroDesconto_impostos_gerais2", true);
            }
            //
            if (erro) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Cargos?action=carregar");
                requestDispatcher.forward(request, response);
            } else {
                Cargo cargo = new Cargo();
                cargo.setId(Integer.parseInt(request.getParameter("id")));
                cargo.setNome(request.getParameter("nome"));
                cargo.setSalario(Float.parseFloat(request.getParameter("salario").replace("R$ ", "").replace(".", "").replace(",", ".")));
                cargo.setRequisitos(request.getParameter("requisitos"));
                cargo.setCarga_trabalho_minima_mes(Integer.parseInt(request.getParameter("carga_trabalho_minima_mes")));
                cargo.setDesconto_impostos_gerais(Integer.parseInt(request.getParameter("desconto_impostos_gerais")));

                String retorno = Facade.editarCargo(cargo);

                if (retorno != null) {
                    request.setAttribute("msg", retorno.toString());
                }
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Cargos?action=carregar");             
                requestDispatcher.forward(request, response);
            }
        } else if ("deletar".equals(action)) {
            String retorno = Facade.deletarCargo(Integer.parseInt(request.getParameter("id")));

            if (retorno != null) {
                request.setAttribute("msg", retorno.toString());
            }
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Cargos?action=carregar");             
            requestDispatcher.forward(request, response);
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

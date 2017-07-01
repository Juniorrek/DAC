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
import model.Departamento;
import model.Funcionario;

@WebServlet(name = "Funcionarios", urlPatterns = {"/Funcionarios"})
public class Funcionarios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
   private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

   private static int calcularDigito(String str, int[] peso) {
      int soma = 0;
      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
         digito = Integer.parseInt(str.substring(indice,indice+1));
         soma += digito*peso[peso.length-str.length()+indice];
      }
      soma = 11 - soma % 11;
      return soma > 9 ? 0 : soma;
   }

   public static boolean isValidCPF(String cpf) {
      if ((cpf==null) || (cpf.length()!=11)) return false;

      Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
      Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
      return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
   }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("criar".equals(action)) {
            boolean erro = false;
            if(!isValidCPF(request.getParameter("cpf").replace(".", "").replace("-", ""))) {
                erro = true;
                request.setAttribute("erroCpf", true);
            }
            if ("".equals(request.getParameter("cpf"))) {
                erro = true;
                request.setAttribute("erroCpf", true);
            }
            if (!request.getParameter("senha").equals(request.getParameter("confirmacao"))) {
                erro = true;
                request.setAttribute("erroSenha", true);
            }
            //
            if (erro) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Funcionarios?action=carregar");
                requestDispatcher.forward(request, response);
            } else {
                Departamento departamento = Facade.carregarDepartamento(Integer.parseInt(request.getParameter("departamento")));

                Cargo cargo = Facade.carregarCargo(Integer.parseInt(request.getParameter("cargo")));

                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(request.getParameter("cpf").replace(".", "").replace("-", ""));
                funcionario.setNome(request.getParameter("nome"));
                funcionario.setRg(request.getParameter("rg").replace(".", "").replace("-", ""));
                funcionario.setCelular(request.getParameter("celular").replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
                funcionario.setEmail(request.getParameter("email"));
                funcionario.setRua(request.getParameter("rua"));
                funcionario.setNumero(Integer.parseInt(request.getParameter("numero")));
                funcionario.setBairro(request.getParameter("bairro"));
                funcionario.setCep(request.getParameter("cep").replace(".", "").replace("-", ""));
                funcionario.setCidade(request.getParameter("cidade"));
                funcionario.setEstado(request.getParameter("estado"));
                funcionario.setDepartamento(departamento);
                funcionario.setCargo(cargo);
                funcionario.setPerfil(request.getParameter("perfil"));
                funcionario.setSenha(request.getParameter("senha"));

                Facade.criarFuncionario(funcionario);

                response.sendRedirect("/RHINDO/Funcionarios?action=carregar");
            }
        } else if ("carregar".equals(action)) {
            List<Funcionario> funcionarios = Facade.carregarFuncionario();
            List<Departamento> departamentos = Facade.carregarDepartamento();
            List<Cargo> cargos = Facade.carregarCargo();

            request.setAttribute("funcionarios", funcionarios);
            request.setAttribute("departamentos", departamentos);
            request.setAttribute("cargos", cargos);

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view/gerente/funcionarios.jsp");             
            requestDispatcher.forward(request, response);
        } else if ("editar".equals(action)) {
            boolean erro = false;
            if (!request.getParameter("senha").equals(request.getParameter("confirmacao"))) {
                erro = true;
                request.setAttribute("erroSenha2", true);
            }
            //
            if (erro) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Funcionarios?action=carregar");
                requestDispatcher.forward(request, response);
            } else {
                Departamento departamento = Facade.carregarDepartamento(Integer.parseInt(request.getParameter("departamento")));

                Cargo cargo = Facade.carregarCargo(Integer.parseInt(request.getParameter("cargo")));

                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(request.getParameter("cpf").replace(".", "").replace("-", ""));
                funcionario.setNome(request.getParameter("nome"));
                funcionario.setRg(request.getParameter("rg").replace(".", "").replace("-", ""));
                funcionario.setCelular(request.getParameter("celular").replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
                funcionario.setEmail(request.getParameter("email"));
                funcionario.setRua(request.getParameter("rua"));
                funcionario.setNumero(Integer.parseInt(request.getParameter("numero")));
                funcionario.setBairro(request.getParameter("bairro"));
                funcionario.setCep(request.getParameter("cep").replace(".", "").replace("-", ""));
                funcionario.setCidade(request.getParameter("cidade"));
                funcionario.setEstado(request.getParameter("estado"));
                funcionario.setDepartamento(departamento);
                funcionario.setCargo(cargo);
                funcionario.setPerfil(request.getParameter("perfil"));
                funcionario.setSenha(request.getParameter("senha"));

                Facade.editarFuncionario(funcionario);

                response.sendRedirect("/RHINDO/Funcionarios?action=carregar");
            }
        } else if ("deletar".equals(action)) {
            Facade.deletarFuncionario(request.getParameter("cpf"));

            response.sendRedirect("/RHINDO/Funcionarios?action=carregar");
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

package facade;

import DAO.FolhaDAO;
import java.util.List;
import DAO.CargoDAO;
import DAO.DepartamentoDAO;
import DAO.FuncionarioDAO;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Cargo;
import model.Departamento;
import model.Folha;
import model.Funcionario;
import model.Holerite;

public class Facade {
    public static String criarFuncionario(Funcionario funcionario) {
        return FuncionarioDAO.criar(funcionario);
    }
    
    public static List<Funcionario> carregarFuncionario() {
        return FuncionarioDAO.carregar();
    }
    
    public static List<Funcionario> carregarFuncionario(int id) {
        return FuncionarioDAO.carregarDep(id);
    }
    
    public static Funcionario carregarFuncionario(String cpf, String senha) {
        return FuncionarioDAO.carregar(cpf, senha);
    }
    
    public static Funcionario carregarFuncionario(String cpf) {
        return FuncionarioDAO.carregar(cpf);
    }
    
    public static String editarFuncionario(Funcionario funcionario) {
        return FuncionarioDAO.editar(funcionario);
    }
    
    public static String deletarFuncionario(String cpf) {
        return FuncionarioDAO.deletar(cpf);
    }
    
    public static String criarDepartamento(Departamento departamento) {
        return DepartamentoDAO.criar(departamento);
    }
    
    public static List<Departamento> carregarDepartamento() {
        return DepartamentoDAO.carregar();
    }
    
    public static Departamento carregarDepartamento(int id) {
        return DepartamentoDAO.carregar(id);
    }
    
    public static String editarDepartamento(Departamento departamento) {
        return DepartamentoDAO.editar(departamento);
    }
    
    public static String deletarDepartamento(int id) {
        return DepartamentoDAO.deletar(id);
    }
    
    public static void notificarDepartamento(int id) {
        DepartamentoDAO.notificar(id);
    }
    
    public static String criarCargo(Cargo cargo) {
        return CargoDAO.criar(cargo);
    }
    
    public static List<Cargo> carregarCargo() {
        return CargoDAO.carregar();
    }
    
    public static Cargo carregarCargo(int id) {
        return CargoDAO.carregar(id);
    }
    
    public static String editarCargo(Cargo cargo) {
        return CargoDAO.editar(cargo);
    }
    
    public static String deletarCargo(int id) {
        return CargoDAO.deletar(id);
    }
    
    public static String fecharFolha(int mes) {
        Client client = ClientBuilder.newClient();
        Response resp = client
            .target("http://localhost:8084/ATOA/webresources/atividades/fechar/" + mes)
            .request(MediaType.APPLICATION_JSON)
            .get();
        List<Folha> lista =
            resp.readEntity(
            new GenericType<List<Folha>>() {}
            );
        if (lista.isEmpty()) {
            return "Sem atividades para serem consolidadas neste mês !!!";
        } else {
            FolhaDAO.fechar(lista);
            return "Folha fechada com sucesso !!!";
        }
    }
    
    public static List<Folha> horasTrabalhadas(String de, String ate, Funcionario logado) {
        Client client = ClientBuilder.newClient();
        Response resp = client
            .target("http://localhost:8084/ATOA/webresources/atividades/horas_trabalhadas/" + de + "/" + ate + "/" + logado.getCpf())
            .request(MediaType.APPLICATION_JSON)
            .get();
        List<Folha> lista =
            resp.readEntity(
            new GenericType<List<Folha>>() {}
            );
        
        return lista;
    }
    
    public static Holerite obterHolerite(Funcionario funcionario, int mes) {
        return FolhaDAO.obterHolerite(funcionario, mes);
    }
    
    public static List<Folha> hrsTrabalhadasDepMes(int mes) {
        Client client = ClientBuilder.newClient();
        Response resp = client
            .target("http://localhost:8084/ATOA/webresources/atividades/horas_trabalhadas_dep_mes/" + mes)
            .request(MediaType.APPLICATION_JSON)
            .get();
        List<Folha> lista =
            resp.readEntity(
            new GenericType<List<Folha>>() {}
            );
        
        return lista;
    }
    
    public static List<Folha> funcncumpriu(int mes) {
        Client client = ClientBuilder.newClient();
        Response resp = client
            .target("http://localhost:8084/ATOA/webresources/atividades/funcncumpriu/" + mes)
            .request(MediaType.APPLICATION_JSON)
            .get();
        List<Folha> lista =
            resp.readEntity(
            new GenericType<List<Folha>>() {}
            );
        
        return lista;
    }
}

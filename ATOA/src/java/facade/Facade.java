package facade;

import DAO.AtividadeDAO;
import java.util.List;
import DAO.TipoDAO;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Atividade;
import model.Cargo;
import model.Departamento;
import model.Funcionario;
import model.Tipo;

public class Facade {
    /*public static List<Funcionario> carregarFuncionario() {
        return null;
    }*/
    
    public static Funcionario carregarFuncionario(String cpf) {
        Client client = ClientBuilder.newClient();
        Funcionario funcionario = client
                    .target("http://localhost:8084/RHINDO/webresources/funcionarios/" + cpf)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Funcionario.class);
            
        return funcionario;
    }
    
    public static void editarFuncionario(Funcionario funcionario) {
        Client client = ClientBuilder.newClient();
        client.target("http://localhost:8084/RHINDO/webresources/funcionarios/")
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.json(funcionario), Funcionario.class);
    }
    
    public static List<Departamento> carregarDepartamento() {
        Client client = ClientBuilder.newClient();
        Response resp = client
            .target("http://localhost:8084/RHINDO/webresources/departamentos/")
            .request(MediaType.APPLICATION_JSON)
            .get();
        List<Departamento> lista =
            resp.readEntity(
            new GenericType<List<Departamento>>() {}
            );
        
        return lista;
    }
    
    public static Departamento carregarDepartamento(int id) {
        Client client = ClientBuilder.newClient();
        Departamento departamento = client
                    .target("http://localhost:8084/RHINDO/webresources/departamentos/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Departamento.class);
            
        return departamento;
    }
    
    public static List<Cargo> carregarCargo() {
        Client client = ClientBuilder.newClient();
        Response resp = client
            .target("http://localhost:8084/RHINDO/webresources/cargos/")
            .request(MediaType.APPLICATION_JSON)
            .get();
        List<Cargo> lista =
            resp.readEntity(
            new GenericType<List<Cargo>>() {}
            );
        
        return lista;
    }
    
    public static Cargo carregarCargo(int id) {
        Client client = ClientBuilder.newClient();
        Cargo cargo = client
                    .target("http://localhost:8084/RHINDO/webresources/cargos/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Cargo.class);
            
        return cargo;
    }
    
    //QUALIDADE
    public static void criarTipo(Tipo tipo) {
        TipoDAO.criar(tipo);
    }
    
    public static List<Tipo> carregarTipo() {
        return TipoDAO.carregar();
    }
    
    /*public static Tipo carregarTipo(int id) {
        return TipoDAO.carregar(id);
    }*/
    
    public static void editarTipo(Tipo tipo) {
        TipoDAO.editar(tipo);
    }
    
    public static void deletarTipo(int id) {
        TipoDAO.deletar(id);
    }
    
    /*public static void criarAtividade(Atividade atividade) {
        AtividadeDAO.criar(atividade);
    }
    
    public static List<Atividade> carregarAtividade(Funcionario funcionario) {
        return AtividadeDAO.carregar(funcionario);
    }
    
    public static Atividade carregarAtividade(int id) {
        return AtividadeDAO.carregar(id);
    }
    
    public static void editarAtividade(Atividade atividade) {
        AtividadeDAO.editar(atividade);
    }*/
    
    /*public static void deletarAtividade(int id) {
        AtividadeDAO.deletar(id);
    }*/
}

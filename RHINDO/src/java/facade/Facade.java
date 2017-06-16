package facade;

import java.util.List;
import DAO.CargoDAO;
import DAO.DepartamentoDAO;
import DAO.FuncionarioDAO;
import model.Cargo;
import model.Departamento;
import model.Funcionario;

public class Facade {
    public static void criarFuncionario(Funcionario funcionario) {
        FuncionarioDAO.criar(funcionario);
    }
    
    public static List<Funcionario> carregarFuncionario() {
        return FuncionarioDAO.carregar();
    }
    
    public static Funcionario carregarFuncionario(String cpf) {
        return FuncionarioDAO.carregar(cpf);
    }
    
    public static void editarFuncionario(Funcionario funcionario) {
        FuncionarioDAO.editar(funcionario);
    }
    
    public static void deletarFuncionario(String cpf) {
        FuncionarioDAO.deletar(cpf);
    }
    
    public static void criarDepartamento(Departamento departamento) {
        DepartamentoDAO.criar(departamento);
    }
    
    public static List<Departamento> carregarDepartamento() {
        return DepartamentoDAO.carregar();
    }
    
    public static Departamento carregarDepartamento(int id) {
        return DepartamentoDAO.carregar(id);
    }
    
    public static void editarDepartamento(Departamento departamento) {
        DepartamentoDAO.editar(departamento);
    }
    
    public static void deletarDepartamento(int id) {
        DepartamentoDAO.deletar(id);
    }
    
    public static void criarCargo(Cargo cargo) {
        CargoDAO.criar(cargo);
    }
    
    public static List<Cargo> carregarCargo() {
        return CargoDAO.carregar();
    }
    
    public static Cargo carregarCargo(int id) {
        return CargoDAO.carregar(id);
    }
    
    public static void editarCargo(Cargo cargo) {
        CargoDAO.editar(cargo);
    }
    
    public static void deletarCargo(int id) {
        CargoDAO.deletar(id);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DAO.AtividadeDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.CargoDAO;
import DAO.DepartamentoDAO;
import DAO.FuncionarioDAO;
import model.Atividade;
import model.Cargo;
import model.Departamento;
import model.Funcionario;

/**
 *
 * @author Fornalha
 */
public class Facade {
    public static List<Funcionario> getFuncionarios() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        funcionarios = FuncionarioDAO.getFuncionarios();
        
        return funcionarios;
    }
    
    public static Funcionario getFuncionario(String cpf) throws SQLException {
        return FuncionarioDAO.getFuncionario(cpf);
    }
    
    public static List<Departamento> getDepartamentos() throws SQLException {
        List<Departamento> departamentos = new ArrayList<Departamento>();
        
        departamentos = DepartamentoDAO.getDepartamentos();
        
        return departamentos;
    }
    
    public static Departamento getDepartamento(int id) throws SQLException {     
        return DepartamentoDAO.getDepartamento(id);
    }
    
    public static List<Cargo> getCargos() throws SQLException {
        List<Cargo> cargos = new ArrayList<Cargo>();
        
        cargos = CargoDAO.getCargos();
        
        return cargos;
    }
    
    public static Cargo getCargo(int id) throws SQLException {     
        return CargoDAO.getCargo(id);
    }
    
    public static void updateFuncionario(Funcionario funcionario) throws SQLException {
        FuncionarioDAO.updateFuncionario(funcionario);
    }
    
    public static void insertFuncionario(Funcionario funcionario) throws SQLException {
        FuncionarioDAO.insertFuncionario(funcionario);
    }
    
    public static void deleteFuncionario(Funcionario funcionario) throws SQLException {
        FuncionarioDAO.deleteFuncionario(funcionario);
    }
    
    public static void insertDepartamento(Departamento departamento) throws SQLException {
        DepartamentoDAO.insertDepartamento(departamento);
    }
    
    public static void updateDepartamento(Departamento departamento) throws SQLException {
        DepartamentoDAO.updateDepartamento(departamento);
    }
    
    public static void deleteDepartamento(Departamento departamento) throws SQLException {
        DepartamentoDAO.deleteDepartamento(departamento);
    }
    
    public static void insertCargo(Cargo cargo) throws SQLException {
        CargoDAO.insertCargo(cargo);
    }
    
    public static void updateCargo(Cargo cargo) throws SQLException {
        CargoDAO.updateCargo(cargo);
    }
    
    public static void deleteCargo(Cargo cargo) throws SQLException {
        CargoDAO.deleteCargo(cargo);
    }
    
    public static List<Atividade> getAtividades(int id_departamento) throws SQLException {
        return AtividadeDAO.getAtividades(id_departamento);
    }
    
    public static Atividade getAtividade(int id) throws SQLException {
        return AtividadeDAO.getAtividade(id);
    }
    
    public static void insertAtividade(Atividade atividade) throws SQLException {
        AtividadeDAO.insertAtividade(atividade);
    }

    public static void updateAtividade(Atividade atividade) throws SQLException {
        AtividadeDAO.updateAtividade(atividade);
    }

    public static void deleteAtividade(Atividade atividade) throws SQLException {
        AtividadeDAO.deleteAtividade(atividade);
    }
}

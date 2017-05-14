/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhindo.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import rhindo.DAO.CargoDAO;
import rhindo.DAO.DepartamentoDAO;
import rhindo.DAO.FuncionarioDAO;
import rhindo.model.Cargo;
import rhindo.model.Departamento;
import rhindo.model.Funcionario;

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
}

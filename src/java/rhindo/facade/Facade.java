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
    
    public static List<Cargo> getCargos() throws SQLException {
        List<Cargo> cargos = new ArrayList<Cargo>();
        
        cargos = CargoDAO.getCargos();
        
        return cargos;
    }
}

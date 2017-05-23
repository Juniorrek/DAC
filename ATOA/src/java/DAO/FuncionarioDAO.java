/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cargo;
import model.Departamento;
import model.Funcionario;

/**
 *
 * @author Fornalha
 */
public class FuncionarioDAO {
    public static List<Funcionario> getFuncionarios() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT F.id, F.nome, F.cpf, F.rg, F.celular, F.email, F.rua, F.numero, F.bairro, F.cep, F.cidade, F.estado, F.perfil, D.id, D.nome, D.localizacao, C.id, C.nome, C.salario, C.requisitos, C.imposto_desconto, C.carga_trabalho_minima_mes "
                                             + "FROM Funcionario F, Departamento D, Cargo C "
                                             + "WHERE F.id_departamento = D.id AND F.id_cargo = C.id");
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Funcionario funcionario = new Funcionario(resultSet.getInt("F.id"),
                                                 resultSet.getString("F.nome"),
                                                 resultSet.getString("cpf"),
                                                 resultSet.getString("rg"),
                                                 resultSet.getString("celular"),
                                                 resultSet.getString("email"),
                                                 resultSet.getString("rua"),
                                                 resultSet.getInt("numero"),
                                                 resultSet.getString("bairro"),
                                                 resultSet.getString("cep"),
                                                 resultSet.getString("cidade"),
                                                 resultSet.getString("estado"),
                                                 resultSet.getString("perfil"),
                                                 new Departamento(resultSet.getInt("D.id"), resultSet.getString("D.nome"), resultSet.getString("D.localizacao")),
                                                 new Cargo(resultSet.getInt("C.id"), resultSet.getString("C.nome"), resultSet.getFloat("C.salario"), resultSet.getString("C.requisitos"), resultSet.getInt("C.imposto_desconto"), resultSet.getInt("C.carga_trabalho_minima_mes")));
                funcionarios.add(funcionario);
                
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Erro. Origem="+exception.getMessage());
        } finally {
            if (stmt != null)
                try { stmt.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar stmt. Ex="+exception.getMessage()); }
            if (connection != null)
                try { connection.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar conexão. Ex="+exception.getMessage()); }
        }
        
        return funcionarios;
    }
    
    public static Funcionario getFuncionario(String cpf) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT F.id, F.nome, F.cpf, F.rg, F.celular, F.email, F.rua, F.numero, F.bairro, F.cep, F.cidade, F.estado, F.perfil, D.id, D.nome, D.localizacao, C.id, C.nome, C.salario, C.requisitos, C.imposto_desconto, C.carga_trabalho_minima_mes "
                                             + "FROM Funcionario F, Departamento D, Cargo C "
                                             + "WHERE F.cpf = ? AND F.id_departamento = D.id AND F.id_cargo = C.id");
            stmt.setString(1, cpf);
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                Funcionario funcionario = new Funcionario(resultSet.getInt("F.id"),
                                                 resultSet.getString("F.nome"),
                                                 resultSet.getString("cpf"),
                                                 resultSet.getString("rg"),
                                                 resultSet.getString("celular"),
                                                 resultSet.getString("email"),
                                                 resultSet.getString("rua"),
                                                 resultSet.getInt("numero"),
                                                 resultSet.getString("bairro"),
                                                 resultSet.getString("cep"),
                                                 resultSet.getString("cidade"),
                                                 resultSet.getString("estado"),
                                                 resultSet.getString("perfil"),
                                                 new Departamento(resultSet.getInt("D.id"), resultSet.getString("D.nome"), resultSet.getString("D.localizacao")),
                                                 new Cargo(resultSet.getInt("C.id"), resultSet.getString("C.nome"), resultSet.getFloat("C.salario"), resultSet.getString("C.requisitos"), resultSet.getInt("C.imposto_desconto"), resultSet.getInt("C.carga_trabalho_minima_mes")));
                return funcionario;
            } else {
                return null;
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Erro. Origem="+exception.getMessage());
        } finally {
            if (stmt != null)
                try { stmt.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar stmt. Ex="+exception.getMessage()); }
            if (connection != null)
                try { connection.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar conexão. Ex="+exception.getMessage()); }
        }
    }
    
    public static Funcionario getFuncionario(int id) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT F.id, F.nome, F.cpf, F.rg, F.celular, F.email, F.rua, F.numero, F.bairro, F.cep, F.cidade, F.estado, F.perfil, D.id, D.nome, D.localizacao, C.id, C.nome, C.salario, C.requisitos, C.imposto_desconto, C.carga_trabalho_minima_mes "
                                             + "FROM Funcionario F, Departamento D, Cargo C "
                                             + "WHERE F.id = ? AND F.id_departamento = D.id AND F.id_cargo = C.id");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                Funcionario funcionario = new Funcionario(resultSet.getInt("F.id"),
                                                 resultSet.getString("F.nome"),
                                                 resultSet.getString("cpf"),
                                                 resultSet.getString("rg"),
                                                 resultSet.getString("celular"),
                                                 resultSet.getString("email"),
                                                 resultSet.getString("rua"),
                                                 resultSet.getInt("numero"),
                                                 resultSet.getString("bairro"),
                                                 resultSet.getString("cep"),
                                                 resultSet.getString("cidade"),
                                                 resultSet.getString("estado"),
                                                 resultSet.getString("perfil"),
                                                 new Departamento(resultSet.getInt("D.id"), resultSet.getString("D.nome"), resultSet.getString("D.localizacao")),
                                                 new Cargo(resultSet.getInt("C.id"), resultSet.getString("C.nome"), resultSet.getFloat("C.salario"), resultSet.getString("C.requisitos"), resultSet.getInt("C.imposto_desconto"), resultSet.getInt("C.carga_trabalho_minima_mes")));
                return funcionario;
            } else {
                return null;
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Erro. Origem="+exception.getMessage());
        } finally {
            if (stmt != null)
                try { stmt.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar stmt. Ex="+exception.getMessage()); }
            if (connection != null)
                try { connection.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar conexão. Ex="+exception.getMessage()); }
        }
    }
    
    public static void updateFuncionario(Funcionario funcionario) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("UPDATE Funcionario "
                                             + "SET nome = ?, cpf = ?, rg = ?, celular = ?, email = ?, rua = ?, numero = ?, bairro = ?, cep = ?, cidade = ?, estado = ?, perfil = ?, id_departamento = ?, id_cargo = ? "
                                             + "WHERE Funcionario.cpf = ?");
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getRg());
            stmt.setString(4, funcionario.getCelular());
            stmt.setString(5, funcionario.getEmail());
            stmt.setString(6, funcionario.getRua());
            stmt.setString(7, Integer.toString(funcionario.getNumero()));
            stmt.setString(8, funcionario.getBairro());
            stmt.setString(9, funcionario.getCep());
            stmt.setString(10, funcionario.getCidade());
            stmt.setString(11, funcionario.getEstado());
            stmt.setString(12, funcionario.getPerfil());
            stmt.setString(13, Integer.toString(funcionario.getDepartamento().getId()));
            stmt.setString(14, Integer.toString(funcionario.getCargo().getId()));
            stmt.setString(15, funcionario.getCpf());
            stmt.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Erro. Origem="+exception.getMessage());
        } finally {
            if (stmt != null)
                try { stmt.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar stmt. Ex="+exception.getMessage()); }
            if (connection != null)
                try { connection.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar conexão. Ex="+exception.getMessage()); }
        }
    }
    
    public static void insertFuncionario(Funcionario funcionario) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("INSERT INTO Funcionario (nome, cpf, rg, celular, email, rua, numero, bairro, cep, cidade, estado, perfil, id_departamento, id_cargo) "
                                             + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getRg());
            stmt.setString(4, funcionario.getCelular());
            stmt.setString(5, funcionario.getEmail());
            stmt.setString(6, funcionario.getRua());
            stmt.setString(7, Integer.toString(funcionario.getNumero()));
            stmt.setString(8, funcionario.getBairro());
            stmt.setString(9, funcionario.getCep());
            stmt.setString(10, funcionario.getCidade());
            stmt.setString(11, funcionario.getEstado());
            stmt.setString(12, funcionario.getPerfil());
            stmt.setString(13, Integer.toString(funcionario.getDepartamento().getId()));
            stmt.setString(14, Integer.toString(funcionario.getCargo().getId()));
            stmt.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Erro. Origem="+exception.getMessage());
        } finally {
            if (stmt != null)
                try { stmt.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar stmt. Ex="+exception.getMessage()); }
            if (connection != null)
                try { connection.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar conexão. Ex="+exception.getMessage()); }
        }
    }
    
    public static void deleteFuncionario(Funcionario funcionario) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("DELETE FROM Funcionario "
                                             + "WHERE cpf = ?");
            stmt.setString(1, funcionario.getCpf());
            stmt.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Erro. Origem="+exception.getMessage());
        } finally {
            if (stmt != null)
                try { stmt.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar stmt. Ex="+exception.getMessage()); }
            if (connection != null)
                try { connection.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar conexão. Ex="+exception.getMessage()); }
        }
    }
}

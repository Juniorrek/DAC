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
import model.Atividade;
import model.Departamento;
import model.Funcionario;

/**
 *
 * @author Fornalha
 */
public class AtividadeDAO {
    public static List<Atividade> getAtividades(int id_departamento) throws SQLException {
        List<Atividade> atividades = new ArrayList<Atividade>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT A.* "
                                             + "FROM Atividade A "
                                             + "WHERE A.id_departamento = ?");
            stmt.setInt(1, id_departamento);
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                //NEM PRECISA PEGAR TODOS OS DADOS, SÓ NOME E ID...
                int id_funcionario = resultSet.getInt("A.id_funcionario");
                Funcionario funcionario = null;
                if (id_funcionario != 0) {
                    funcionario = FuncionarioDAO.getFuncionario(id_funcionario);
                }
                Atividade atividade = new Atividade(resultSet.getInt("A.id"),
                                                 resultSet.getString("A.nome"),
                                                 resultSet.getString("A.descricao"),
                                                 resultSet.getDate("A.inicio"),
                                                 resultSet.getDate("A.fim"),
                                                 null,
                                                 funcionario);
                atividades.add(atividade);
                
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
        
        return atividades;
    }
    
    public static Atividade getAtividade(int id) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT A.* "
                                             + "FROM Atividade A "
                                             + "WHERE A.id = ?");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                return new Atividade(resultSet.getInt("A.id"),
                                                 resultSet.getString("A.nome"),
                                                 resultSet.getString("A.descricao"),
                                                 resultSet.getDate("A.inicio"),
                                                 resultSet.getDate("A.fim"),
                                                 null,
                                                 null);
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
        
    public static void insertAtividade(Atividade atividade) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("INSERT INTO Atividade (nome, descricao, id_departamento) "
                                             + "VALUES (?, ?, ?)");
            stmt.setString(1, atividade.getNome());
            stmt.setString(2, atividade.getDescricao());
            stmt.setInt(3, atividade.getDepartamento().getId());
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

    public static void updateAtividade(Atividade atividade) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("UPDATE Atividade "
                                             + "SET nome = ?, descricao = ? "
                                             + "WHERE id = ? ");
            stmt.setString(1, atividade.getNome());
            stmt.setString(2, atividade.getDescricao());
            stmt.setInt(3, atividade.getId());
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

    public static void deleteAtividade(Atividade atividade) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("DELETE FROM Atividade "
                                             + "WHERE id = ? ");
            stmt.setInt(1, atividade.getId());
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

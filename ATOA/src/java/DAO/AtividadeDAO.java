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
    /*public static void criar(Atividade atividade) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("INSERT INTO Atividade (nome, descricao, tipo, inicio, fim, funcionario, status) "
                                             + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, atividade.getNome());
            stmt.setString(2, atividade.getDescricao());
            stmt.setInt(3, atividade.getTipo().getId());
            stmt.setDate(4, atividade.getInicio());
            stmt.setDate(5, atividade.getFim());
            stmt.setInt(6, atividade.getFuncionario().getId());
            stmt.setInt(7, 0);
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
    
    public static List<Atividade> carregar(Funcionario funcionario) {
        List<Atividade> atividades = new ArrayList<Atividade>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Atividade ");
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Atividade atividade = new Atividade();
                atividade.setId(resultSet.getInt("id"));
                atividade.setNome(resultSet.getString("nome"));
                atividade.setDescricao(resultSet.getString("descricao"));
                atividade.setTipo(TipoDAO.carregar(resultSet.getInt("tipo")));
                atividade.setInicio(resultSet.getDate("inicio"));
                atividade.setFim(resultSet.getDate("fim"));
                atividade.setFuncionario(funcionario);
                atividade.setStatus(resultSet.getInt("status"));
                
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
    TALVEZ TIRAR
    public static Tipo carregar(int id) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Tipo "
                                             + "WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                Tipo tipo = new Tipo();
                tipo.setId(Integer.parseInt(resultSet.getString("id")));
                tipo.setNome(resultSet.getString("nome"));
                tipo.setDescricao(resultSet.getString("descricao"));
                
                return tipo;
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

    public static void editar(Atividade atividade) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("UPDATE Atividade "
                                             + "SET nome = ?, descricao = ?, tipo = ?, inicio = ?, fim = ?, funcionario = ?, status = ? "
                                             + "WHERE id = ? ");
            stmt.setString(1, atividade.getNome());
            stmt.setString(2, atividade.getDescricao());
            stmt.setInt(3, atividade.getTipo().getId());
            stmt.setDate(4, atividade.getInicio());
            stmt.setDate(5, atividade.getFim());
            stmt.setInt(6, atividade.getFuncionario().getId());
            stmt.setInt(7, atividade.getStatus());
            stmt.setInt(8, atividade.getId());
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
    }*/

    /*public static void deletar(int id) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("DELETE FROM Atividade "
                                             + "WHERE id = ? ");
            stmt.setInt(1, id);
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
    }*/
}

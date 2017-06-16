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
import model.Tipo;

/**
 *
 * @author Fornalha
 */
public class TipoDAO {
    public static void criar(Tipo tipo) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("INSERT INTO Tipo (nome, descricao) "
                                             + "VALUES (?, ?)");
            stmt.setString(1, tipo.getNome());
            stmt.setString(2, tipo.getDescricao());
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
    
    public static List<Tipo> carregar() {
        List<Tipo> tipos = new ArrayList<Tipo>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Tipo ");
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Tipo tipo = new Tipo();
                tipo.setId(resultSet.getInt("id"));
                tipo.setNome(resultSet.getString("nome"));
                tipo.setDescricao(resultSet.getString("descricao"));
                
                tipos.add(tipo);
                
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
        
        return tipos;
    }

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
                tipo.setId(resultSet.getInt("id"));
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

    public static void editar(Tipo tipo) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("UPDATE Tipo "
                                             + "SET nome = ?, descricao = ? "
                                             + "WHERE id = ? ");
            stmt.setString(1, tipo.getNome());
            stmt.setString(2, tipo.getDescricao());
            stmt.setInt(3, tipo.getId());
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

    public static void deletar(int id) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("DELETE FROM Tipo "
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
    }
}

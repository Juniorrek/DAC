/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhindo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import rhindo.model.Cargo;

/**
 *
 * @author Fornalha
 */
public class CargoDAO {
    public static List<Cargo> getCargos() throws SQLException {
        List<Cargo> cargos = new ArrayList<Cargo>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Cargo ");
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Cargo cargo = new Cargo(resultSet.getInt("id"),
                                        resultSet.getString("nome"),
                                        resultSet.getFloat("salario"),
                                        resultSet.getString("requisitos"),
                                        resultSet.getInt("imposto_desconto"),
                                        resultSet.getInt("carga_trabalho_minima_mes"));
                cargos.add(cargo);
                
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
        
        return cargos;
    }
    
    public static Cargo getCargo(int id) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Cargo C "
                                             + "WHERE C.id = ?");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                return new Cargo(resultSet.getInt("id"),
                                    resultSet.getString("nome"),
                                    resultSet.getFloat("salario"),
                                    resultSet.getString("requisitos"),
                                    resultSet.getInt("imposto_desconto"),
                                    resultSet.getInt("carga_trabalho_minima_mes"));
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
    
    public static void insertCargo(Cargo cargo) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("INSERT INTO Cargo (nome, salario, requisitos, imposto_desconto, carga_trabalho_minima_mes) "
                                             + "VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, cargo.getNome());
            stmt.setFloat(2, cargo.getSalario());
            stmt.setString(3, cargo.getRequisitos());
            stmt.setInt(4, cargo.getImposto_desconto());
            stmt.setInt(5, cargo.getCarga_trabalho_minima_mes());
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
    
    public static void updateCargo(Cargo cargo) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("UPDATE Cargo "
                                             + "SET nome = ?, salario = ?, requisitos = ?, imposto_desconto = ?, carga_trabalho_minima_mes = ? "
                                             + "WHERE id = ? ");
            stmt.setString(1, cargo.getNome());
            stmt.setFloat(2, cargo.getSalario());
            stmt.setString(3, cargo.getRequisitos());
            stmt.setInt(4, cargo.getImposto_desconto());
            stmt.setInt(5, cargo.getCarga_trabalho_minima_mes());
            stmt.setInt(6, cargo.getId());
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
    
    public static void deleteCargo(Cargo cargo) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("DELETE FROM Cargo "
                                             + "WHERE id = ? ");
            stmt.setInt(1, cargo.getId());
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

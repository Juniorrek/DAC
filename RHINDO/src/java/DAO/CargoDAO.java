package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cargo;

public class CargoDAO {
    public static void criar(Cargo cargo) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("INSERT INTO Cargo (nome, salario, requisitos, carga_trabalho_minima_mes, desconto_impostos_gerais) "
                                             + "VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, cargo.getNome());
            stmt.setFloat(2, cargo.getSalario());
            stmt.setString(3, cargo.getRequisitos());
            stmt.setInt(4, cargo.getCarga_trabalho_minima_mes());
            stmt.setInt(5, cargo.getDesconto_impostos_gerais());
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
    
    public static List<Cargo> carregar() {
        List<Cargo> cargos = new ArrayList<Cargo>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Cargo ");
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Cargo cargo = new Cargo();
                cargo.setId(resultSet.getInt("id"));
                cargo.setNome(resultSet.getString("nome"));
                cargo.setSalario(resultSet.getFloat("salario"));
                cargo.setRequisitos(resultSet.getString("requisitos"));
                cargo.setCarga_trabalho_minima_mes(resultSet.getInt("carga_trabalho_minima_mes"));
                cargo.setDesconto_impostos_gerais(resultSet.getInt("desconto_impostos_gerais"));
                
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

    public static Cargo carregar(int id) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Cargo "
                                             + "WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                Cargo cargo = new Cargo();
                cargo.setId(resultSet.getInt("id"));
                cargo.setNome(resultSet.getString("nome"));
                cargo.setSalario(resultSet.getFloat("salario"));
                cargo.setRequisitos(resultSet.getString("requisitos"));
                cargo.setCarga_trabalho_minima_mes(resultSet.getInt("carga_trabalho_minima_mes"));
                cargo.setDesconto_impostos_gerais(resultSet.getInt("desconto_impostos_gerais"));
                
                return cargo;
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

    public static void editar(Cargo cargo) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("UPDATE Cargo "
                                             + "SET nome = ?, salario = ?, requisitos = ?, carga_trabalho_minima_mes = ?, desconto_impostos_gerais = ? "
                                             + "WHERE id = ? ");
            stmt.setString(1, cargo.getNome());
            stmt.setFloat(2, cargo.getSalario());
            stmt.setString(3, cargo.getRequisitos());
            stmt.setInt(4, cargo.getCarga_trabalho_minima_mes());
            stmt.setInt(5, cargo.getDesconto_impostos_gerais());
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

    public static void deletar(int id) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("DELETE FROM Cargo "
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

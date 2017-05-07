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
import rhindo.model.Funcionario;

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
            stmt = connection.prepareStatement("SELECT F.nome, F.cpf, F.rg, F.celular, F.email, F.rua, F.numero, F.bairro, F.cep, F.cidade, F.estado, F.perfil, D.nome, C.nome "
                                             + "FROM Funcionario F, Departamento D, Cargo C "
                                             + "WHERE F.id_departamento = D.id AND F.id_cargo = C.id");
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Funcionario funcionario = new Funcionario(resultSet.getString("F.nome"),
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
                                                 resultSet.getString("perfil"));
                funcionario.setDepartamento(resultSet.getString("D.nome"));
                funcionario.setCargo(resultSet.getString("C.nome"));
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
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Funcionario F "
                                             + "WHERE F.cpf = ?");
            stmt.setString(1, cpf);
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                return new Funcionario(resultSet.getString("F.nome"),
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
                                                 resultSet.getString("perfil"));
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
}

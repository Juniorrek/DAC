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
import rhindo.model.Departamento;

/**
 *
 * @author Fornalha
 */
public class DepartamentoDAO {
    public static List<Departamento> getDepartamentos() throws SQLException {
        List<Departamento> departamentos = new ArrayList<Departamento>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Departamento ");
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Departamento departamento = new Departamento(resultSet.getString("nome"),
                                                 resultSet.getString("localizacao"));
                departamentos.add(departamento);
                
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
        
        return departamentos;
    }
}

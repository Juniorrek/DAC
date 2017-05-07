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
                Cargo cargo = new Cargo(resultSet.getString("nome"),
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
}

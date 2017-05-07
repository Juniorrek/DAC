/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhindo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fornalha
 */
public class ConnectionFactory {
     private Connection connection;
    
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/dac", "root", "");
        } catch (SQLException exception) { 
                System.out.println("Erro ao conectar ao banco: " + exception);
                throw new RuntimeException(exception); 
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }
}

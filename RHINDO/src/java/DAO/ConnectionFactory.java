package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
     private Connection connection;
    
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "root");
        } catch (SQLException exception) { 
                System.out.println("Erro ao conectar ao banco: " + exception);
                //throw new RuntimeException(exception); 
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }
}

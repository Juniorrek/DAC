package DAO;

import facade.Facade;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cargo;
import model.Departamento;
import model.Funcionario;

public class FuncionarioDAO {
    public static void criar(Funcionario funcionario) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        String senha = "";
        try {
            MessageDigest m=MessageDigest.getInstance("MD5");
            m.update(funcionario.getSenha().getBytes(),0,funcionario.getSenha().length());
            senha = new BigInteger(1,m.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            stmt = connection.prepareStatement("INSERT INTO Funcionario (cpf, nome, rg, celular, email, rua, numero, bairro, cep, cidade, estado, departamento, cargo, perfil, senha) "
                                             + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, funcionario.getCpf());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getRg());
            stmt.setString(4, funcionario.getCelular());
            stmt.setString(5, funcionario.getEmail());
            stmt.setString(6, funcionario.getRua());
            stmt.setInt(7, funcionario.getNumero());
            stmt.setString(8, funcionario.getBairro());
            stmt.setString(9, funcionario.getCep());
            stmt.setString(10, funcionario.getCidade());
            stmt.setString(11, funcionario.getEstado());
            stmt.setInt(12, funcionario.getDepartamento().getId());
            stmt.setInt(13, funcionario.getCargo().getId());
            stmt.setString(14, funcionario.getPerfil());
            stmt.setString(15, senha);
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
    
    public static List<Funcionario> carregar() {
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Funcionario ");
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Departamento departamento = Facade.carregarDepartamento(resultSet.getInt("departamento"));
                
                Cargo cargo = Facade.carregarCargo(resultSet.getInt("cargo"));
                
                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(resultSet.getString("cpf"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setRg(resultSet.getString("rg"));
                funcionario.setCelular(resultSet.getString("celular"));
                funcionario.setEmail(resultSet.getString("email"));
                funcionario.setRua(resultSet.getString("rua"));
                funcionario.setNumero(resultSet.getInt("numero"));
                funcionario.setBairro(resultSet.getString("bairro"));
                funcionario.setCep(resultSet.getString("cep"));
                funcionario.setCidade(resultSet.getString("cidade"));
                funcionario.setEstado(resultSet.getString("estado"));
                funcionario.setDepartamento(departamento);
                funcionario.setCargo(cargo);
                funcionario.setPerfil(resultSet.getString("perfil"));
                
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
    
    public static List<Funcionario> carregarDep(int id) {
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Funcionario "
                                             + "WHERE departamento = ? AND perfil = 'Funcionário'");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Departamento departamento = Facade.carregarDepartamento(resultSet.getInt("departamento"));
                
                Cargo cargo = Facade.carregarCargo(resultSet.getInt("cargo"));
                
                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(resultSet.getString("cpf"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setRg(resultSet.getString("rg"));
                funcionario.setCelular(resultSet.getString("celular"));
                funcionario.setEmail(resultSet.getString("email"));
                funcionario.setRua(resultSet.getString("rua"));
                funcionario.setNumero(resultSet.getInt("numero"));
                funcionario.setBairro(resultSet.getString("bairro"));
                funcionario.setCep(resultSet.getString("cep"));
                funcionario.setCidade(resultSet.getString("cidade"));
                funcionario.setEstado(resultSet.getString("estado"));
                funcionario.setDepartamento(departamento);
                funcionario.setCargo(cargo);
                funcionario.setPerfil(resultSet.getString("perfil"));
                
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

    public static Funcionario carregar(String cpf, String senha) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        try {
            MessageDigest m=MessageDigest.getInstance("MD5");
            m.update(senha.getBytes(),0,senha.length());
            senha = new BigInteger(1,m.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Funcionario "
                                             + "WHERE cpf = ? AND senha = ?");
            stmt.setString(1, cpf);
            stmt.setString(2, senha);
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                Departamento departamento = Facade.carregarDepartamento(resultSet.getInt("departamento"));
                
                Cargo cargo = Facade.carregarCargo(resultSet.getInt("cargo"));
                
                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(resultSet.getString("cpf"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setRg(resultSet.getString("rg"));
                funcionario.setCelular(resultSet.getString("celular"));
                funcionario.setEmail(resultSet.getString("email"));
                funcionario.setRua(resultSet.getString("rua"));
                funcionario.setNumero(resultSet.getInt("numero"));
                funcionario.setBairro(resultSet.getString("bairro"));
                funcionario.setCep(resultSet.getString("cep"));
                funcionario.setCidade(resultSet.getString("cidade"));
                funcionario.setEstado(resultSet.getString("estado"));
                funcionario.setDepartamento(departamento);
                funcionario.setCargo(cargo);
                funcionario.setPerfil(resultSet.getString("perfil"));
                funcionario.setSenha(resultSet.getString("senha"));
                
                return funcionario;
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
    
    public static Funcionario carregar(String cpf) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Funcionario "
                                             + "WHERE cpf = ?");
            stmt.setString(1, cpf);
            ResultSet resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                Departamento departamento = Facade.carregarDepartamento(resultSet.getInt("departamento"));
                
                Cargo cargo = Facade.carregarCargo(resultSet.getInt("cargo"));
                
                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(resultSet.getString("cpf"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setRg(resultSet.getString("rg"));
                funcionario.setCelular(resultSet.getString("celular"));
                funcionario.setEmail(resultSet.getString("email"));
                funcionario.setRua(resultSet.getString("rua"));
                funcionario.setNumero(resultSet.getInt("numero"));
                funcionario.setBairro(resultSet.getString("bairro"));
                funcionario.setCep(resultSet.getString("cep"));
                funcionario.setCidade(resultSet.getString("cidade"));
                funcionario.setEstado(resultSet.getString("estado"));
                funcionario.setDepartamento(departamento);
                funcionario.setCargo(cargo);
                funcionario.setPerfil(resultSet.getString("perfil"));
                funcionario.setSenha(resultSet.getString("senha"));
                
                return funcionario;
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

    public static void editar(Funcionario funcionario) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            if (funcionario.getDepartamento() != null) {
                stmt = connection.prepareStatement("UPDATE Funcionario "
                                             + "SET nome = ?, rg = ?, celular = ?, email = ?, rua = ?, numero = ?, bairro = ?, cep = ?, cidade = ?, estado = ?, departamento = ?, cargo = ?, perfil = ? "
                                             + "WHERE cpf = ? ");
                stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getRg());
                stmt.setString(3, funcionario.getCelular());
                stmt.setString(4, funcionario.getEmail());
                stmt.setString(5, funcionario.getRua());
                stmt.setInt(6, funcionario.getNumero());
                stmt.setString(7, funcionario.getBairro());
                stmt.setString(8, funcionario.getCep());
                stmt.setString(9, funcionario.getCidade());
                stmt.setString(10, funcionario.getEstado());
                stmt.setInt(11, funcionario.getDepartamento().getId());
                stmt.setInt(12, funcionario.getCargo().getId());
                stmt.setString(13, funcionario.getPerfil());
                stmt.setString(14, funcionario.getCpf());
            } else {
                stmt = connection.prepareStatement("UPDATE Funcionario "
                                             + "SET nome = ?, rg = ?, celular = ?, email = ?, rua = ?, numero = ?, bairro = ?, cep = ?, cidade = ?, estado = ? "
                                             + "WHERE cpf = ? ");
                stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getRg());
                stmt.setString(3, funcionario.getCelular());
                stmt.setString(4, funcionario.getEmail());
                stmt.setString(5, funcionario.getRua());
                stmt.setInt(6, funcionario.getNumero());
                stmt.setString(7, funcionario.getBairro());
                stmt.setString(8, funcionario.getCep());
                stmt.setString(9, funcionario.getCidade());
                stmt.setString(10, funcionario.getEstado());
                stmt.setString(11, funcionario.getCpf());
            }
            
            if (!("".equals(funcionario.getSenha()))) {
                try {
                    MessageDigest m=MessageDigest.getInstance("MD5");
                    m.update(funcionario.getSenha().getBytes(),0,funcionario.getSenha().length());
                    String senha = new BigInteger(1,m.digest()).toString(16);
                    stmt = connection.prepareStatement("UPDATE Funcionario "
                                                        + "SET senha = ? "
                                                        + "WHERE cpf = ?");
                    stmt.setString(1, senha);
                    stmt.setString(2, funcionario.getCpf());
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
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

    public static void deletar(String cpf) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("DELETE FROM Funcionario "
                                             + "WHERE cpf = ? ");
            stmt.setString(1, cpf);
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

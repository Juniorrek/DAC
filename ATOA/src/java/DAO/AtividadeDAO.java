/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Atividade;
import model.Departamento;
import model.Funcionario;
import facade.Facade;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Fornalha
 */
public class AtividadeDAO {
    public static void criar(Atividade atividade) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Atividade "
                                             + "WHERE fim is null AND funcionario = ?");
            stmt.setString(1, atividade.getFuncionario().getCpf());
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                Facade.finalizarAtividade(resultSet.getInt("id"));
            }
            
            stmt = connection.prepareStatement("INSERT INTO Atividade (nome, descricao, tipo, inicio, fim, funcionario, status) "
                                             + "VALUES (?, ?, ?, ?, null, ?, 'EM ANDAMENTO')");
            stmt.setString(1, atividade.getNome());
            stmt.setString(2, atividade.getDescricao());
            stmt.setInt(3, atividade.getTipo().getId());
            stmt.setTimestamp(4, atividade.getInicio());
            stmt.setString(5, atividade.getFuncionario().getCpf());
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
                                             + "FROM Atividade "
                                             + "WHERE funcionario = ?");
            stmt.setString(1, funcionario.getCpf());
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Atividade atividade = new Atividade();
                atividade.setId(resultSet.getInt("id"));
                atividade.setNome(resultSet.getString("nome"));
                atividade.setDescricao(resultSet.getString("descricao"));
                atividade.setTipo(TipoDAO.carregar(resultSet.getInt("tipo")));
                atividade.setInicio(resultSet.getTimestamp("inicio"));
                atividade.setFim(resultSet.getTimestamp("fim"));
                atividade.setFuncionario(funcionario);
                atividade.setStatus(resultSet.getString("status"));
                
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
    
    public static void finalizar(int id) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("SELECT * FROM Atividade WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            Atividade atividade = new Atividade();
            if (resultSet.next()) {
                if ("EM ANDAMENTO".equals(resultSet.getString("status"))) {
                    stmt = connection.prepareStatement("UPDATE Atividade "
                                             + "SET fim = ?, status = 'FINALIZADA' "
                                             + "WHERE id = ?");
                } else {
                    stmt = connection.prepareStatement("UPDATE Atividade "
                                             + "SET fim = ?, status = 'FECHADA' "
                                             + "WHERE id = ?");
                }
            }
            
            stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(2, id);
            stmt.executeUpdate();
            
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM CorrigirAtividade "
                                             + "WHERE id = ?");
            stmt.setInt(1, id);
            resultSet = stmt.executeQuery();
            if(resultSet.next()){
                stmt = connection.prepareStatement("DELETE FROM CorrigirAtividade "
                                             + "WHERE id = ? ");
                stmt.setInt(1, id);
                stmt.executeUpdate();
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
    
    public static void solicitarCorrigir(Atividade atividade) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("INSERT INTO CorrigirAtividade (id, nome, descricao, tipo, inicio, fim, funcionario, status) "
                                             + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) "
                                             + "ON DUPLICATE KEY UPDATE nome = ?, descricao = ?, tipo = ?, inicio = ?, fim = ?, funcionario = ?, status = ?");
            stmt.setInt(1, atividade.getId());
            stmt.setString(2, atividade.getNome());
            stmt.setString(3, atividade.getDescricao());
            stmt.setInt(4, atividade.getTipo().getId());
            stmt.setTimestamp(5, atividade.getInicio());
            stmt.setTimestamp(6, atividade.getFim());
            stmt.setString(7, atividade.getFuncionario().getCpf());
            stmt.setString(8, atividade.getStatus());
            
            stmt.setString(9, atividade.getNome());
            stmt.setString(10, atividade.getDescricao());
            stmt.setInt(11, atividade.getTipo().getId());
            stmt.setTimestamp(12, atividade.getInicio());
            stmt.setTimestamp(13, atividade.getFim());
            stmt.setString(14, atividade.getFuncionario().getCpf());
            stmt.setString(15, atividade.getStatus());
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
    
    public static List<Map<String, Atividade>> carregarCorrecoes() {
        List<Map<String, Atividade>> correcoes = new ArrayList<Map<String, Atividade>>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM CorrigirAtividade ");
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Map<String, Atividade> correcao = new HashMap<String, Atividade>();
                
                Atividade atividade = new Atividade();
                atividade.setId(resultSet.getInt("id"));
                atividade.setNome(resultSet.getString("nome"));
                atividade.setDescricao(resultSet.getString("descricao"));
                atividade.setTipo(TipoDAO.carregar(resultSet.getInt("tipo")));
                atividade.setInicio(resultSet.getTimestamp("inicio"));
                atividade.setFim(resultSet.getTimestamp("fim"));
                atividade.setFuncionario(Facade.carregarFuncionario(resultSet.getString("funcionario")));
                atividade.setStatus(resultSet.getString("status"));
                
                
                
                stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Atividade "
                                             + "WHERE id = ?");
                stmt.setInt(1, resultSet.getInt("id"));
                ResultSet resultSet2 = stmt.executeQuery();
                if(resultSet2.next()) {
                    Atividade atividade2 = new Atividade();
                    atividade2.setId(resultSet2.getInt("id"));
                    atividade2.setNome(resultSet2.getString("nome"));
                    atividade2.setDescricao(resultSet2.getString("descricao"));
                    atividade2.setTipo(TipoDAO.carregar(resultSet2.getInt("tipo")));
                    atividade2.setInicio(resultSet2.getTimestamp("inicio"));
                    atividade2.setFim(resultSet2.getTimestamp("fim"));
                    atividade2.setFuncionario(Facade.carregarFuncionario(resultSet2.getString("funcionario")));
                    atividade2.setStatus(resultSet2.getString("status"));
                    
                    correcao.put("antes", atividade2);
                    correcao.put("depois", atividade);
                }
                
                correcoes.add(correcao);
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
        
        return correcoes;
    }
    
    public static void reprovarCorrecao(int id) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("DELETE FROM CorrigirAtividade "
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
    
    public static void aprovarCorrecao(int id) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM CorrigirAtividade "
                                             + "WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            
            Atividade atividade = new Atividade();
            if(resultSet.next()){
                atividade.setId(resultSet.getInt("id"));
                atividade.setNome(resultSet.getString("nome"));
                atividade.setDescricao(resultSet.getString("descricao"));
                atividade.setTipo(TipoDAO.carregar(resultSet.getInt("tipo")));
                atividade.setInicio(resultSet.getTimestamp("inicio"));
                atividade.setFim(resultSet.getTimestamp("fim"));
                atividade.setFuncionario(Facade.carregarFuncionario(resultSet.getString("funcionario")));
                atividade.setStatus(resultSet.getString("status"));
            }
            
            if ("EM ANDAMENTO".equals(atividade.getStatus())) {
                stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Atividade "
                                             + "WHERE fim is null AND funcionario = ?");
                stmt.setString(1, atividade.getFuncionario().getCpf());
                ResultSet resultSett = stmt.executeQuery();
                while(resultSett.next()){
                    Facade.finalizarAtividade(resultSett.getInt("id"));
                }
            }
            
            stmt = connection.prepareStatement("DELETE FROM CorrigirAtividade "
                                             + "WHERE id = ? ");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
            stmt = connection.prepareStatement("UPDATE Atividade "
                                             + "SET nome = ?, descricao = ?, tipo = ?, inicio = ?, fim = ?, funcionario = ?, status = ? "
                                             + "WHERE id = ?");
            stmt.setString(1, atividade.getNome());
            stmt.setString(2, atividade.getDescricao());
            stmt.setInt(3, atividade.getTipo().getId());
            stmt.setTimestamp(4, atividade.getInicio());
            stmt.setTimestamp(5, atividade.getFim());
            stmt.setString(6, atividade.getFuncionario().getCpf());
            stmt.setString(7, atividade.getStatus());
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
    }
    
    public static List<Atividade> carregarMes(Funcionario funcionario) {
        Timestamp dataAtual = new Timestamp(System.currentTimeMillis());
        long timestamp = dataAtual.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);

        List<Atividade> atividades = new ArrayList<Atividade>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Atividade "
                                             + "WHERE funcionario = ? AND MONTH(inicio) = ?");
            stmt.setString(1, funcionario.getCpf());
            stmt.setInt(2, cal.get(Calendar.MONTH) + 1);
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Atividade atividade = new Atividade();
                atividade.setId(resultSet.getInt("id"));
                atividade.setNome(resultSet.getString("nome"));
                atividade.setDescricao(resultSet.getString("descricao"));
                atividade.setTipo(TipoDAO.carregar(resultSet.getInt("tipo")));
                atividade.setInicio(resultSet.getTimestamp("inicio"));
                atividade.setFim(resultSet.getTimestamp("fim"));
                atividade.setFuncionario(funcionario);
                atividade.setStatus(resultSet.getString("status"));
                
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
    
    public static List<Atividade> carregarDep(Funcionario funcionario) {
        List<Atividade> atividades = new ArrayList<Atividade>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT atividade.* "
                                             + "FROM Atividade, Funcionario "
                                             + "WHERE atividade.funcionario = funcionario.cpf AND funcionario.departamento = ? AND (atividade.status = 'EM ANDAMENTO' OR atividade.status = 'FINALIZADA') ");
            stmt.setInt(1, funcionario.getDepartamento().getId());
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Atividade atividade = new Atividade();
                atividade.setId(resultSet.getInt("id"));
                atividade.setNome(resultSet.getString("nome"));
                atividade.setDescricao(resultSet.getString("descricao"));
                atividade.setTipo(TipoDAO.carregar(resultSet.getInt("tipo")));
                atividade.setInicio(resultSet.getTimestamp("inicio"));
                atividade.setFim(resultSet.getTimestamp("fim"));
                atividade.setFuncionario(Facade.carregarFuncionario(resultSet.getString("funcionario")));
                atividade.setStatus(resultSet.getString("status"));
                
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
    
    public static void fechar() {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("UPDATE Atividade "
                                             + "SET status = 'FECHADA' "
                                             + "WHERE status = 'FINALIZADA'");
            stmt.executeUpdate();
            
            stmt = connection.prepareStatement("UPDATE Atividade "
                                             + "SET status = 'PENDENTE' "
                                             + "WHERE status = 'EM ANDAMENTO'");
            stmt.executeUpdate();
            
            stmt = connection.prepareStatement("DELETE FROM CorrigirAtividade");
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
    
    public static void fechar(String cpf) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("UPDATE Atividade "
                                             + "SET status = 'FECHADA' "
                                             + "WHERE status = 'FINALIZADA' AND funcionario = ?");
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            
            stmt = connection.prepareStatement("UPDATE Atividade "
                                             + "SET status = 'PENDENTE' "
                                             + "WHERE status = 'EM ANDAMENTO' AND funcionario = ?");
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            
            stmt = connection.prepareStatement("DELETE FROM CorrigirAtividade WHERE funcionario = ?");
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
    
    /*
    
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
}

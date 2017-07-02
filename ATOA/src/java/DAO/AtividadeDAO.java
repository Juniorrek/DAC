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
import model.Folha;

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
            System.out.println("vou inserir");
            stmt.executeUpdate();
        } catch (SQLException exception) {
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
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
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
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
            //desnotifica all
                    stmt = connection.prepareStatement("UPDATE Departamento SET notificacao = false");
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
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
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
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
        } finally {
            if (stmt != null)
                try { stmt.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar stmt. Ex="+exception.getMessage()); }
            if (connection != null)
                try { connection.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar conexão. Ex="+exception.getMessage()); }
        }
    }
    
    public static List<Map<String, Atividade>> carregarCorrecoes(Funcionario logado) {
        //ws
        List<Map<String, Atividade>> correcoes = new ArrayList<Map<String, Atividade>>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * "
                                             + "FROM CorrigirAtividade ");
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Funcionario funcionario = Facade.carregarFuncionario(resultSet.getString("funcionario"));
                if (funcionario.getDepartamento().getId() == logado.getDepartamento().getId()) {
                    Map<String, Atividade> correcao = new HashMap<String, Atividade>();

                    Atividade atividade = new Atividade();
                    atividade.setId(resultSet.getInt("id"));
                    atividade.setNome(resultSet.getString("nome"));
                    atividade.setDescricao(resultSet.getString("descricao"));
                    atividade.setTipo(TipoDAO.carregar(resultSet.getInt("tipo")));
                    atividade.setInicio(resultSet.getTimestamp("inicio"));
                    atividade.setFim(resultSet.getTimestamp("fim"));
                    atividade.setFuncionario(funcionario);
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
            }
        } catch (SQLException exception) {
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
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
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
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
            
            /*if ("EM ANDAMENTO".equals(atividade.getStatus())) {
                stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Atividade "
                                             + "WHERE fim is null AND funcionario = ?");
                stmt.setString(1, atividade.getFuncionario().getCpf());
                ResultSet resultSett = stmt.executeQuery();
                while(resultSett.next()){
                    Facade.finalizarAtividade(resultSett.getInt("id"));
                }
            }*/
            
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
            if ("FINALIZADA".equals(atividade.getStatus())) {
                stmt.setTimestamp(5, atividade.getFim());
            } else {
                stmt.setTimestamp(5, null);
            }
            stmt.setString(6, atividade.getFuncionario().getCpf());
            stmt.setString(7, atividade.getStatus());
            stmt.setInt(8, atividade.getId());
            stmt.executeUpdate();
        } catch (SQLException exception) {
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
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
                                             + "WHERE funcionario = ? AND (MONTH(inicio) = ? OR MONTH(fim) = ?)");
            stmt.setString(1, funcionario.getCpf());
            stmt.setInt(2, cal.get(Calendar.MONTH) + 1);
            stmt.setInt(3, cal.get(Calendar.MONTH) + 1);
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
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
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
                                             + "FROM Atividade "
                                             + "WHERE atividade.status = 'EM ANDAMENTO' OR atividade.status = 'FINALIZADA' ");
            ResultSet resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                Funcionario func = Facade.carregarFuncionario(resultSet.getString("funcionario"));
                if (funcionario.getDepartamento().getId() == func.getDepartamento().getId()) {
                    Atividade atividade = new Atividade();
                    atividade.setId(resultSet.getInt("id"));
                    atividade.setNome(resultSet.getString("nome"));
                    atividade.setDescricao(resultSet.getString("descricao"));
                    atividade.setTipo(TipoDAO.carregar(resultSet.getInt("tipo")));
                    atividade.setInicio(resultSet.getTimestamp("inicio"));
                    atividade.setFim(resultSet.getTimestamp("fim"));
                    atividade.setFuncionario(func);
                    atividade.setStatus(resultSet.getString("status"));

                    atividades.add(atividade);
                }
            }
        } catch (SQLException exception) {
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
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
    
    public static void fechar(Funcionario logado) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

        try {
            
            
            stmt = connection.prepareStatement("SELECT * FROM Atividade "
                                                + "WHERE status = 'FINALIZADA' ");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Funcionario funcionario = Facade.carregarFuncionario(rs.getString("funcionario"));
                if (funcionario.getDepartamento().getId() == logado.getDepartamento().getId()) {
                    stmt = connection.prepareStatement("UPDATE Atividade "
                                                        + "SET status = 'FECHADA' "
                                                        + "WHERE status = 'FINALIZADA' and funcionario = ? ");
                    stmt.setString(1, funcionario.getCpf());
                    stmt.executeUpdate();
                    
                    stmt = connection.prepareStatement("DELETE FROM CorrigirAtividade "
                                                        + "WHERE funcionario = ? ");
                    stmt.setString(1, funcionario.getCpf());
                    stmt.executeUpdate();
                }
            }
            /*stmt = connection.prepareStatement("UPDATE Atividade " +
                "JOIN Funcionario ON funcionario.cpf = atividade.funcionario " +
                "JOIN Departamento ON funcionario.departamento = departamento.id"  +
                "SET status = 'FECHADA', notificacao = false " +
                "WHERE status = 'FINALIZADA' AND funcionario.departamento = ? ");
            stmt.setInt(1, logado.getDepartamento().getId());
            stmt.executeUpdate();*/
            //desnotifica all
                    stmt = connection.prepareStatement("UPDATE Departamento SET notificacao = false");
                    stmt.executeUpdate();
            
            stmt = connection.prepareStatement("SELECT * FROM Atividade "
                                                + "WHERE status = 'EM ANDAMENTO' ");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Funcionario funcionario = Facade.carregarFuncionario(rs.getString("funcionario"));
                if (funcionario.getDepartamento().getId() == logado.getDepartamento().getId()) {
                    stmt = connection.prepareStatement("UPDATE Atividade "
                                                        + "SET status = 'PENDENTE' "
                                                        + "WHERE status = 'EM ANDAMENTO' and funcionario = ? ");
                    stmt.setString(1, funcionario.getCpf());
                    stmt.executeUpdate();
                    
                    stmt = connection.prepareStatement("DELETE FROM CorrigirAtividade "
                                                        + "WHERE funcionario = ? ");
                    stmt.setString(1, funcionario.getCpf());
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException exception) {
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
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
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
        } finally {
            if (stmt != null)
                try { stmt.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar stmt. Ex="+exception.getMessage()); }
            if (connection != null)
                try { connection.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar conexão. Ex="+exception.getMessage()); }
        }
    }
    
    public static List<Folha> fecharFolha(int mes) {
        List<Folha> folhas = new ArrayList<Folha>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT funcionario as funcionario, SUM(((TIMESTAMPDIFF(MINUTE, inicio, fim)) - (TIMESTAMPDIFF(DAY, inicio, fim) * 16 * 60)) / 60) as horas_trabalhadas, MONTH(fim) as mes "
                    + "FROM Atividade "
                    + "WHERE status = 'FECHADA' AND MONTH(fim) = ? "
                    + "GROUP BY funcionario");
            stmt.setInt(1, mes);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {

                //CONSOLIDA AS FECHADAS
                stmt = connection.prepareStatement("UPDATE Atividade "
                                                 + "SET status = 'CONSOLIDADA' "
                                                 + "WHERE status = 'FECHADA' AND MONTH(fim) = ?");
                stmt.setInt(1, mes);
                stmt.executeUpdate();

                do {
                    Folha folha = new Folha();
                    Funcionario funcionario = new Funcionario();
                    funcionario = Facade.carregarFuncionario(resultSet.getString("funcionario"));
                    folha.setFuncionario(funcionario);
                    folha.setHoras_trabalhadas(resultSet.getFloat("horas_trabalhadas"));
                    folha.setMes(mes);

                    folhas.add(folha);
                } while(resultSet.next());

                
            }
            //NOTIFICAÇÃO
            stmt = connection.prepareStatement("SELECT * FROM Atividade WHERE status != 'FECHADA' AND status != 'CONSOLIDADA' AND MONTH(inicio) = ? GROUP BY funcionario");
            stmt.setInt(1, mes);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Funcionario funcionario = Facade.carregarFuncionario(resultSet.getString("funcionario"));//PRA PEGAR DEPARTAMENTO
                Facade.notificarDepartamento(funcionario.getDepartamento());
            }
        } catch (SQLException exception) {
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
        } finally {
            if (stmt != null)
                try { stmt.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar stmt. Ex="+exception.getMessage()); }
            if (connection != null)
                try { connection.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar conexão. Ex="+exception.getMessage()); }
        }
        
        return folhas;
    }
    
    public static List<Folha> horas_trabalhadas(String de, String ate, String cpf) {
        List<Folha> folhas = new ArrayList<Folha>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT funcionario as funcionario, SUM(((TIMESTAMPDIFF(MINUTE, inicio, fim)) - (TIMESTAMPDIFF(DAY, inicio, fim) * 16 * 60)) / 60) as horas_trabalhadas, MONTH(fim) as mes " +
                                    "FROM Atividade " +
                                    "WHERE funcionario = ? AND status != 'EM ANDAMENTO' AND fim BETWEEN ? AND ? " +
                                    "GROUP BY MONTH(fim)");
            stmt.setString(1, cpf);
            stmt.setString(2, de);
            stmt.setString(3, ate);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                Folha folha = new Folha();
                Funcionario funcionario = new Funcionario();
                funcionario = Facade.carregarFuncionario(cpf);
                folha.setFuncionario(funcionario);
                folha.setHoras_trabalhadas(resultSet.getFloat("horas_trabalhadas"));
                folha.setMes(resultSet.getInt("mes"));

                folhas.add(folha);
            }
        } catch (SQLException exception) {
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
        } finally {
            if (stmt != null)
                try { stmt.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar stmt. Ex="+exception.getMessage()); }
            if (connection != null)
                try { connection.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar conexão. Ex="+exception.getMessage()); }
        }
        
        return folhas;//;TESTAMONTANDO RELATORIO
    }
    
    public static List<Folha> horas_trabalhadas_dep_mes(int mes) {
        List<Folha> folhas = new ArrayList<Folha>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        //WSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
        try {
            stmt = connection.prepareStatement("SELECT funcionario as funcionario, SUM(((TIMESTAMPDIFF(MINUTE, inicio, fim)) - (TIMESTAMPDIFF(DAY, inicio, fim) * 16 * 60)) / 60) as horas_trabalhadas, MONTH(fim) as mes " +
                                                "FROM Atividade " +
                                                "JOIN Funcionario ON funcionario.cpf = atividade.funcionario " +
                                                "WHERE MONTH(fim) = ? " +
                                                "GROUP BY funcionario.departamento");
            stmt.setInt(1, mes);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                Folha folha = new Folha();
                Funcionario funcionario = new Funcionario();
                funcionario = Facade.carregarFuncionario(resultSet.getString("funcionario"));
                folha.setFuncionario(funcionario);
                folha.setHoras_trabalhadas(resultSet.getFloat("horas_trabalhadas"));
                folha.setMes(resultSet.getInt("mes"));

                folhas.add(folha);
            }
        } catch (SQLException exception) {
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
        } finally {
            if (stmt != null)
                try { stmt.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar stmt. Ex="+exception.getMessage()); }
            if (connection != null)
                try { connection.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar conexão. Ex="+exception.getMessage()); }
        }
        
        return folhas;//;TESTAMONTANDO RELATORIO
    }
    
    public static List<Folha> funcncumpriu(int mes) {
        List<Folha> folhas = new ArrayList<Folha>();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = connection.prepareStatement("SELECT funcionario as funcionario, SUM(((TIMESTAMPDIFF(MINUTE, inicio, fim)) - (TIMESTAMPDIFF(DAY, inicio, fim) * 16 * 60)) / 60) as horas_trabalhadas, MONTH(fim) as mes " +
                                                "FROM Atividade a " +
                                                "WHERE MONTH(fim) = ? " +
                                                "GROUP BY funcionario");
            stmt.setInt(1, mes);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario = Facade.carregarFuncionario(resultSet.getString("funcionario"));
                
                if (resultSet.getFloat("horas_trabalhadas") < funcionario.getCargo().getCarga_trabalho_minima_mes()) {
                    Folha folha = new Folha();
                    folha.setFuncionario(funcionario);
                    folha.setHoras_trabalhadas(resultSet.getFloat("horas_trabalhadas"));
                    folha.setMes(resultSet.getInt("mes"));

                    folhas.add(folha);
                }
            }
        } catch (SQLException exception) {
            //throw new RuntimeException("Erro. Origem="+exception.getMessage());
        } finally {
            if (stmt != null)
                try { stmt.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar stmt. Ex="+exception.getMessage()); }
            if (connection != null)
                try { connection.close(); }
                catch (SQLException exception) { System.out.println("Erro ao fechar conexão. Ex="+exception.getMessage()); }
        }
        
        return folhas;//;TESTAMONTANDO RELATORIO
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

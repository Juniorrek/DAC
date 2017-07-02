/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Folha;
import model.Funcionario;
import model.Holerite;

/**
 *
 * @author Fornalha
 */
public class FolhaDAO {
    public static void fechar(List<Folha> lista) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        try {
            for (Folha folha : lista) {
                //vai verificar se ja existe
                stmt = connection.prepareStatement("SELECT * "
                                             + "FROM Folha "
                                             + "WHERE funcionario = ? AND mes = ? ");
                stmt.setString(1, folha.getFuncionario().getCpf());
                stmt.setInt(2, folha.getMes());
                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    stmt = connection.prepareStatement("UPDATE Folha "
                                                        + "SET horas_trabalhadas = ? "
                                                        + "WHERE funcionario = ? AND mes = ?"
                                                        + "VALUES (?, ?, ?)");
                    stmt.setFloat(1, (folha.getHoras_trabalhadas() + resultSet.getFloat("horas_trabalhadas")));
                    stmt.setString(2, folha.getFuncionario().getCpf());
                    stmt.setInt(3, folha.getMes());
                } else {
                    stmt = connection.prepareStatement("INSERT INTO Folha (funcionario, horas_trabalhadas, mes) "
                                                        + "VALUES (?, ?, ?)");
                    stmt.setString(1, folha.getFuncionario().getCpf());
                    stmt.setFloat(2, folha.getHoras_trabalhadas());
                    stmt.setInt(3, folha.getMes());
                }
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
    
    public static Holerite obterHolerite(Funcionario funcionario, int mes) {
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        Holerite holerite = null;
        try {
            stmt = connection.prepareStatement("SELECT funcionario, mes, horas_trabalhadas "
                                                + "FROM Folha "
                                                + "WHERE funcionario = ? AND mes = ?");
            stmt.setString(1, funcionario.getCpf());
            stmt.setInt(2, mes);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                holerite = new Holerite();
                holerite.setCpf(funcionario.getCpf());
                holerite.setMes(mes);
                holerite.setNome(funcionario.getNome());
                float salario_bruto = 0;
                salario_bruto = (float) (funcionario.getCargo().getSalario() * resultSet.getFloat("horas_trabalhadas")) / funcionario.getCargo().getCarga_trabalho_minima_mes();
                holerite.setSalario_bruto(salario_bruto);
                float salario_liquido = 0;
                salario_liquido = (float) (salario_bruto - (salario_bruto * (funcionario.getCargo().getDesconto_impostos_gerais() / 100.0)));
                holerite.setSalario_liquido(salario_liquido);
                holerite.setHoras_trabalhadas(resultSet.getFloat("horas_trabalhadas"));
            }
            return holerite;
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
        return null;
    }
}

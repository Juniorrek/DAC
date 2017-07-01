/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author david
 */
public class Holerite implements java.io.Serializable {
    private String cpf;
    private int mes;
    private String nome;
    private float horas_trabalhadas;
    private float salario_bruto;
    private float salario_liquido;
    
    public Holerite() {}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSalario_bruto() {
        return salario_bruto;
    }

    public void setSalario_bruto(float salario_bruto) {
        this.salario_bruto = salario_bruto;
    }

    public float getSalario_liquido() {
        return salario_liquido;
    }

    public void setSalario_liquido(float salario_liquido) {
        this.salario_liquido = salario_liquido;
    }

    public float getHoras_trabalhadas() {
        return horas_trabalhadas;
    }

    public void setHoras_trabalhadas(float horas_trabalhadas) {
        this.horas_trabalhadas = horas_trabalhadas;
    }
}

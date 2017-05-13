/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhindo.model;

/**
 *
 * @author Fornalha
 */
public class Cargo {
    private int id;
    private String nome;
    private float salario;
    private String requisitos;
    private int imposto_desconto;
    private int carga_trabalho_minima_mes;

    public Cargo(int id, String nome, float salario, String requisitos, int imposto_desconto, int carga_trabalho_minima_mes) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
        this.requisitos = requisitos;
        this.imposto_desconto = imposto_desconto;
        this.carga_trabalho_minima_mes = carga_trabalho_minima_mes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public int getImposto_desconto() {
        return imposto_desconto;
    }

    public void setImposto_desconto(int imposto_desconto) {
        this.imposto_desconto = imposto_desconto;
    }

    public int getCarga_trabalho_minima_mes() {
        return carga_trabalho_minima_mes;
    }

    public void setCarga_trabalho_minima_mes(int carga_trabalho_minima_mes) {
        this.carga_trabalho_minima_mes = carga_trabalho_minima_mes;
    }
}

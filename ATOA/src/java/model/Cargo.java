package model;

public class Cargo implements java.io.Serializable {
    private int id;
    private String nome;
    private float salario;
    private String requisitos;
    private int carga_trabalho_minima_mes;
    private int desconto_impostos_gerais;

    public Cargo() {}

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

    public int getCarga_trabalho_minima_mes() {
        return carga_trabalho_minima_mes;
    }

    public void setCarga_trabalho_minima_mes(int carga_trabalho_minima_mes) {
        this.carga_trabalho_minima_mes = carga_trabalho_minima_mes;
    }

    public int getDesconto_impostos_gerais() {
        return desconto_impostos_gerais;
    }

    public void setDesconto_impostos_gerais(int desconto_impostos_gerais) {
        this.desconto_impostos_gerais = desconto_impostos_gerais;
    }
}

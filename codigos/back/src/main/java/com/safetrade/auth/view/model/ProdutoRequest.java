package com.safetrade.auth.view.model;

public class ProdutoRequest {
    
    //#region Atributos

    private String nome;

    private int quantidade;

    private double preco;
    
    private String obs;

    //#endregion

    //#region Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    //#endregion
}

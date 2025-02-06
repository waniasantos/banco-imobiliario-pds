package com.bancoimobiliario.models;

public abstract class Logradouro {
    protected String nome;
    protected int posicao; // posição no tabuleiro

    public Logradouro(String nome, int posicao) {
        this.nome = nome;
        this.posicao = posicao;
    }

    public String getNome() { return nome; }

    public int getPosicao() {  return posicao; }
    
    public abstract void acaoAoParar(Jogador jogador, int valorDado);
}

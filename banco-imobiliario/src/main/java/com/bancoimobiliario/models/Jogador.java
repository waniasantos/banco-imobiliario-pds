package com.bancoimobiliario.models;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private double saldo;
    private int posicao;
    private List<Adquirivel> propriedades;

    public Jogador(String nome, double saldoInicial) {
        this.nome = nome;
        this.saldo = saldoInicial;
        this.posicao = 0;
        this.propriedades = new ArrayList<>();
    }

    public String getNome() { return nome; }

    public double getSaldo() { return saldo; }

    public int getPosicao() { return posicao; }

    public List<Adquirivel> getPropriedades() { return propriedades; }

    public void setPosicao(int posicao) { this.posicao = posicao; }
    
    public void atualizarSaldo(double valor) { this.saldo += valor; }
    
    public void adicionarPropriedade(Adquirivel prop) { this.propriedades.add(prop); }
    
    public void removerPropriedade(Adquirivel prop) { this.propriedades.remove(prop); }

    public void mover(int passos, int tamanhoTabuleiro) {
        int novaPosicao = (this.posicao + passos) % tamanhoTabuleiro;
        this.posicao = novaPosicao;
    }
    
    @Override
    public String toString() {
        return "Jogador{" +
                "nome='" + nome + '\'' +
                ", saldo=" + saldo +
                ", posicao=" + posicao +
                ", propriedades=" + propriedades +
                '}';
    }
}

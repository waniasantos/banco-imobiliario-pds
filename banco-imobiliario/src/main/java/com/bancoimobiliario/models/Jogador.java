package com.bancoimobiliario.models;

import com.bancoimobiliario.observer.Observer;
import com.bancoimobiliario.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class Jogador implements Subject {
    private String nome;
    private double saldo;
    private int posicao;
    private List<Adquirivel> propriedades;
    private List<Observer> observers;
    private boolean eliminado;

    public Jogador(String nome, double saldoInicial) {
        this.nome = nome;
        this.saldo = saldoInicial;
        this.posicao = 0;
        this.propriedades = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.eliminado = false;
    }

    public String getNome() { return nome; }
    public double getSaldo() { return saldo; }
    public int getPosicao() { return posicao; }
    public List<Adquirivel> getPropriedades() { return propriedades; }
    public boolean isEliminado() { return eliminado; }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
        notificarObservers();
    }
    
    public void atualizarSaldo(double valor) {
        this.saldo += valor;
        if (this.saldo < 0) {
            this.eliminado = true;
        }
        notificarObservers();
    }
    
    public void adicionarPropriedade(Adquirivel prop) {
        this.propriedades.add(prop);
        notificarObservers();
    }
    
    public void removerPropriedade(Adquirivel prop) {
        this.propriedades.remove(prop);
        notificarObservers();
    }

    public void mover(int passos, int tamanhoTabuleiro) {
        int novaPosicao = (this.posicao + passos) % tamanhoTabuleiro;
        this.posicao = novaPosicao;
        notificarObservers();
    }
    
    public void eliminar() {
        this.eliminado = true;
        notificarObservers();
    }

    // Implementação dos métodos da interface Subject
    @Override
    public void adicionarObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removerObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notificarObservers() {
        for (Observer observer : observers) {
            observer.atualizar(this);
        }
    }
    
    @Override
    public String toString() {
        return "Jogador{" +
                "nome='" + nome + '\'' +
                ", saldo=" + saldo +
                ", posicao=" + posicao +
                ", propriedades=" + propriedades +
                ", eliminado=" + eliminado +
                '}';
    }
}
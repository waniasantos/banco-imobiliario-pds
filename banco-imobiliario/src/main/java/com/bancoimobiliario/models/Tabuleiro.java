package com.bancoimobiliario.models;

import java.util.List;

public class Tabuleiro {
    private static Tabuleiro instance;
    private List<Logradouro> casas;
    private int pontoDePartida;
    private double bonusPassarPorPartida;

    private Tabuleiro(List<Logradouro> casas, int pontoDePartida, double bonusPassarPorPartida) {
        this.casas = casas;
        this.pontoDePartida = pontoDePartida;
        this.bonusPassarPorPartida = bonusPassarPorPartida;
    }

    public static Tabuleiro getInstance(List<Logradouro> casas, int pontoDePartida, double bonusPassarPorPartida) {
        if (instance == null) {
            instance = new Tabuleiro(casas, pontoDePartida, bonusPassarPorPartida);
        }
        return instance;
    }
    
    public static Tabuleiro getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Tabuleiro não foi inicializado. Use getInstance(casas, pontoDePartida, bonusPassarPorPartida) primeiro.");
        }
        return instance;
    }

    public Logradouro getCasa(int posicao) { return casas.get(posicao); }

    public int getTamanho() { return casas.size(); }

    public int getPontoDePartida() { return pontoDePartida; }

    public double getBonusPassarPorPartida() { return bonusPassarPorPartida; }
    
    public List<Logradouro> getCasas() { return casas; }
}

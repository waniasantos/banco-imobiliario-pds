package com.bancoimobiliario.models;

import java.util.List;

public class Tabuleiro {
    private List<Logradouro> casas;
    private int pontoDePartida;
    private double bonusPassarPorPartida;

    public Tabuleiro(List<Logradouro> casas, int pontoDePartida, double bonusPassarPorPartida) {
        this.casas = casas;
        this.pontoDePartida = pontoDePartida;
        this.bonusPassarPorPartida = bonusPassarPorPartida;
    }

    public Logradouro getCasa(int posicao) { return casas.get(posicao); }

    public int getTamanho() { return casas.size(); }

    public int getPontoDePartida() { return pontoDePartida; }

    public double getBonusPassarPorPartida() { return bonusPassarPorPartida; }
    
    public List<Logradouro> getCasas() { return casas; }
}

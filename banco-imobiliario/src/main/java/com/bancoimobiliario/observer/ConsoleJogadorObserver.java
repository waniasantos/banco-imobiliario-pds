package com.bancoimobiliario.observer;

import com.bancoimobiliario.models.Jogador;

public class ConsoleJogadorObserver implements Observer {
    @Override
    public void atualizar(Jogador jogador) {
        System.out.println("=== Atualização de Status do Jogador ===");
        System.out.println("Jogador: " + jogador.getNome());
        System.out.println("Saldo Atual: " + jogador.getSaldo());
        System.out.println("Posição: " + jogador.getPosicao());
        System.out.println("Total de Propriedades: " + jogador.getPropriedades().size());
        if (jogador.isEliminado()) {
            System.out.println("JOGADOR ELIMINADO!");
        }
        System.out.println("======================================");
    }
}
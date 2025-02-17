package com.bancoimobiliario;

import com.bancoimobiliario.factories.TabuleiroFactory;
import com.bancoimobiliario.models.Jogador;
import com.bancoimobiliario.models.Tabuleiro;
import com.bancoimobiliario.models.Adquirivel;
import com.bancoimobiliario.observer.ConsoleJogadorObserver;

public class App {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = TabuleiroFactory.criarTabuleiroDefault();
        
        Jogador jogador1 = new Jogador("Jogador 1", 1500);
        Jogador jogador2 = new Jogador("Jogador 2", 1500);
        
        ConsoleJogadorObserver observer = new ConsoleJogadorObserver();
        jogador1.adicionarObserver(observer);
        jogador2.adicionarObserver(observer);
        
        jogador1.atualizarSaldo(200);
        jogador1.mover(3, tabuleiro.getTamanho());
        
        if (tabuleiro.getCasa(jogador1.getPosicao()) instanceof Adquirivel) {
            Adquirivel propriedade = (Adquirivel) tabuleiro.getCasa(jogador1.getPosicao());
            propriedade.comprar(jogador1);
        }
        
        System.out.println("Tabuleiro inicializado com " + tabuleiro.getTamanho() + " casas.");
    }
}
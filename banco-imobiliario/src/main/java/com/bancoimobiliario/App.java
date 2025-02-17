package com.bancoimobiliario;
import com.bancoimobiliario.factories.TabuleiroFactory;
import com.bancoimobiliario.models.Tabuleiro;

public class App {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = TabuleiroFactory.criarTabuleiroDefault();
        System.out.println("Tabuleiro inicializado com " + tabuleiro.getTamanho() + " casas.");
    }
}
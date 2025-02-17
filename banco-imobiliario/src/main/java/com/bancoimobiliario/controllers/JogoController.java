package com.bancoimobiliario.controllers;

import com.bancoimobiliario.models.Jogador;
import com.bancoimobiliario.models.Logradouro;
import com.bancoimobiliario.models.Tabuleiro;
import com.bancoimobiliario.models.LugarEspecial;
import com.bancoimobiliario.models.Adquirivel;

import java.util.List;
import java.util.ArrayList;

public class JogoController {
    private List<Jogador> jogadores;
    private int jogadorAtual;
    private Tabuleiro tabuleiro;
    private boolean jogoFinalizado;
    
    public JogoController(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.jogadores = new ArrayList<>();
        this.jogadorAtual = 0;
        this.jogoFinalizado = false;
    }
    
    public void adicionarJogador(Jogador jogador) { jogadores.add(jogador); }
    
    public Jogador getJogadorAtual() { return jogadores.get(jogadorAtual); }
    
    public List<Jogador> getJogadores() { return jogadores; }
    
    public boolean isJogoFinalizado() { return jogoFinalizado; }
    
    public int[] valoresDados(int dado1, int dado2) {

        // Validar os valores dos dados (devem estar entre 1 e 6)
        if (dado1 < 1 || dado1 > 6 || dado2 < 1 || dado2 > 6) {
            throw new IllegalArgumentException("Os valores dos dados devem estar entre 1 e 6");
        }
        return new int[]{dado1, dado2};
    }
    
    public void proximoTurno() {
        do {
            jogadorAtual = (jogadorAtual + 1) % jogadores.size();
        } while (jogadores.get(jogadorAtual).isEliminado() && !verificarFimDeJogo());
    }
    
    public void realizarJogada(int dado1, int dado2) {
        Jogador jogadorAtual = getJogadorAtual();
        if (jogadorAtual.isEliminado()) return;
        
        int[] dados = valoresDados(dado1, dado2);
        int totalDados = dados[0] + dados[1];
        
        int posicaoAnterior = jogadorAtual.getPosicao();
        jogadorAtual.mover(totalDados, tabuleiro.getTamanho());
        
        if (jogadorAtual.getPosicao() < posicaoAnterior) {
            jogadorAtual.atualizarSaldo(tabuleiro.getBonusPassarPorPartida());
            System.out.println(jogadorAtual.getNome() + " passou pelo ponto de partida e recebeu " 
                             + tabuleiro.getBonusPassarPorPartida());
        }
        
        Logradouro casaAtual = tabuleiro.getCasa(jogadorAtual.getPosicao());
        casaAtual.acaoAoParar(jogadorAtual, totalDados);
        
        if (casaAtual instanceof LugarEspecial) {
            LugarEspecial lugarEspecial = (LugarEspecial) casaAtual;
            if (lugarEspecial.getMovimento() != 0) {
                int novoMovimento = lugarEspecial.getMovimento();
                jogadorAtual.mover(novoMovimento, tabuleiro.getTamanho());
                
                if (novoMovimento > 0 && jogadorAtual.getPosicao() < (posicaoAnterior + totalDados) % tabuleiro.getTamanho()) {
                    jogadorAtual.atualizarSaldo(tabuleiro.getBonusPassarPorPartida());
                    System.out.println(jogadorAtual.getNome() + " passou pelo ponto de partida e recebeu " 
                                    + tabuleiro.getBonusPassarPorPartida());
                }
                
                Logradouro novaCasa = tabuleiro.getCasa(jogadorAtual.getPosicao());
                if (novaCasa != lugarEspecial) {
                    novaCasa.acaoAoParar(jogadorAtual, totalDados);
                }
            }
        }
        
        verificarFimDeJogo();
    }
    
    public boolean verificarFimDeJogo() {
        int jogadoresAtivos = 0;
        Jogador ultimoJogadorAtivo = null;
        
        for (Jogador jogador : jogadores) {
            if (!jogador.isEliminado()) {
                jogadoresAtivos++;
                ultimoJogadorAtivo = jogador;
            }
        }
        
        if (jogadoresAtivos <= 1 && jogadores.size() > 1) {
            jogoFinalizado = true;
            if (ultimoJogadorAtivo != null) {
                System.out.println("O jogo terminou! O vencedor é: " + ultimoJogadorAtivo.getNome());
            } else {
                System.out.println("O jogo terminou! Não houve vencedor.");
            }
            return true;
        }
        return false;
    }
    
    public boolean podeComprarPropriedadeAtual() {
        Jogador jogadorAtual = getJogadorAtual();
        Logradouro casaAtual = tabuleiro.getCasa(jogadorAtual.getPosicao());
        
        if (casaAtual instanceof Adquirivel) {
            Adquirivel propriedade = (Adquirivel) casaAtual;
            return !propriedade.isAdquirido() && jogadorAtual.getSaldo() >= propriedade.getValorCompra();
        }
        return false;
    }
    
    public void comprarPropriedadeAtual() {
        if (podeComprarPropriedadeAtual()) {
            Jogador jogadorAtual = getJogadorAtual();
            Adquirivel propriedade = (Adquirivel) tabuleiro.getCasa(jogadorAtual.getPosicao());
            propriedade.comprar(jogadorAtual);
        }
    }
}
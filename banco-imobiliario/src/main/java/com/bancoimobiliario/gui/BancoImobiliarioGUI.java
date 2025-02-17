package com.bancoimobiliario.gui;

import com.bancoimobiliario.controllers.JogoController;
import com.bancoimobiliario.factories.TabuleiroFactory;
import com.bancoimobiliario.models.*;
import com.bancoimobiliario.observer.ConsoleJogadorObserver;
import com.bancoimobiliario.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BancoImobiliarioGUI extends JFrame {
    private JogoController controller;
    private JPanel painelJogo;
    private JTextArea logJogo;
    private List<JPanel> painelJogadores;
    private JButton botaoRolarDados;
    private JButton botaoComprar;
    private JButton botaoPassarVez;
    
    public BancoImobiliarioGUI() {
        // Inicializar o tabuleiro e o controlador
        Tabuleiro tabuleiro = TabuleiroFactory.criarTabuleiroDefault();
        controller = new JogoController(tabuleiro);
        
        // Criar jogadores
        Jogador jogador1 = new Jogador("Jogador 1", 1500);
        Jogador jogador2 = new Jogador("Jogador 2", 1500);
        
        controller.adicionarJogador(jogador1);
        controller.adicionarJogador(jogador2);
        
        // Configurar a janela principal
        setTitle("Banco Imobiliário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        
        // Criação dos painéis principais
        JPanel painelSuperior = new JPanel(new GridLayout(1, 2));
        painelJogo = new JPanel();
        JPanel painelControles = new JPanel();
        JScrollPane painelLog = new JScrollPane();
        
        // Configuração do painel de jogadores
        painelJogadores = new ArrayList<>();
        for (Jogador jogador : controller.getJogadores()) {
            JPanel painelJogador = criarPainelJogador(jogador);
            painelJogadores.add(painelJogador);
            painelSuperior.add(painelJogador);
            
            // Adicionar observer para atualizar o painel
            jogador.adicionarObserver(new GUIJogadorObserver(painelJogador));
            jogador.adicionarObserver(new ConsoleJogadorObserver());
        }
        
        // Configuração do painel de controles
        painelControles.setLayout(new GridLayout(3, 1));
        botaoRolarDados = new JButton("Rolar Dados");
        botaoComprar = new JButton("Comprar Propriedade");
        botaoPassarVez = new JButton("Passar Vez");
        
        painelControles.add(botaoRolarDados);
        painelControles.add(botaoComprar);
        painelControles.add(botaoPassarVez);
        
        // Configuração do painel de log
        logJogo = new JTextArea();
        logJogo.setEditable(false);
        painelLog.setViewportView(logJogo);
        
        // Adicionar os componentes à janela principal
        add(painelSuperior, BorderLayout.NORTH);
        add(painelJogo, BorderLayout.CENTER);
        add(painelControles, BorderLayout.EAST);
        add(painelLog, BorderLayout.SOUTH);
        
        // Configurar os listeners dos botões
        configurarListeners();
        
        // Atualizar o estado inicial
        atualizarEstadoJogo();
    }
    
    private JPanel criarPainelJogador(Jogador jogador) {
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder(jogador.getNome()));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        
        JLabel labelSaldo = new JLabel("Saldo: $" + jogador.getSaldo());
        JLabel labelPosicao = new JLabel("Posição: " + jogador.getPosicao());
        JLabel labelPropriedades = new JLabel("Propriedades: " + jogador.getPropriedades().size());
        
        painel.add(labelSaldo);
        painel.add(labelPosicao);
        painel.add(labelPropriedades);
        
        // Guardar referências para atualização
        painel.putClientProperty("labelSaldo", labelSaldo);
        painel.putClientProperty("labelPosicao", labelPosicao);
        painel.putClientProperty("labelPropriedades", labelPropriedades);
        
        return painel;
    }
    
    private void configurarListeners() {
        botaoRolarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] dados = controller.rolarDados();
                logJogo.append(controller.getJogadorAtual().getNome() + " rolou os dados: " + 
                              dados[0] + " e " + dados[1] + "\n");
                controller.realizarJogada();
                atualizarEstadoJogo();
            }
        });
        
        botaoComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.podeComprarPropriedadeAtual()) {
                    controller.comprarPropriedadeAtual();
                    atualizarEstadoJogo();
                } else {
                    logJogo.append("Não é possível comprar esta propriedade\n");
                }
            }
        });
        
        botaoPassarVez.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.proximoTurno();
                atualizarEstadoJogo();
            }
        });
    }
    
    private void atualizarEstadoJogo() {
        // Atualizar controles baseados no estado do jogo
        Jogador jogadorAtual = controller.getJogadorAtual();
        
        botaoRolarDados.setEnabled(!controller.isJogoFinalizado());
        botaoComprar.setEnabled(!controller.isJogoFinalizado() && controller.podeComprarPropriedadeAtual());
        botaoPassarVez.setEnabled(!controller.isJogoFinalizado());
        
        // Destacar o jogador atual
        for (int i = 0; i < painelJogadores.size(); i++) {
            JPanel painel = painelJogadores.get(i);
            if (controller.getJogadores().get(i) == jogadorAtual) {
                painel.setBorder(BorderFactory.createTitledBorder(
                    jogadorAtual.getNome() + " (Jogador Atual)"));
            } else {
                painel.setBorder(BorderFactory.createTitledBorder(
                    controller.getJogadores().get(i).getNome()));
            }
        }
        
        // Mostrar estado atual
        Logradouro casaAtual = TabuleiroFactory.criarTabuleiroDefault().getCasa(
            jogadorAtual.getPosicao());
        logJogo.append(jogadorAtual.getNome() + " está em " + casaAtual.getNome() + "\n");
        
        if (controller.isJogoFinalizado()) {
            JOptionPane.showMessageDialog(this, "Jogo Finalizado!");
        }
    }
    
    // Observer para atualizar a interface
    private class GUIJogadorObserver implements Observer {
        private JPanel painelJogador;
        
        public GUIJogadorObserver(JPanel painelJogador) {
            this.painelJogador = painelJogador;
        }
        
        @Override
        public void atualizar(Jogador jogador) {
            SwingUtilities.invokeLater(() -> {
                JLabel labelSaldo = (JLabel) painelJogador.getClientProperty("labelSaldo");
                JLabel labelPosicao = (JLabel) painelJogador.getClientProperty("labelPosicao");
                JLabel labelPropriedades = (JLabel) painelJogador.getClientProperty("labelPropriedades");
                
                labelSaldo.setText("Saldo: $" + jogador.getSaldo());
                labelPosicao.setText("Posição: " + jogador.getPosicao());
                labelPropriedades.setText("Propriedades: " + jogador.getPropriedades().size());
                
                if (jogador.isEliminado()) {
                    painelJogador.setBorder(BorderFactory.createTitledBorder(
                        jogador.getNome() + " (ELIMINADO)"));
                }
            });
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BancoImobiliarioGUI().setVisible(true);
        });
    }
}
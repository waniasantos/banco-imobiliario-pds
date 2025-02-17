package com.bancoimobiliario.models;

public class LugarEspecial extends Logradouro {
    private int movimento;     // valor que indica quantas casas o jogador deve se mover (pode ser positivo ou negativo)
    private double valorSaldo; // valor a ser adicionado ou subtraído do saldo do jogador

    public LugarEspecial(String nome, int posicao, int movimento, double valorSaldo) {
        super(nome, posicao);
        this.movimento = movimento;
        this.valorSaldo = valorSaldo;
    }

    public int getMovimento() { return movimento; }

    @Override
    public void acaoAoParar(Jogador jogador, int valorDado) {
        if (movimento != 0) {
            System.out.println(jogador.getNome() + " foi movido " + movimento + " casas devido ao efeito de " + nome + ".");
            // reposicionamento -> controlador
        }
        if (valorSaldo != 0) {
            jogador.atualizarSaldo(valorSaldo);
            String acao = valorSaldo > 0 ? "recebeu" : "pagou";
            System.out.println(jogador.getNome() + " " + acao + " " + Math.abs(valorSaldo) + " devido ao efeito de " + nome + ".");
        }
    }
    
    @Override
    public String toString() {
        return "LugarEspecial{" +
                "nome='" + nome + '\'' +
                ", posicao=" + posicao +
                ", movimento=" + movimento +
                ", valorSaldo=" + valorSaldo +
                '}';
    }
}

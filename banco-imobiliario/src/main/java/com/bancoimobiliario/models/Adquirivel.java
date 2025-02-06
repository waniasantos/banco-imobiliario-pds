package com.bancoimobiliario.models;

public abstract class Adquirivel extends Logradouro {
    protected double valorCompra;
    protected Jogador dono; 

    public Adquirivel(String nome, int posicao, double valorCompra) {
        super(nome, posicao);
        this.valorCompra = valorCompra;
        this.dono = null;
    }

    public double getValorCompra() { return valorCompra; }

    public Jogador getDono() { return dono; }

    public boolean isAdquirido() { return dono != null; }

    public void comprar(Jogador jogador) {
        if (!isAdquirido() && jogador.getSaldo() >= valorCompra) {
            this.dono = jogador;
            jogador.atualizarSaldo(-valorCompra);
            jogador.adicionarPropriedade(this);
            System.out.println(jogador.getNome() + " comprou " + nome + " por " + valorCompra);
        }
    }
    
    public abstract void cobrarTaxa(Jogador visitante, int valorDado);

    @Override
    public String toString() {
        return "Adquirivel{" +
                "nome='" + nome + '\'' +
                ", posicao=" + posicao +
                ", valorCompra=" + valorCompra +
                ", dono=" + (dono != null ? dono.getNome() : "Nenhum") +
                '}';
    }
}

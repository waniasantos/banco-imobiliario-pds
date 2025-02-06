package com.bancoimobiliario.models;

public class Imovel extends Adquirivel {
    private double taxaAluguel;

    public Imovel(String nome, int posicao, double valorCompra, double taxaAluguel) {
        super(nome, posicao, valorCompra);
        this.taxaAluguel = taxaAluguel;
    }

    public double getTaxaAluguel() { return taxaAluguel; }

    @Override
    public void acaoAoParar(Jogador jogador, int valorDado) {
        if (!isAdquirido()) {
            System.out.println(jogador.getNome() + " caiu no imóvel " + nome + " e pode comprá-lo por " + valorCompra);
        } else if (dono != jogador) {
            System.out.println(jogador.getNome() + " caiu no imóvel " + nome + " pertencente a " + dono.getNome() + ". Deve pagar aluguel.");
            cobrarTaxa(jogador, valorDado);
        } else {
            System.out.println(jogador.getNome() + " caiu em seu próprio imóvel " + nome + ".");
        }
    }

    @Override
    public void cobrarTaxa(Jogador visitante, int valorDado) {
        double taxa = this.taxaAluguel;
        if (visitante.getSaldo() >= taxa) {
            visitante.atualizarSaldo(-taxa);
            dono.atualizarSaldo(taxa);
            System.out.println("Cobrado aluguel de " + taxa + " de " + visitante.getNome());
        } else {
            System.out.println(visitante.getNome() + " não possui saldo suficiente para pagar o aluguel.");
            // eliminação --> controlador
        }
    }
    
    @Override
    public String toString() {
        return "Imovel{" +
                "nome='" + nome + '\'' +
                ", posicao=" + posicao +
                ", valorCompra=" + valorCompra +
                ", taxaAluguel=" + taxaAluguel +
                ", dono=" + (dono != null ? dono.getNome() : "Nenhum") +
                '}';
    }
}

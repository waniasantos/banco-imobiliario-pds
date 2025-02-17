package com.bancoimobiliario.models;

import com.bancoimobiliario.strategy.CalculoTaxaStrategy;
import com.bancoimobiliario.strategy.TaxaFixaStrategy;

public class Empresa extends Adquirivel {
    private double taxaUsoFixa; 
    private CalculoTaxaStrategy estrategiaCalculo;

    public Empresa(String nome, int posicao, double valorCompra, double taxaUsoFixa) {
        super(nome, posicao, valorCompra);
        this.taxaUsoFixa = taxaUsoFixa;
        this.estrategiaCalculo = new TaxaFixaStrategy();
    }

    public double getTaxaUsoFixa() { return taxaUsoFixa; }

    public void setEstrategiaCalculo(CalculoTaxaStrategy estrategia) {
        this.estrategiaCalculo = estrategia;
    }

    @Override
    public void acaoAoParar(Jogador jogador, int valorDado) {
        if (!isAdquirido()) {
            System.out.println(jogador.getNome() + " caiu na empresa " + nome + " e pode comprá-la por " + valorCompra);
        } else if (dono != jogador) {
            System.out.println(jogador.getNome() + " caiu na empresa " + nome + " pertencente a " + dono.getNome() + ". Deve pagar taxa de uso.");
            cobrarTaxa(jogador, valorDado);
        } else {
            System.out.println(jogador.getNome() + " caiu em sua própria empresa " + nome + ".");
        }
    }

    @Override
    public void cobrarTaxa(Jogador visitante, int valorDado) {
        double taxa = estrategiaCalculo.calcularTaxa(this, visitante, valorDado);
        if (visitante.getSaldo() >= taxa) {
            visitante.atualizarSaldo(-taxa);
            dono.atualizarSaldo(taxa);
            System.out.println("Cobrado taxa de uso de " + taxa + " de " + visitante.getNome());
        } else {
            System.out.println(visitante.getNome() + " não possui saldo suficiente para pagar a taxa de uso.");
        }
    }
    
    @Override
    public String toString() {
        return "Empresa{" +
                "nome='" + nome + '\'' +
                ", posicao=" + posicao +
                ", valorCompra=" + valorCompra +
                ", taxaUsoFixa=" + taxaUsoFixa +
                ", dono=" + (dono != null ? dono.getNome() : "Nenhum") +
                '}';
    }
}

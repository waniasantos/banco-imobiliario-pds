package com.bancoimobiliario.strategy;

import com.bancoimobiliario.models.Empresa;
import com.bancoimobiliario.models.Jogador;

public class TaxaBaseDadosStrategy implements CalculoTaxaStrategy {
    private double multiplicador;
    
    public TaxaBaseDadosStrategy(double multiplicador) {
        this.multiplicador = multiplicador;
    }
    
    @Override
    public double calcularTaxa(Empresa empresa, Jogador visitante, int valorDado) {
        return valorDado * multiplicador;
    }
}
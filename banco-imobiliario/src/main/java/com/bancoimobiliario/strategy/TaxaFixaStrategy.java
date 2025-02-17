package com.bancoimobiliario.strategy;

import com.bancoimobiliario.models.Empresa;
import com.bancoimobiliario.models.Jogador;

public class TaxaFixaStrategy implements CalculoTaxaStrategy {
    @Override
    public double calcularTaxa(Empresa empresa, Jogador visitante, int valorDado) {
        return empresa.getTaxaUsoFixa();
    }
}
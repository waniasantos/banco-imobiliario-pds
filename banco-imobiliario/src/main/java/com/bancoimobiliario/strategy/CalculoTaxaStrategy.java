package com.bancoimobiliario.strategy;

import com.bancoimobiliario.models.Empresa;
import com.bancoimobiliario.models.Jogador;

public interface CalculoTaxaStrategy {
    double calcularTaxa(Empresa empresa, Jogador visitante, int valorDado);
}
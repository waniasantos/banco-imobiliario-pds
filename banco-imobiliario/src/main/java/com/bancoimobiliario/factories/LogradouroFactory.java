package com.bancoimobiliario.factories;

import com.bancoimobiliario.models.Logradouro;

public interface LogradouroFactory {
    Logradouro criarLogradouro(String nome, int posicao, double valorFinanceiro);
}
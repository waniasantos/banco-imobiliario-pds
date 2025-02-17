package com.bancoimobiliario.factories;

import com.bancoimobiliario.models.Empresa;
import com.bancoimobiliario.models.Logradouro;

public class EmpresaFactory implements LogradouroFactory {
    @Override
    public Logradouro criarLogradouro(String nome, int posicao, double valorCompra) {
        double taxaUsoFixa = valorCompra * 0.15;
        return new Empresa(nome, posicao, valorCompra, taxaUsoFixa);
    }
}
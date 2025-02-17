package com.bancoimobiliario.factories;

import com.bancoimobiliario.models.Imovel;
import com.bancoimobiliario.models.Logradouro;

public class ImovelFactory implements LogradouroFactory {
    @Override
    public Logradouro criarLogradouro(String nome, int posicao, double valorCompra) {
        double taxaAluguel = valorCompra * 0.1;
        return new Imovel(nome, posicao, valorCompra, taxaAluguel);
    }
}
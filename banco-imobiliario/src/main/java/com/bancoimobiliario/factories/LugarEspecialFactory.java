package com.bancoimobiliario.factories;

import com.bancoimobiliario.models.Logradouro;
import com.bancoimobiliario.models.LugarEspecial;

public class LugarEspecialFactory implements LogradouroFactory {
    private int movimento;
    
    public LugarEspecialFactory(int movimento) { this.movimento = movimento; }
    
    @Override
    public Logradouro criarLogradouro(String nome, int posicao, double valorSaldo) {
        return new LugarEspecial(nome, posicao, movimento, valorSaldo);
    }
}
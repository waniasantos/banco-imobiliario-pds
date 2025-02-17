// 5. Factory Registry (ou Creator)
package com.bancoimobiliario.factories;

import com.bancoimobiliario.models.Logradouro;
import com.bancoimobiliario.models.Tabuleiro;
import com.bancoimobiliario.models.LugarEspecial;

import java.util.ArrayList;
import java.util.List;

public class TabuleiroFactory {
    public static Tabuleiro criarTabuleiroDefault() {
        List<Logradouro> casas = new ArrayList<>();
        
        ImovelFactory imovelFactory = new ImovelFactory();
        EmpresaFactory empresaFactory = new EmpresaFactory();
        LugarEspecialFactory lugarBomFactory = new LugarEspecialFactory(2);
        LugarEspecialFactory lugarRuimFactory = new LugarEspecialFactory(-3);
        
        casas.add(new LugarEspecial("Ponto de Partida", 0, 0, 0));
        
        casas.add(imovelFactory.criarLogradouro("Leblon", 1, 100));
        casas.add(imovelFactory.criarLogradouro("Ipanema", 2, 120));
        
        casas.add(empresaFactory.criarLogradouro("Companhia Elétrica", 3, 150));
        
        casas.add(lugarRuimFactory.criarLogradouro("Imposto de Renda", 4, -200));
        
        casas.add(imovelFactory.criarLogradouro("Copacabana", 5, 140));
        casas.add(imovelFactory.criarLogradouro("Botafogo", 6, 160));
        
        casas.add(empresaFactory.criarLogradouro("Companhia de Água", 7, 150));
        
        casas.add(lugarBomFactory.criarLogradouro("Sorte Grande", 8, 100));
        
        return Tabuleiro.getInstance(casas, 0, 200.0);
    }
}
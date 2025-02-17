package com.bancoimobiliario.factories;

import com.bancoimobiliario.models.Empresa;
import com.bancoimobiliario.models.Logradouro;
import com.bancoimobiliario.strategy.TaxaBaseDadosStrategy;

public class EmpresaFactory implements LogradouroFactory {
    private boolean usarTaxaVariavel;
    private double multiplicadorDados;
    
    public EmpresaFactory() {
        this.usarTaxaVariavel = false;
    }
    
    public EmpresaFactory(boolean usarTaxaVariavel, double multiplicadorDados) {
        this.usarTaxaVariavel = usarTaxaVariavel;
        this.multiplicadorDados = multiplicadorDados;
    }
    
    @Override
    public Logradouro criarLogradouro(String nome, int posicao, double valorCompra) {
        double taxaUsoFixa = valorCompra * 0.15;
        Empresa empresa = new Empresa(nome, posicao, valorCompra, taxaUsoFixa);
        
        if (usarTaxaVariavel) {
            empresa.setEstrategiaCalculo(new TaxaBaseDadosStrategy(multiplicadorDados));
        }
        
        return empresa;
    }
}
package models;

import java.util.ArrayList;

public class Produto {
    private String formato; // CD, DVD, Blu-ray, Cartucho, Disquete
    private String plataforma; // PS4, XBOX, PC
    private String localizacao; 
    private double precoCusto;
    private double precoVenda;

    public Produto(String formato, String plataforma, String localizacao, double precoCusto, double precoVenda) {
        this.formato = formato;
        this.plataforma = plataforma;
        this.localizacao = localizacao;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }
}

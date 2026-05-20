package models;

public class Produtora {
    private String nome;
    private String morada;
    private String paginaWeb;
    private String email;
    private String estiloJogoPrincipal;
    private String observacoes; 

    public Produtora(String nome, String morada, String paginaWeb, String email, String estiloJogoPrincipal, String observacoes) {
        this.nome = nome;
        this.morada = morada;
        this.paginaWeb = paginaWeb;
        this.email = email;
        this.estiloJogoPrincipal = estiloJogoPrincipal;
        this.observacoes = observacoes;
    }

}

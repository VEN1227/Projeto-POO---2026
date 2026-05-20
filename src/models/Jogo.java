package models;

public class Jogo {
    private String nome;
    private int duracaoPrevista; // Em formato de minutos
    private String observacoes;
    private Diretor diretor;
    private Produtora produtora;
    private String estilo; // Estilo de jogop (aventura, fantasia, desporto, terror, etc)
    
    public Jogo(String nome, int duracaoPrevista, String observacoes, Diretor diretor, Produtora produtora, String estilo) {
        this.nome = nome;
        this.duracaoPrevista = duracaoPrevista;
        this.observacoes = observacoes;
        this.diretor = diretor;
        this.produtora = produtora;
        this.estilo = estilo.toLowerCase();
    }



}

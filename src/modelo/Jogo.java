package modelo;

import java.io.Serializable;

	/*Representa um Jogo.
  	Um jogo tem um diretor, uma produtora e um estilo*/

	public class Jogo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private int duracaoPrevista; 
    private String observacoes;
    private Diretor diretor;       
    private Produtora produtora;   
    private String estiloJogo;     

    public Jogo(int id, String nome, int duracaoPrevista, String observacoes,
                Diretor diretor, Produtora produtora, String estiloJogo) {
        this.id = id;
        this.nome = nome;
        this.duracaoPrevista = duracaoPrevista;
        this.observacoes = observacoes;
        this.diretor = diretor;
        this.produtora = produtora;
        this.estiloJogo = estiloJogo;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getDuracaoPrevista() { return duracaoPrevista; }
    public String getObservacoes() { return observacoes; }
    public Diretor getDiretor() { return diretor; }
    public Produtora getProdutora() { return produtora; }
    public String getEstiloJogo() { return estiloJogo; }

    // Setters (para edição)
    public void setNome(String nome) { this.nome = nome; }
    public void setDuracaoPrevista(int duracao) { this.duracaoPrevista = duracao; }
    public void setObservacoes(String obs) { this.observacoes = obs; }
    public void setEstiloJogo(String estilo) { this.estiloJogo = estilo; }

    @Override
    public String toString() {
        return "[" + id + "] " + nome
                + " | Estilo: " + estiloJogo
                + " | Duração: " + duracaoPrevista + " min"
                + " | Diretor: " + (diretor != null ? diretor.getNome() : "N/A")
                + " | Produtora: " + (produtora != null ? produtora.getNome() : "N/A");
    }
}

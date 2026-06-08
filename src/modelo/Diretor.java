package modelo;

import java.io.Serializable;


 /*Representa um Diretor de jogos.
 	Guardado em ficheiro de objetos (Serializable)*/

	public class Diretor implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String dataNascimento;
    private String email;
    private String paginaWeb;
    private String morada;
    private String moradaFas;
    private String observacoes;

    public Diretor(int id, String nome, String dataNascimento, String email,
                   String paginaWeb, String morada, String moradaFas, String observacoes) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.paginaWeb = paginaWeb;
        this.morada = morada;
        this.moradaFas = moradaFas;
        this.observacoes = observacoes;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDataNascimento() { return dataNascimento; }
    public String getEmail() { return email; }
    public String getPaginaWeb() { return paginaWeb; }
    public String getMorada() { return morada; }
    public String getMoradaFas() { return moradaFas; }
    public String getObservacoes() { return observacoes; }

    // Setters (para edição)
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setPaginaWeb(String paginaWeb) { this.paginaWeb = paginaWeb; }
    public void setMorada(String morada) { this.morada = morada; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " | Nascimento: " + dataNascimento + " | Email: " + email;
    }
}

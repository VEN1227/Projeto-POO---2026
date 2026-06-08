package modelo;

import java.io.Serializable;

/*
 * Representa uma Produtora de jogos.
 * Guardado em ficheiro de objetos (Serializable)
 */

public class Produtora implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String morada;
    private String paginaWeb;
    private String email;
    private String estiloJogosPrincipal;
    private String observacoes;

    public Produtora(int id, String nome, String morada, String paginaWeb,
                     String email, String estiloJogosPrincipal, String observacoes) {
        this.id = id;
        this.nome = nome;
        this.morada = morada;
        this.paginaWeb = paginaWeb;
        this.email = email;
        this.estiloJogosPrincipal = estiloJogosPrincipal;
        this.observacoes = observacoes;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getMorada() { return morada; }
    public String getPaginaWeb() { return paginaWeb; }
    public String getEmail() { return email; }
    public String getEstiloJogosPrincipal() { return estiloJogosPrincipal; }
    public String getObservacoes() { return observacoes; }

    // Setters (para edição)
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setMorada(String morada) { this.morada = morada; }
    public void setEstiloJogosPrincipal(String estilo) { this.estiloJogosPrincipal = estilo; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " | Estilo: " + estiloJogosPrincipal + " | Email: " + email;
    }
}

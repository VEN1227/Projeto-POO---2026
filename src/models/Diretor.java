package models;

import java.time.LocalDate;

public class Diretor {
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String paginaWeb;
    private String morada;
    private String moradaClubeFas;
    private String observacoes;

    public Diretor(String nome, LocalDate dataNascimento, String email, String paginaWeb, String morada, String moradaClubeFans, String observacoes) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.paginaWeb = paginaWeb;
        this.morada = morada;
        this.moradaClubeFas = moradaClubeFans;
        this.observacoes = observacoes;
    }
}

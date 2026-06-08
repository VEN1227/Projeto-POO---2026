package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Representa um Produto físico da loja (CD, DVD, Blu-ray, cartucho, etc.)
 * Um produto contém um ou mais jogos
 */

public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String titulo;
    private String formato;       // CD, DVD, Blu-ray, cartucho, disquete
    private String plataforma;    // PS4, XBOX, PC, etc. 
    private Localizacao localizacao; // pode ser null (produto não existe na loja)
    private double precoCusto;    
    private double precoVenda;    
    private int stock;
    private boolean ativo;        // para desabilitar sem apagar

    private List<Jogo> jogos;     // um produto tem 1 ou mais jogos

    public Produto(int id, String titulo, String formato, String plataforma,
                   Localizacao localizacao, double precoCusto, double precoVenda, int stock) {
        this.id = id;
        this.titulo = titulo;
        this.formato = formato;
        this.plataforma = plataforma;
        this.localizacao = localizacao;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.stock = stock;
        this.ativo = true;
        this.jogos = new ArrayList<>();
    }

    // --- Gestão de jogos ---
    public void adicionarJogo(Jogo jogo) {
        jogos.add(jogo);
    }

    public void removerJogo(Jogo jogo) {
        jogos.remove(jogo);
    }

    // --- Getters ---
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getFormato() { return formato; }
    public String getPlataforma() { return plataforma; }
    public Localizacao getLocalizacao() { return localizacao; }
    public double getPrecoCusto() { return precoCusto; }
    public double getPrecoVenda() { return precoVenda; }
    public int getStock() { return stock; }
    public boolean isAtivo() { return ativo; }
    public List<Jogo> getJogos() { return jogos; }

    // --- Setters (para edição) ---
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setFormato(String formato) { this.formato = formato; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }
    public void setLocalizacao(Localizacao loc) { this.localizacao = loc; }
    public void setPrecoCusto(double precoCusto) { this.precoCusto = precoCusto; }
    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }
    public void setStock(int stock) { this.stock = stock; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    // Diminui stock após venda
    public boolean diminuirStock(int quantidade) {
        if (stock >= quantidade) {
            stock -= quantidade;
            return true;
        }
        return false; // stock insuficiente
    }

    @Override
    public String toString() {
        String loc = (localizacao != null) ? localizacao.toString() : "Sem localização na loja";
        return "[" + id + "] " + titulo
                + " | Formato: " + formato
                + " | Plataforma: " + plataforma
                + " | Preço: " + precoVenda + "€"
                + " | Stock: " + stock
                + " | Local: " + loc
                + (ativo ? "" : " [DESATIVADO]");
    }
}

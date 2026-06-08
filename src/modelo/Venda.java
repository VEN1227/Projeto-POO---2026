package modelo;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * Representa uma Venda registada na loja
 * Guardada em ficheiro de texto.
 */

public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nomeEmpregado;   // empregado que registou
    private Produto produto;         // o que foi vendido
    private int quantidade;          
    private double custoTotal;       // preço total
    private LocalDate dataVenda;     

    public Venda(int id, String nomeEmpregado, Produto produto, int quantidade, LocalDate dataVenda) {
        this.id = id;
        this.nomeEmpregado = nomeEmpregado;
        this.produto = produto;
        this.quantidade = quantidade;
        this.custoTotal = produto.getPrecoVenda() * quantidade;
        this.dataVenda = dataVenda;
    }

    // Getters
    public int getId() { return id; }
    public String getNomeEmpregado() { return nomeEmpregado; }
    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getCustoTotal() { return custoTotal; }
    public LocalDate getDataVenda() { return dataVenda; }

    // Formato para guardar em ficheiro de texto
    public String toTexto() {
        return id + ";" + nomeEmpregado + ";" + produto.getTitulo() + ";"
                + quantidade + ";" + custoTotal + ";" + dataVenda.toString();
    }

    @Override
    public String toString() {
        return "[Venda #" + id + "] " + produto.getTitulo()
                + " x" + quantidade
                + " | Total: " + custoTotal + "€"
                + " | Empregado: " + nomeEmpregado
                + " | Data: " + dataVenda;
    }
}

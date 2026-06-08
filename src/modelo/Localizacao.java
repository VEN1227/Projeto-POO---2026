package modelo;

import java.io.Serializable;

/*
 * Representa a localização de um produto na loja.
 * Pode ser uma área ou uma prateleira.
 * Um produto pode não ter localização (não existir fisicamente na loja).
 */

public class Localizacao implements Serializable {

    private static final long serialVersionUID = 1L;

    // Tipos de localização possíveis
    public static final String TIPO_AREA = "Área";
    public static final String TIPO_PRATELEIRA = "Prateleira";

    private String tipo;        // "Área" ou "Prateleira"
    private String descricao;    

    public Localizacao(String tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public String getTipo() { return tipo; }
    public String getDescricao() { return descricao; }

    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return tipo + ": " + descricao;
    }
}

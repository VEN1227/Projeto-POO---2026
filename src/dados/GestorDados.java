package dados;

import modelo.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class GestorDados {

    // Caminhos dos ficheiros de dados
    private static final String FICHEIRO_DIRETORES  = "data/diretores.ser";
    private static final String FICHEIRO_PRODUTORAS  = "data/produtoras.ser";
    private static final String FICHEIRO_JOGOS       = "data/jogos.ser";
    private static final String FICHEIRO_PRODUTOS    = "data/produtos.ser";
    private static final String FICHEIRO_VENDAS      = "data/vendas.txt";
    private static final String FICHEIRO_PASSWORD    = "data/password.txt";

    // Listas em memória
    private List<Diretor> diretores;
    private List<Produtora> produtoras;
    private List<Jogo> jogos;
    private List<Produto> produtos;
    private List<Venda> vendas;

    // Contadores de IDs
    private int proximoIdDiretor  = 1;
    private int proximoIdProdutora = 1;
    private int proximoIdJogo     = 1;
    private int proximoIdProduto  = 1;
    private int proximoIdVenda    = 1;

    public GestorDados() {
        diretores  = new ArrayList<>();
        produtoras  = new ArrayList<>();
        jogos       = new ArrayList<>();
        produtos    = new ArrayList<>();
        vendas      = new ArrayList<>();
        criarPastaDados();
    }

    // ==================== INICIALIZAÇÃO ====================

    private void criarPastaDados() {
        File pasta = new File("data");
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    // Carrega todos os dados dos ficheiros ao iniciar o programa
    public void carregarTudo() {
        carregarDiretores();
        carregarProdutoras();
        carregarJogos();
        carregarProdutos();
        // Vendas: lidas do texto mas guardamos em memória como objetos simples
        System.out.println("Dados carregados com sucesso.");
    }

    /** Guarda todos os dados nos ficheiros */
    public void guardarTudo() {
        guardarDiretores();
        guardarProdutoras();
        guardarJogos();
        guardarProdutos();
        System.out.println("Dados guardados com sucesso.");
    }

    // ==================== DIRETORES ====================

    public void adicionarDiretor(Diretor d) {
        diretores.add(d);
        guardarDiretores();
    }

    public List<Diretor> getDiretores() { return diretores; }

    public Diretor encontrarDiretorPorId(int id) {
        for (Diretor d : diretores) {
            if (d.getId() == id) return d;
        }
        return null;
    }

    public List<Diretor> pesquisarDiretoresPorNome(String nome) {
        List<Diretor> resultado = new ArrayList<>();
        for (Diretor d : diretores) {
            if (d.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(d);
            }
        }
        return resultado;
    }

    public int getProximoIdDiretor() { return proximoIdDiretor++; }

    // ==================== PRODUTORAS ====================

    public void adicionarProdutora(Produtora p) {
        produtoras.add(p);
        guardarProdutoras();
    }

    public List<Produtora> getProdutoras() { return produtoras; }

    public Produtora encontrarProdutoraPorId(int id) {
        for (Produtora p : produtoras) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public int getProximoIdProdutora() { return proximoIdProdutora++; }

    // ==================== JOGOS ====================

    public void adicionarJogo(Jogo j) {
        jogos.add(j);
        guardarJogos();
    }

    public List<Jogo> getJogos() { return jogos; }

    public Jogo encontrarJogoPorId(int id) {
        for (Jogo j : jogos) {
            if (j.getId() == id) return j;
        }
        return null;
    }

    // Pesquisa jogos pelo nome
    public List<Jogo> pesquisarJogosPorNome(String nome) {
        List<Jogo> resultado = new ArrayList<>();
        for (Jogo j : jogos) {
            if (j.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(j);
            }
        }
        return resultado;
    }

    // Ver outros jogos do mesmo diretor
    public List<Jogo> jogosDoMesmoDiretor(Diretor diretor) {
        List<Jogo> resultado = new ArrayList<>();
        for (Jogo j : jogos) {
            if (j.getDiretor() != null && j.getDiretor().getId() == diretor.getId()) {
                resultado.add(j);
            }
        }
        return resultado;
    }

    // Ver outros jogos do mesmo estilo
    public List<Jogo> jogosDoMesmoEstilo(String estilo) {
        List<Jogo> resultado = new ArrayList<>();
        for (Jogo j : jogos) {
            if (j.getEstiloJogo().equalsIgnoreCase(estilo)) {
                resultado.add(j);
            }
        }
        return resultado;
    }

    // Ver que diretores trabalham para uma produtora
    public List<Diretor> diretoresDaProdutora(Produtora produtora) {
        List<Diretor> resultado = new ArrayList<>();
        for (Jogo j : jogos) {
            if (j.getProdutora() != null && j.getProdutora().getId() == produtora.getId()) {
                Diretor d = j.getDiretor();
                if (d != null && !resultado.contains(d)) {
                    resultado.add(d);
                }
            }
        }
        return resultado;
    }

    public int getProximoIdJogo() { return proximoIdJogo++; }

    // ==================== PRODUTOS ====================

    public void adicionarProduto(Produto p) {
        produtos.add(p);
        guardarProdutos();
    }

    public List<Produto> getProdutos() { return produtos; }

    public Produto encontrarProdutoPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    // Encontrar produtos que contêm um determinado jogo
    public List<Produto> produtosComJogo(Jogo jogo) {
        List<Produto> resultado = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getJogos().contains(jogo)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public int getProximoIdProduto() { return proximoIdProduto++; }

    // ==================== VENDAS ====================

    // Regista uma venda e guarda em ficheiro de texto
    public boolean registarVenda(String nomeEmpregado, int idProduto, int quantidade) {
        Produto produto = encontrarProdutoPorId(idProduto);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return false;
        }
        if (!produto.diminuirStock(quantidade)) {
            System.out.println("Stock insuficiente.");
            return false;
        }
        Venda venda = new Venda(proximoIdVenda++, nomeEmpregado, produto, quantidade, LocalDate.now());
        vendas.add(venda);
        guardarProdutos(); // atualiza stock
        guardarVendaTexto(venda);
        return true;
    }

    public List<Venda> getVendas() { return vendas; }

    // ==================== PERSISTÊNCIA ====================

    @SuppressWarnings("unchecked")
    private void carregarDiretores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHEIRO_DIRETORES))) {
            diretores = (List<Diretor>) ois.readObject();
            // Atualizar contador de IDs
            for (Diretor d : diretores) {
                if (d.getId() >= proximoIdDiretor) proximoIdDiretor = d.getId() + 1;
            }
        } catch (FileNotFoundException e) {
            // Normal na primeira vez
        } catch (Exception e) {
            System.out.println("Erro ao carregar diretores: " + e.getMessage());
        }
    }

    private void guardarDiretores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHEIRO_DIRETORES))) {
            oos.writeObject(diretores);
        } catch (Exception e) {
            System.out.println("Erro ao guardar diretores: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarProdutoras() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHEIRO_PRODUTORAS))) {
            produtoras = (List<Produtora>) ois.readObject();
            for (Produtora p : produtoras) {
                if (p.getId() >= proximoIdProdutora) proximoIdProdutora = p.getId() + 1;
            }
        } catch (FileNotFoundException e) {
            // Normal na primeira vez
        } catch (Exception e) {
            System.out.println("Erro ao carregar produtoras: " + e.getMessage());
        }
    }

    private void guardarProdutoras() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHEIRO_PRODUTORAS))) {
            oos.writeObject(produtoras);
        } catch (Exception e) {
            System.out.println("Erro ao guardar produtoras: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarJogos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHEIRO_JOGOS))) {
            jogos = (List<Jogo>) ois.readObject();
            for (Jogo j : jogos) {
                if (j.getId() >= proximoIdJogo) proximoIdJogo = j.getId() + 1;
            }
        } catch (FileNotFoundException e) {
            // Normal na primeira vez
        } catch (Exception e) {
            System.out.println("Erro ao carregar jogos: " + e.getMessage());
        }
    }

    private void guardarJogos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHEIRO_JOGOS))) {
            oos.writeObject(jogos);
        } catch (Exception e) {
            System.out.println("Erro ao guardar jogos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarProdutos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHEIRO_PRODUTOS))) {
            produtos = (List<Produto>) ois.readObject();
            for (Produto p : produtos) {
                if (p.getId() >= proximoIdProduto) proximoIdProduto = p.getId() + 1;
            }
        } catch (FileNotFoundException e) {
            // Normal na primeira vez
        } catch (Exception e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
        }
    }

    private void guardarProdutos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHEIRO_PRODUTOS))) {
            oos.writeObject(produtos);
        } catch (Exception e) {
            System.out.println("Erro ao guardar produtos: " + e.getMessage());
        }
    }

    // Guarda uma venda em ficheiro de texto
    private void guardarVendaTexto(Venda venda) {
        try (FileWriter fw = new FileWriter(FICHEIRO_VENDAS, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(venda.toTexto());
            bw.newLine();
        } catch (Exception e) {
            System.out.println("Erro ao guardar venda: " + e.getMessage());
        }
    }

    // ==================== PASSWORD ====================

    // Verifica a password do administrador
    public boolean verificarPassword(String tentativa) {
        String passwordGuardada = lerPassword();
        if (passwordGuardada == null) {
            // Primeira vez: password padrão é "admin"
            return tentativa.equals("admin");
        }
        // Compara com a password (encriptada com base64 simples)
        String tentativaEncriptada = encriptar(tentativa);
        return tentativaEncriptada.equals(passwordGuardada);
    }

    // Guarda a password encriptada após o primeiro login
    public void guardarPasswordEncriptada(String password) {
        try (FileWriter fw = new FileWriter(FICHEIRO_PASSWORD);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(encriptar(password));
        } catch (Exception e) {
            System.out.println("Erro ao guardar password: " + e.getMessage());
        }
    }

    private String lerPassword() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHEIRO_PASSWORD))) {
            return br.readLine();
        } catch (FileNotFoundException e) {
            return null; // primeira vez
        } catch (Exception e) {
            return null;
        }
    }

    // Encriptação simples com Base64
    private String encriptar(String texto) {
        return java.util.Base64.getEncoder().encodeToString(texto.getBytes());
    }
}

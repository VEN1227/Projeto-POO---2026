package menus;

import dados.GestorDados;
import modelo.*;

import java.util.List;
import java.util.Scanner;

 // Menu da Zona Administrador 

public class MenuAdmin {

    private GestorDados gestor;
    private Scanner scanner;

    public MenuAdmin(GestorDados gestor, Scanner scanner) {
        this.gestor = gestor;
        this.scanner = scanner;
    }

    public void mostrar() {
        int opcao;
        do {
            System.out.println("\n========== ZONA ADMINISTRADOR ==========");
            System.out.println("1. Gerir Diretores");
            System.out.println("2. Gerir Produtoras");
            System.out.println("3. Gerir Jogos");
            System.out.println("4. Gerir Produtos");
            System.out.println("5. Registar Venda");
            System.out.println("6. Ver todas as Vendas");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1: menuDiretores(); break;
                case 2: menuProdutoras(); break;
                case 3: menuJogos(); break;
                case 4: menuProdutos(); break;
                case 5: registarVenda(); break;
                case 6: listarVendas(); break;
                case 0: System.out.println("A sair da zona de administrador..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // ==================== DIRETORES ====================

    private void menuDiretores() {
        int opcao;
        do {
            System.out.println("\n--- Gestão de Diretores ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Inserir novo");
            System.out.println("3. Editar");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = lerInteiro();
            switch (opcao) {
                case 1: listarDiretores(); break;
                case 2: inserirDiretor(); break;
                case 3: editarDiretor(); break;
            }
        } while (opcao != 0);
    }

    private void listarDiretores() {
        List<Diretor> lista = gestor.getDiretores();
        if (lista.isEmpty()) {
            System.out.println("Nenhum diretor registado.");
        } else {
            System.out.println("\n--- Diretores ---");
            for (Diretor d : lista) {
                System.out.println(d);
                System.out.println("  Nascimento: " + d.getDataNascimento()
                        + " | Web: " + d.getPaginaWeb()
                        + " | Fãs: " + d.getMoradaFas()
                        + " | Obs: " + d.getObservacoes());
            }
        }
    }

    private void inserirDiretor() {
        System.out.println("\n-- Inserir Diretor --");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Data de nascimento (dd-mm-aaaa): ");
        String dataNasc = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Página web: ");
        String web = scanner.nextLine();
        System.out.print("Morada: ");
        String morada = scanner.nextLine();
        System.out.print("Morada clube de fãs: ");
        String moradaFas = scanner.nextLine();
        System.out.print("Observações: ");
        String obs = scanner.nextLine();

        Diretor d = new Diretor(gestor.getProximoIdDiretor(), nome, dataNasc, email, web, morada, moradaFas, obs);
        gestor.adicionarDiretor(d);
        System.out.println("Diretor inserido com ID: " + d.getId());
    }

    private void editarDiretor() {
        listarDiretores();
        System.out.print("ID do diretor a editar: ");
        int id = lerInteiro();
        Diretor d = gestor.encontrarDiretorPorId(id);
        if (d == null) { System.out.println("Diretor não encontrado."); return; }

        System.out.print("Novo nome (Enter para manter '" + d.getNome() + "'): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) d.setNome(nome);

        System.out.print("Novo email (Enter para manter '" + d.getEmail() + "'): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) d.setEmail(email);

        System.out.print("Novas observações (Enter para manter): ");
        String obs = scanner.nextLine();
        if (!obs.isEmpty()) d.setObservacoes(obs);

        gestor.guardarTudo();
        System.out.println("Diretor atualizado.");
    }

    // ==================== PRODUTORAS ====================

    private void menuProdutoras() {
        int opcao;
        do {
            System.out.println("\n--- Gestão de Produtoras ---");
            System.out.println("1. Listar todas");
            System.out.println("2. Inserir nova");
            System.out.println("3. Editar");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = lerInteiro();
            switch (opcao) {
                case 1: listarProdutoras(); break;
                case 2: inserirProdutora(); break;
                case 3: editarProdutora(); break;
            }
        } while (opcao != 0);
    }

    private void listarProdutoras() {
        List<Produtora> lista = gestor.getProdutoras();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma produtora registada.");
        } else {
            System.out.println("\n--- Produtoras ---");
            for (Produtora p : lista) System.out.println(p);
        }
    }

    private void inserirProdutora() {
        System.out.println("\n-- Inserir Produtora --");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Morada: ");
        String morada = scanner.nextLine();
        System.out.print("Página web: ");
        String web = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Estilo de jogos principal: ");
        String estilo = scanner.nextLine();
        System.out.print("Observações: ");
        String obs = scanner.nextLine();

        Produtora p = new Produtora(gestor.getProximoIdProdutora(), nome, morada, web, email, estilo, obs);
        gestor.adicionarProdutora(p);
        System.out.println("Produtora inserida com ID: " + p.getId());
    }

    private void editarProdutora() {
        listarProdutoras();
        System.out.print("ID da produtora a editar: ");
        int id = lerInteiro();
        Produtora p = gestor.encontrarProdutoraPorId(id);
        if (p == null) { System.out.println("Produtora não encontrada."); return; }

        System.out.print("Novo nome (Enter para manter '" + p.getNome() + "'): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) p.setNome(nome);

        System.out.print("Novo email (Enter para manter '" + p.getEmail() + "'): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) p.setEmail(email);

        gestor.guardarTudo();
        System.out.println("Produtora atualizada.");
    }

    // ==================== JOGOS ====================

    private void menuJogos() {
        int opcao;
        do {
            System.out.println("\n--- Gestão de Jogos ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Inserir novo");
            System.out.println("3. Editar");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = lerInteiro();
            switch (opcao) {
                case 1: listarJogos(); break;
                case 2: inserirJogo(); break;
                case 3: editarJogo(); break;
            }
        } while (opcao != 0);
    }

    private void listarJogos() {
        List<Jogo> lista = gestor.getJogos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum jogo registado.");
        } else {
            System.out.println("\n--- Jogos ---");
            for (Jogo j : lista) System.out.println(j);
        }
    }

    private void inserirJogo() {
        System.out.println("\n-- Inserir Jogo --");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Duração prevista (minutos): ");
        int duracao = lerInteiro();
        System.out.print("Observações: ");
        String obs = scanner.nextLine();
        System.out.print("Estilo (aventura/fantasia/desporto/terror/outro): ");
        String estilo = scanner.nextLine();

        // Selecionar Diretor
        listarDiretores();
        System.out.print("ID do Diretor (0 para nenhum): ");
        int idDiretor = lerInteiro();
        Diretor diretor = (idDiretor > 0) ? gestor.encontrarDiretorPorId(idDiretor) : null;

        // Selecionar Produtora
        listarProdutoras();
        System.out.print("ID da Produtora (0 para nenhuma): ");
        int idProdutora = lerInteiro();
        Produtora produtora = (idProdutora > 0) ? gestor.encontrarProdutoraPorId(idProdutora) : null;

        Jogo j = new Jogo(gestor.getProximoIdJogo(), nome, duracao, obs, diretor, produtora, estilo);
        gestor.adicionarJogo(j);
        System.out.println("Jogo inserido com ID: " + j.getId());
    }

    private void editarJogo() {
        listarJogos();
        System.out.print("ID do jogo a editar: ");
        int id = lerInteiro();
        Jogo j = gestor.encontrarJogoPorId(id);
        if (j == null) { System.out.println("Jogo não encontrado."); return; }

        System.out.print("Novo nome (Enter para manter '" + j.getNome() + "'): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) j.setNome(nome);

        System.out.print("Novo estilo (Enter para manter '" + j.getEstiloJogo() + "'): ");
        String estilo = scanner.nextLine();
        if (!estilo.isEmpty()) j.setEstiloJogo(estilo);

        gestor.guardarTudo();
        System.out.println("Jogo atualizado.");
    }

    // ==================== PRODUTOS ====================

    private void menuProdutos() {
        int opcao;
        do {
            System.out.println("\n--- Gestão de Produtos ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Inserir novo");
            System.out.println("3. Editar");
            System.out.println("4. Desativar produto");
            System.out.println("5. Associar jogo a produto");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = lerInteiro();
            switch (opcao) {
                case 1: listarProdutos(); break;
                case 2: inserirProduto(); break;
                case 3: editarProduto(); break;
                case 4: desativarProduto(); break;
                case 5: associarJogoAProduto(); break;
            }
        } while (opcao != 0);
    }

    private void listarProdutos() {
        List<Produto> lista = gestor.getProdutos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto registado.");
        } else {
            System.out.println("\n--- Produtos ---");
            for (Produto p : lista) System.out.println(p);
        }
    }

    private void inserirProduto() {
        System.out.println("\n-- Inserir Produto --");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Formato (CD/DVD/Blu-ray/Cartucho/Disquete/Outro): ");
        String formato = scanner.nextLine();
        System.out.print("Plataforma (PS4/XBOX/PC/etc.): ");
        String plataforma = scanner.nextLine();
        System.out.print("Preço de custo (€): ");
        double precoCusto = lerDouble();
        System.out.print("Preço de venda (€): ");
        double precoVenda = lerDouble();
        System.out.print("Stock inicial: ");
        int stock = lerInteiro();

        // Localização (pode ser null - R5)
        System.out.print("Tem localização na loja? (s/n): ");
        String temLoc = scanner.nextLine();
        Localizacao loc = null;
        if (temLoc.equalsIgnoreCase("s")) {
            System.out.print("Tipo de localização (1-Área / 2-Prateleira): ");
            int tipoLoc = lerInteiro();
            String tipo = (tipoLoc == 1) ? Localizacao.TIPO_AREA : Localizacao.TIPO_PRATELEIRA;
            System.out.print("Descrição (ex: A3 ou Prateleira 2): ");
            String desc = scanner.nextLine();
            loc = new Localizacao(tipo, desc);
        }

        Produto p = new Produto(gestor.getProximoIdProduto(), titulo, formato, plataforma, loc, precoCusto, precoVenda, stock);
        gestor.adicionarProduto(p);
        System.out.println("Produto inserido com ID: " + p.getId());
    }

    private void editarProduto() {
        listarProdutos();
        System.out.print("ID do produto a editar: ");
        int id = lerInteiro();
        Produto p = gestor.encontrarProdutoPorId(id);
        if (p == null) { System.out.println("Produto não encontrado."); return; }

        System.out.print("Novo título (Enter para manter '" + p.getTitulo() + "'): ");
        String titulo = scanner.nextLine();
        if (!titulo.isEmpty()) p.setTitulo(titulo);

        System.out.print("Novo preço de venda (0 para manter " + p.getPrecoVenda() + "€): ");
        double preco = lerDouble();
        if (preco > 0) p.setPrecoVenda(preco);

        System.out.print("Novo stock (0 para manter " + p.getStock() + "): ");
        int stock = lerInteiro();
        if (stock > 0) p.setStock(stock);

        gestor.guardarTudo();
        System.out.println("Produto atualizado.");
    }

    // Desativar produto sem apagar
    private void desativarProduto() {
        listarProdutos();
        System.out.print("ID do produto a desativar: ");
        int id = lerInteiro();
        Produto p = gestor.encontrarProdutoPorId(id);
        if (p == null) { System.out.println("Produto não encontrado."); return; }
        p.setAtivo(false);
        gestor.guardarTudo();
        System.out.println("Produto '" + p.getTitulo() + "' desativado.");
    }

    // Associar jogo a produto
    private void associarJogoAProduto() {
        listarProdutos();
        System.out.print("ID do produto: ");
        int idProduto = lerInteiro();
        Produto produto = gestor.encontrarProdutoPorId(idProduto);
        if (produto == null) { System.out.println("Produto não encontrado."); return; }

        listarJogos();
        System.out.print("ID do jogo a associar: ");
        int idJogo = lerInteiro();
        Jogo jogo = gestor.encontrarJogoPorId(idJogo);
        if (jogo == null) { System.out.println("Jogo não encontrado."); return; }

        produto.adicionarJogo(jogo);
        gestor.guardarTudo();
        System.out.println("Jogo '" + jogo.getNome() + "' associado ao produto '" + produto.getTitulo() + "'.");
    }

    // ==================== VENDAS ====================

    // Registar venda
    private void registarVenda() {
        System.out.println("\n-- Registar Venda --");
        System.out.print("Nome do empregado: ");
        String empregado = scanner.nextLine();

        listarProdutos();
        System.out.print("ID do produto vendido: ");
        int idProduto = lerInteiro();

        System.out.print("Quantidade: ");
        int quantidade = lerInteiro();

        boolean sucesso = gestor.registarVenda(empregado, idProduto, quantidade);
        if (sucesso) {
            System.out.println("Venda registada com sucesso.");
        }
    }

    private void listarVendas() {
        List<Venda> vendas = gestor.getVendas();
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registada nesta sessão.");
            System.out.println("(As vendas anteriores estão no ficheiro data/vendas.txt)");
        } else {
            System.out.println("\n--- Vendas desta sessão ---");
            for (Venda v : vendas) System.out.println(v);
        }
    }

    // ==================== UTILITÁRIOS ====================

    private void listarDiretores() {
        System.out.println("\n--- Diretores disponíveis ---");
        List<Diretor> lista = gestor.getDiretores();
        if (lista.isEmpty()) System.out.println("(Nenhum diretor registado)");
        else for (Diretor d : lista) System.out.println(d);
    }

    private void listarProdutoras() {
        System.out.println("\n--- Produtoras disponíveis ---");
        List<Produtora> lista = gestor.getProdutoras();
        if (lista.isEmpty()) System.out.println("(Nenhuma produtora registada)");
        else for (Produtora p : lista) System.out.println(p);
    }

    private int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private double lerDouble() {
        try {
            return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}

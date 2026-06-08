package menus;

import dados.GestorDados;
import modelo.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

	// Menu da Zona Cliente

public class MenuCliente {

    private GestorDados gestor;
    private Scanner scanner;

    public MenuCliente(GestorDados gestor, Scanner scanner) {
        this.gestor = gestor;
        this.scanner = scanner;
    }

    public void mostrar() {
        int opcao;
        do {
            System.out.println("\n========== ZONA CLIENTE ==========");
            System.out.println("1. Pesquisar jogo por nome");
            System.out.println("2. Ver jogos de um diretor");
            System.out.println("3. Ver jogos de um produto");
            System.out.println("4. Pesquisar diretor por nome");
            System.out.println("5. Ver diretores de uma produtora");
            System.out.println("6. Ver jogos do mesmo estilo");
            System.out.println("7. Guardar pesquisa em ficheiro de texto");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1: pesquisarJogoPorNome(); break;
                case 2: verJogosDeDiretor(); break;
                case 3: verJogosDeProduto(); break;
                case 4: pesquisarDiretorPorNome(); break;
                case 5: verDiretoresDeProdutora(); break;
                case 6: verJogosDoMesmoEstilo(); break;
                case 7: guardarPesquisaFicheiro(); break;
                case 0: System.out.println("A sair da zona de cliente..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // --------Pesquisar jogo por nome--------
    private void pesquisarJogoPorNome() {
        System.out.print("Nome do jogo (ou parte): ");
        String nome = scanner.nextLine();
        List<Jogo> resultado = gestor.pesquisarJogosPorNome(nome);

        if (resultado.isEmpty()) {
            System.out.println("Nenhum jogo encontrado.");
            return;
        }

        System.out.println("\n--- Jogos encontrados ---");
        for (Jogo j : resultado) {
            System.out.println(j);
            // Mostrar em que produtos aparece
            List<Produto> produtos = gestor.produtosComJogo(j);
            if (!produtos.isEmpty()) {
                System.out.println("  -> Disponível nos produtos:");
                for (Produto p : produtos) {
                    System.out.println("     " + p);
                }
            }
        }

        // ver outros jogos do mesmo diretor
        if (!resultado.isEmpty()) {
            Jogo primeiroJogo = resultado.get(0);
            if (primeiroJogo.getDiretor() != null) {
                System.out.println("\n--- Outros jogos do mesmo diretor (" + primeiroJogo.getDiretor().getNome() + ") ---");
                List<Jogo> mesmoDiretor = gestor.jogosDoMesmoDiretor(primeiroJogo.getDiretor());
                for (Jogo j : mesmoDiretor) {
                    if (j.getId() != primeiroJogo.getId()) {
                        System.out.println("  " + j);
                    }
                }
            }
        }
    }

    // --------Ver todos os jogos de um diretor--------
    private void verJogosDeDiretor() {
        listarDiretores();
        System.out.print("ID do diretor: ");
        int id = lerInteiro();
        Diretor diretor = gestor.encontrarDiretorPorId(id);
        if (diretor == null) {
            System.out.println("Diretor não encontrado.");
            return;
        }
        List<Jogo> jogos = gestor.jogosDoMesmoDiretor(diretor);
        System.out.println("\n--- Jogos de " + diretor.getNome() + " ---");
        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo registado para este diretor.");
        } else {
            for (Jogo j : jogos) System.out.println(j);
        }
    }

    // --------Ver jogos de um produto--------
    private void verJogosDeProduto() {
        listarProdutos();
        System.out.print("ID do produto: ");
        int id = lerInteiro();
        Produto produto = gestor.encontrarProdutoPorId(id);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        System.out.println("\n--- Jogos em: " + produto.getTitulo() + " ---");
        if (produto.getJogos().isEmpty()) {
            System.out.println("Nenhum jogo associado a este produto.");
        } else {
            for (Jogo j : produto.getJogos()) System.out.println(j);
        }
    }

    // --------Pesquisar diretor por nome--------
    private void pesquisarDiretorPorNome() {
        System.out.print("Nome do diretor (ou parte): ");
        String nome = scanner.nextLine();
        List<Diretor> resultado = gestor.pesquisarDiretoresPorNome(nome);
        System.out.println("\n--- Diretores encontrados ---");
        if (resultado.isEmpty()) {
            System.out.println("Nenhum diretor encontrado.");
        } else {
            for (Diretor d : resultado) System.out.println(d);
        }
    }

    // --------Ver diretores de uma produtora--------
    private void verDiretoresDeProdutora() {
        listarProdutoras();
        System.out.print("ID da produtora: ");
        int id = lerInteiro();
        Produtora produtora = gestor.encontrarProdutoraPorId(id);
        if (produtora == null) {
            System.out.println("Produtora não encontrada.");
            return;
        }
        List<Diretor> diretores = gestor.diretoresDaProdutora(produtora);
        System.out.println("\n--- Diretores que trabalham para " + produtora.getNome() + " ---");
        if (diretores.isEmpty()) {
            System.out.println("Nenhum diretor encontrado para esta produtora.");
        } else {
            for (Diretor d : diretores) System.out.println(d);
        }
    }

    // --------Ver jogos do mesmo estilo--------
    private void verJogosDoMesmoEstilo() {
        System.out.print("Estilo de jogo (aventura, fantasia, desporto, terror, etc.): ");
        String estilo = scanner.nextLine();
        List<Jogo> jogos = gestor.jogosDoMesmoEstilo(estilo);
        System.out.println("\n--- Jogos do estilo '" + estilo + "' ---");
        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo encontrado com esse estilo.");
        } else {
            for (Jogo j : jogos) System.out.println(j);
        }
    }

    // -------- Guardar pesquisa em ficheiro--------
    private void guardarPesquisaFicheiro() {
        System.out.print("Nome do ficheiro para guardar (ex: pesquisa.txt): ");
        String nomeFicheiro = scanner.nextLine();
        System.out.print("O que pretende guardar? (nome do jogo a pesquisar): ");
        String nome = scanner.nextLine();
        List<Jogo> resultado = gestor.pesquisarJogosPorNome(nome);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeFicheiro))) {
            bw.write("Pesquisa de jogos: " + nome);
            bw.newLine();
            bw.write("Data: " + LocalDate.now());
            bw.newLine();
            bw.write("-----------------------------------");
            bw.newLine();
            if (resultado.isEmpty()) {
                bw.write("Nenhum resultado encontrado.");
                bw.newLine();
            } else {
                for (Jogo j : resultado) {
                    bw.write(j.toString());
                    bw.newLine();
                }
            }
            System.out.println("Pesquisa guardada em: " + nomeFicheiro);
        } catch (Exception e) {
            System.out.println("Erro ao guardar ficheiro: " + e.getMessage());
        }
    }

    // -------- Utilitários --------
    private void listarDiretores() {
        System.out.println("\n--- Lista de Diretores ---");
        for (Diretor d : gestor.getDiretores()) System.out.println(d);
    }

    private void listarProdutos() {
        System.out.println("\n--- Lista de Produtos ---");
        for (Produto p : gestor.getProdutos()) {
            if (p.isAtivo()) System.out.println(p);
        }
    }

    private void listarProdutoras() {
        System.out.println("\n--- Lista de Produtoras ---");
        for (Produtora p : gestor.getProdutoras()) System.out.println(p);
    }

    private int lerInteiro() {
        try {
            int val = Integer.parseInt(scanner.nextLine().trim());
            return val;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

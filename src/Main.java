import dados.GestorDados;
import menus.MenuAdmin;
import menus.MenuCliente;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GestorDados gestor = new GestorDados();

        // Carrega todos os dados dos ficheiros ao iniciar
        gestor.carregarTudo();

        System.out.println("=======================================");
        System.out.println("   BEM-VINDO À LOJA DE JOGOS");
        System.out.println("=======================================");

        int opcao;
        do {
            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1. Zona Cliente");
            System.out.println("2. Zona Administrador");
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    // Zona cliente - livre acesso
                    MenuCliente menuCliente = new MenuCliente(gestor, scanner);
                    menuCliente.mostrar();
                    break;

                case 2:
                    // Zona admin - requer password
                    if (autenticarAdmin(gestor, scanner)) {
                        MenuAdmin menuAdmin = new MenuAdmin(gestor, scanner);
                        menuAdmin.mostrar();
                    }
                    break;

                case 0:
                    // Guardar todos os dados antes de sair
                    gestor.guardarTudo();
                    System.out.println("Até breve!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }


     //Autentica o administrador com password

    private static boolean autenticarAdmin(GestorDados gestor, Scanner scanner) {
        System.out.print("\nPassword de administrador: ");
        String tentativa = scanner.nextLine();

        if (gestor.verificarPassword(tentativa)) {
            // Se password correta, garantir que está encriptada no ficheiro 
            gestor.guardarPasswordEncriptada(tentativa);
            System.out.println("Acesso concedido!");
            return true;
        } else {
            System.out.println("Password incorreta. Acesso negado.");
            return false;
        }
    }
}

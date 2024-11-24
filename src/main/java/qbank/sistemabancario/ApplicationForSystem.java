package qbank.sistemabancario;

import qbank.sistemabancario.service.AccountUser;
import java.util.Scanner;

public class ApplicationForSystem {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Selecione uma opção:\n1 - Criar Conta\n2 - Entrar na Conta\n3 - Sair");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> AccountUser.verificarDuplicidade(scanner);
                    case 2 -> AccountUser.entrarNaConta(scanner);
                    case 3 -> {
                        System.out.println("Obrigado por usar o sistema bancário!");
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package qbank.sistemabancario.service;

import qbank.sistemabancario.context.pessoa.Pessoa;

import java.util.Scanner;

public class PessoaService {
    public static Pessoa obterPessoa(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("RG: ");
        String rg = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        return new Pessoa(nome, cpf, rg, email, telefone);
    }

}

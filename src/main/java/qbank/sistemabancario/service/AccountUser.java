package qbank.sistemabancario.service;

import qbank.sistemabancario.context.conta.*;
import qbank.sistemabancario.context.pessoa.Pessoa;
import qbank.sistemabancario.strategy.transacao.TransacaoFactory;
import qbank.sistemabancario.strategy.transacao.TransacaoStrategy;
import qbank.sistemabancario.utils.JsonUtils;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;

public class AccountUser {
    public static void criarConta(Scanner scanner) {
        Pessoa pessoa;
        while (true) {
            pessoa = PessoaService.obterPessoa(scanner);
            if (PessoaService.verificarDuplicidadeUsuario(pessoa.getNome(), pessoa.getCpf(), pessoa.getRg())) {
                System.out.println("Usuário já registrado com o nome, CPF ou RG informados. Tente novamente.");
            } else {
                break;
            }
        }

        Conta conta = criarConta(scanner, pessoa);
        if (conta == null) return;

        JsonUtils.salvarContaNoJson(conta);
        System.out.println("Conta criada com sucesso! Saldo inicial: R$" + conta.getSaldo());
    }

    public static Conta criarConta(Scanner scanner, Pessoa pessoa) {
        System.out.println("Escolha o tipo de conta a ser criada:");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        System.out.println("3 - Conta Salário");

        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        String agencia = gerarAgencia();
        String numeroConta = gerarNumeroConta();

        return switch (tipoConta) {
            case 1 -> new ContaCorrente(agencia, numeroConta, 3000f, pessoa);
            case 2 -> new ContaPoupanca(agencia, numeroConta, 1000f, pessoa);
            case 3 -> new ContaSalario(agencia, numeroConta, 2000f, pessoa);
            default -> {
                System.out.println("Tipo de conta inválido.");
                yield null;
            }
        };
    }

    private static String gerarAgencia() {
        Random random = new Random();
        return String.format("%03d", random.nextInt(100));
    }

    private static String gerarNumeroConta() {
        Random random = new Random();
        return String.format("%05d", random.nextInt(10000));
    }

    public static void entrarNaConta(Scanner scanner) {
        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.nextLine();
        Conta conta = JsonUtils.buscarContaPorNumero(numeroConta);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        System.out.println("Olá, " + conta.getPessoa().getNome() + "! Bem-vindo(a) à sua conta.");
        System.out.println("Tipo de conta: " + conta.getTipoConta());
        System.out.println("Saldo disponível: R$" + conta.getSaldo());
        System.out.println("O que deseja fazer?");
        System.out.println("1 - Transferência");
        System.out.println("2 - Sair");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha == 1) {
            realizarTransacao(scanner, conta);
        } else {
            System.out.println("Saindo da conta...");
        }
    }

    private static void realizarTransacao(Scanner scanner, Conta conta) {
        System.out.println("Escolha o tipo de transação:");
        System.out.println("1 - Pix\n2 - TED");

        int tipoTransacao = scanner.nextInt();
        System.out.print("Valor da transação: ");
        BigDecimal valor = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.print("Informe o número da conta de destino: ");
        String numeroContaDestino = scanner.nextLine();
        Conta contaDestino = JsonUtils.buscarContaPorNumero(numeroContaDestino);

        TransacaoStrategy estrategia = TransacaoFactory.criarTransacao(tipoTransacao);

        if (contaDestino != null) {
            estrategia.realizarTransacao(valor, conta, contaDestino);
            JsonUtils.salvarContaNoJson(conta);
            JsonUtils.salvarContaNoJson(contaDestino);
        }
    }
}

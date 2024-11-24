package qbank.sistemabancario.service;

import qbank.sistemabancario.context.conta.*;
import qbank.sistemabancario.context.pessoa.Pessoa;
import qbank.sistemabancario.strategy.transacao.TransacaoFactory;
import qbank.sistemabancario.strategy.transacao.TransacaoStrategy;
import kong.unirest.Unirest;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;

public class AccountUser {
    public static void verificarDuplicidade(Scanner scanner) {
        while (true) {
            Pessoa pessoa = PessoaService.obterPessoa(scanner);

            try {
                boolean duplicado = Unirest.get("http://localhost:8080/api/pessoas/duplicada")
                        .queryString("nome", pessoa.getNome())
                        .queryString("cpf", pessoa.getCpf())
                        .queryString("rg", pessoa.getRg())
                        .asObject(Boolean.class)
                        .getBody();

                if (duplicado) {
                    System.out.println("Usuário já registrado com o nome, CPF ou RG informados. Tente novamente.");
                } else {
                    Conta conta = verificarDuplicidade(scanner, pessoa);
                    if (conta == null) return;

                    Unirest.post("http://localhost:8080/api/contas")
                            .header("Content-Type", "application/json")
                            .body(conta)
                            .asEmpty();

                    System.out.println("Conta criada com sucesso! Saldo inicial: R$" + conta.getSaldo());
                    return;
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar requisição: " + e.getMessage());
                return;
            }
        }
    }

//    Vai precisar de um EndPoint de verificação

//    @GetMapping("/api/pessoas/duplicada")
//    public boolean verificarDuplicidade(
//            @RequestParam String nome,
//            @RequestParam String cpf,
//            @RequestParam String rg) {
//        return pessoaService.verificarDuplicidade(nome, cpf, rg);
//    }


    public static Conta verificarDuplicidade(Scanner scanner, Pessoa pessoa) {
        System.out.println("Escolha o tipo de conta a ser criada:");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        System.out.println("3 - Conta Salário");

        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        String agencia = gerarAgencia();
        String numeroConta = gerarNumeroConta();
        Conta novaConta = switch (tipoConta) {
            case 1 -> new ContaCorrente(agencia, numeroConta, 3000f, pessoa);
            case 2 -> new ContaPoupanca(agencia, numeroConta, 1000f, pessoa);
            case 3 -> new ContaSalario(agencia, numeroConta, 2000f, pessoa);
            default -> {
                System.out.println("Tipo de conta inválido.");
                yield null;
            }
        };

        if (novaConta == null) return null;

        try {
            String resposta = Unirest.post("http://localhost:8080/api/contas")
                    .header("Content-Type", "application/json")
                    .body(novaConta)
                    .asString()
                    .getBody();

            System.out.println("Resposta da API: " + resposta);
            System.out.println("Conta criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar conta na API: " + e.getMessage());
        }
        return novaConta;
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

        try {
            Conta conta = Unirest.get("http://localhost:8080/transacoes/conta/{numeroConta}")
                    .routeParam("numeroConta", numeroConta)
                    .asObject(Conta.class)
                    .getBody();

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
        } catch (Exception e) {
            System.out.println("Erro ao acessar a conta: " + e.getMessage());
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

        try {
            Conta contaDestino = Unirest.get("http://localhost:8080/api/contas/{numeroConta}")
                    .routeParam("numeroConta", numeroContaDestino)
                    .asObject(Conta.class)
                    .getBody();

            if (contaDestino == null) {
                System.out.println("Conta de destino não encontrada.");
                return;
            }

            TransacaoStrategy estrategia = TransacaoFactory.criarTransacao(tipoTransacao);

            estrategia.realizarTransacao(valor, conta, contaDestino);

            Unirest.put("http://localhost:8080/api/contas/{numeroConta}")
                    .routeParam("numeroConta", conta.getNumero())
                    .header("Content-Type", "application/json")
                    .body(conta)
                    .asEmpty();

            Unirest.put("http://localhost:8080/api/contas/{numeroConta}")
                    .routeParam("numeroConta", contaDestino.getNumero())
                    .header("Content-Type", "application/json")
                    .body(contaDestino)
                    .asEmpty();

            System.out.println("Transação realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao realizar a transação: " + e.getMessage());
        }
    }
}

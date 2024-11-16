package qbank.sistemabancario;

import com.google.gson.*;
import qbank.sistemabancario.context.pessoa.Pessoa;
import qbank.sistemabancario.context.conta.Conta;
import qbank.sistemabancario.context.conta.ContaCorrente;
import qbank.sistemabancario.context.conta.ContaPoupanca;
import qbank.sistemabancario.context.conta.ContaSalario;
import qbank.sistemabancario.strategy.transacao.Pix;
import qbank.sistemabancario.strategy.transacao.TED;
import qbank.sistemabancario.strategy.transacao.TransacaoStrategy;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Application {

    private static final String ACCOUNT_FILE_PATH = "src/main/java/qbank/sistemabancario/account/account.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pessoa pessoa = obterPessoa(scanner);
        Conta conta = criarConta(scanner, pessoa);

        if (conta == null) return;

        salvarContaNoJson(conta);
        System.out.println("Conta criada com sucesso! Saldo inicial: " + conta.getSaldo());

        if (!desejaRealizarTransferencia(scanner)) {
            System.out.println("Obrigado por usar o sistema bancário!");
            return;
        }

        realizarTransacao(scanner, conta);
    }

    private static Pessoa obterPessoa(Scanner scanner) {
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

    private static Conta criarConta(Scanner scanner, Pessoa pessoa) {
        String agencia = gerarAgencia();
        String numeroConta = gerarNumeroConta();

        System.out.println("Selecione o tipo de conta:");
        System.out.println("1 - Conta Corrente\n2 - Conta Poupança\n3 - Conta Salário");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        return switch (opcao) {
            case 1 -> new ContaCorrente(agencia, numeroConta, 4000f, pessoa);
            case 2 -> new ContaPoupanca(agencia, numeroConta, 5000f, pessoa);
            case 3 -> new ContaSalario(agencia, numeroConta, 3000f, pessoa);
            default -> {
                System.out.println("Opção inválida!");
                yield null;
            }
        };
    }

    private static String gerarAgencia() {
        return "" + (int) (Math.random() * 1000);
    }

    private static String gerarNumeroConta() {
        return "" + (int) (Math.random() * 1000000);
    }

    private static void salvarContaNoJson(Conta conta) {
        JsonObject jsonObject = GSON.toJsonTree(conta).getAsJsonObject();
        JsonArray contasArray = lerContasJson();

        contasArray.add(jsonObject);

        JsonObject newJson = new JsonObject();
        newJson.add("contas", contasArray);

        escreverNoJson(newJson);
    }

    private static JsonArray lerContasJson() {
        JsonArray contasArray = new JsonArray();
        try (FileReader fileReader = new FileReader(ACCOUNT_FILE_PATH)) {
            JsonObject existingJson = GSON.fromJson(fileReader, JsonObject.class);
            if (existingJson != null && existingJson.has("contas")) {
                contasArray = existingJson.getAsJsonArray("contas");
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return contasArray;
    }

    private static void escreverNoJson(JsonObject newJson) {
        try (FileWriter fileWriter = new FileWriter(ACCOUNT_FILE_PATH)) {
            GSON.toJson(newJson, fileWriter);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    private static boolean desejaRealizarTransferencia(Scanner scanner) {
        System.out.print("Deseja realizar transferências com essa conta? (Sim/Não): ");
        String resposta = scanner.nextLine();
        return resposta.equalsIgnoreCase("Sim");
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
        Conta contaDestino = buscarContaPorNumero(numeroContaDestino);

        TransacaoStrategy estrategia = switch (tipoTransacao) {
            case 1 -> new Pix();
            case 2 -> new TED();
            default -> {
                System.out.println("Opção inválida para transação!");
                yield null;
            }
        };

        if (estrategia != null && contaDestino != null) {
            estrategia.realizarTransacao(valor, conta, contaDestino);
            atualizarSaldoNoJson(conta, contaDestino);
            System.out.println("Transação realizada com sucesso!");
        } else {
            System.out.println(contaDestino == null ? "Conta de destino não encontrada." : "Erro na transação.");
        }
    }

    private static Conta buscarContaPorNumero(String numeroConta) {
        try (FileReader reader = new FileReader(ACCOUNT_FILE_PATH)) {
            JsonObject jsonObject = GSON.fromJson(reader, JsonObject.class);
            JsonArray contasArray = jsonObject.getAsJsonArray("contas");

            for (JsonElement elemento : contasArray) {
                JsonObject contaJson = elemento.getAsJsonObject();
                if (contaJson.get("numero").getAsString().equals(numeroConta)) {
                    return criarContaDeJson(contaJson);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao buscar conta: " + e.getMessage());
        }
        return null;
    }

    private static Conta criarContaDeJson(JsonObject contaJson) {
        Pessoa pessoa = GSON.fromJson(contaJson.get("pessoa"), Pessoa.class);
        String tipoConta = contaJson.has("tipoConta") ? contaJson.get("tipoConta").getAsString() : "Corrente";
        float saldo = contaJson.get("saldo").getAsFloat();

        return switch (tipoConta) {
            case "Poupança" -> new ContaPoupanca(contaJson.get("agencia").getAsString(), contaJson.get("numero").getAsString(), saldo, pessoa);
            case "Salário" -> new ContaSalario(contaJson.get("agencia").getAsString(), contaJson.get("numero").getAsString(), saldo, pessoa);
            default -> new ContaCorrente(contaJson.get("agencia").getAsString(), contaJson.get("numero").getAsString(), saldo, pessoa);
        };
    }

    private static void atualizarSaldoNoJson(Conta contaOrigem, Conta contaDestino) {
        JsonObject jsonObject = lerJsonContas();

        for (JsonElement elemento : jsonObject.getAsJsonArray("contas")) {
            JsonObject contaJson = elemento.getAsJsonObject();
            if (contaOrigem.getNumero().equals(contaJson.get("numero").getAsString())) {
                contaJson.addProperty("saldo", contaOrigem.getSaldo());
            } else if (contaDestino != null && contaDestino.getNumero().equals(contaJson.get("numero").getAsString())) {
                contaJson.addProperty("saldo", contaDestino.getSaldo());
            }
        }

        escreverNoJson(jsonObject);
    }

    private static JsonObject lerJsonContas() {
        try (FileReader reader = new FileReader(ACCOUNT_FILE_PATH)) {
            return GSON.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return new JsonObject();
        }
    }
}

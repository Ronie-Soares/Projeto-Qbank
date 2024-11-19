package qbank.sistemabancario.dto;

import com.google.gson.*;
import qbank.sistemabancario.context.conta.Conta;
import qbank.sistemabancario.context.conta.ContaCorrente;
import qbank.sistemabancario.context.conta.ContaPoupanca;
import qbank.sistemabancario.context.conta.ContaSalario;
import qbank.sistemabancario.context.pessoa.Pessoa;

import java.io.FileReader;
import java.io.IOException;

public class ContaService {

    private static final String ACCOUNT_FILE_PATH = "src/main/java/qbank/sistemabancario/account/account.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static Conta buscarContaPorNumero(String numeroConta) {
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
}

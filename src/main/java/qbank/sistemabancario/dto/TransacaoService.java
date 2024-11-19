package qbank.sistemabancario.dto;

import com.google.gson.*;
import qbank.sistemabancario.context.conta.ContaCorrente;
import qbank.sistemabancario.context.conta.ContaPoupanca;
import qbank.sistemabancario.context.conta.ContaSalario;
import qbank.sistemabancario.context.pessoa.Pessoa;
import qbank.sistemabancario.strategy.transacao.TED;
import qbank.sistemabancario.strategy.transacao.Transacao;

import java.io.FileReader;
import java.io.IOException;

public class TransacaoService {

    private static final String ACCOUNT_FILE_PATH = "src/main/java/qbank/sistemabancario/account/account.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static Transacao buscarTransacoesPorNumero(String numeroTransacao) {
        try (FileReader reader = new FileReader(ACCOUNT_FILE_PATH)) {
            JsonObject jsonObject = GSON.fromJson(reader, JsonObject.class);
            JsonArray contasArray = jsonObject.getAsJsonArray("transacoes");

            for (JsonElement elemento : contasArray) {
                JsonObject transacaoJson = elemento.getAsJsonObject();
                if (transacaoJson.get("id").getAsString().equals(numeroTransacao)) {
                    System.out.println(transacaoJson.get("id").getAsString().equals(numeroTransacao));
                    //return criarTransacaoDeJson(transacaoJson);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao buscar transacao: " + e.getMessage());
        }
        return null;
    }

//    private static Transacao criarTransacaoDeJson(JsonObject transacaoJson) {
//        Pessoa pessoa = GSON.fromJson(transacaoJson.get("transacoes"), Pessoa.class);
//
//        String tipoTransacao = transacaoJson.has("tipo") ? transacaoJson.get("tipo").getAsString() : "TED";
//
//        float valor = transacaoJson.get("valor").getAsFloat();
//        // AQUI TA CAGADO.
////
////        TED ted = new TED(valor,transacaoJson.get("contaOrigem"),transacaoJson.get("contaDestino"));
////        return switch (tipoTransacao) {
////            //case "TED" -> new Transacao(transacaoJson.get("contaOrigem"),transacaoJson.get("contaDestino"),valor,ted);
////            //case "PIX" -> new Transacao(transacaoJson.get("contaOrigem"),transacaoJson.get("contaDestino",valor,"",PIX);
////            //default -> new ContaCorrente(transacaoJson.get("agencia").getAsString(), contaJson.get("numero").getAsString(), saldo, pessoa);
////        };
//    }
}

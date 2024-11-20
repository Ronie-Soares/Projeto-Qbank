package qbank.sistemabancario.utils;

import com.google.gson.*;
import qbank.sistemabancario.context.conta.Conta;
import qbank.sistemabancario.service.ContaTypeAdapter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonUtils {
    private static final String ACCOUNT_FILE_PATH = "src/main/java/qbank/sistemabancario/account/account.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Conta.class, new ContaTypeAdapter()).create();

    public static JsonArray lerContasJson() {
        try (FileReader fileReader = new FileReader(ACCOUNT_FILE_PATH)) {
            JsonObject existingJson = GSON.fromJson(fileReader, JsonObject.class);
            return existingJson != null && existingJson.has("contas") ? existingJson.getAsJsonArray("contas") : new JsonArray();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new JsonArray();
        }
    }

    public static void salvarContaNoJson(Conta conta) {
        JsonArray contasArray = lerContasJson();
        boolean contaAtualizada = false;

        for (JsonElement elemento : contasArray) {
            JsonObject contaJson = elemento.getAsJsonObject();
            if (contaJson.get("numero").getAsString().equals(conta.getNumero())) {
                contaJson.addProperty("saldo", conta.getSaldo());
                contaAtualizada = true;
                break;
            }
        }

        if (!contaAtualizada) {
            contasArray.add(GSON.toJsonTree(conta).getAsJsonObject());
        }

        JsonObject newJson = new JsonObject();
        newJson.add("contas", contasArray);
        escreverNoJson(newJson);
    }

    public static Conta buscarContaPorNumero(String numeroConta) {
        try (FileReader reader = new FileReader(ACCOUNT_FILE_PATH)) {
            JsonObject jsonObject = GSON.fromJson(reader, JsonObject.class);
            for (JsonElement elemento : jsonObject.getAsJsonArray("contas")) {
                JsonObject contaJson = elemento.getAsJsonObject();
                if (contaJson.get("numero").getAsString().equals(numeroConta)) {
                    return GSON.fromJson(contaJson, Conta.class);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private static void escreverNoJson(JsonObject jsonObject) {
        try (FileWriter fileWriter = new FileWriter(ACCOUNT_FILE_PATH)) {
            GSON.toJson(jsonObject, fileWriter);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

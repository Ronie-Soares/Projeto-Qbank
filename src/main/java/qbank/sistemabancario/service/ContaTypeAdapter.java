package qbank.sistemabancario.service;

import com.google.gson.*;
import qbank.sistemabancario.context.conta.*;
import qbank.sistemabancario.context.pessoa.Pessoa;

import java.lang.reflect.Type;

public class ContaTypeAdapter implements JsonDeserializer<Conta> {
    @Override
    public Conta deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String tipoConta = jsonObject.get("tipoConta").getAsString();
        String agencia = jsonObject.get("agencia").getAsString();
        String numero = jsonObject.get("numero").getAsString();
        float saldo = jsonObject.get("saldo").getAsFloat();
        Pessoa pessoa = context.deserialize(jsonObject.get("pessoa"), Pessoa.class);

        return switch (tipoConta) {
            case "Poupança" -> new ContaPoupanca(agencia, numero, saldo, pessoa);
            case "Salário" -> new ContaSalario(agencia, numero, saldo, pessoa);
            default -> new ContaCorrente(agencia, numero, saldo, pessoa);
        };
    }
}

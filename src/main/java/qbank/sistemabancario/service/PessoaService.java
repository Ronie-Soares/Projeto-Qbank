package qbank.sistemabancario.service;

import qbank.sistemabancario.context.pessoa.Pessoa;
import qbank.sistemabancario.utils.JsonUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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

    public static boolean verificarDuplicidadeUsuario(String nome, String cpf, String rg) {
        JsonArray contasArray = JsonUtils.lerContasJson();

        for (JsonElement elemento : contasArray) {
            JsonObject contaJson = elemento.getAsJsonObject();
            JsonObject pessoaJson = contaJson.getAsJsonObject("pessoa");

            String nomeExistente = pessoaJson.get("nome").getAsString();
            String cpfExistente = pessoaJson.get("cpf").getAsString();
            String rgExistente = pessoaJson.get("rg").getAsString();

            if (nomeExistente.equalsIgnoreCase(nome) || cpfExistente.equals(cpf) || rgExistente.equals(rg)) {
                return true;
            }
        }
        return false;
    }
}

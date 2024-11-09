package qbank.sistemabancario;

import io.micronaut.runtime.Micronaut;
import qbank.sistemabancario.context.conta.Conta;
import qbank.sistemabancario.context.conta.ContaCorrente;
import qbank.sistemabancario.context.pessoa.Pessoa;

public class Application {

    public static void main(String[] args) {
        Pessoa pessoaTeste = new Pessoa("Ronie Soares","00100200355","12345678","ronie.profissional@gmail.com","31993143539");
        Conta conta1 = new ContaCorrente("0001","123456-7", 1000.00F,pessoaTeste);

        System.out.println("TESTE: "+ conta1.DadosConta());

        Float saldo = conta1.getSaldo();
        Micronaut.run(Application.class, args);
    }
}
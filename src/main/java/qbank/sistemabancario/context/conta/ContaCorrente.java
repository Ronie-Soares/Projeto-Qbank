package qbank.sistemabancario.context.conta;

import qbank.sistemabancario.context.pessoa.Pessoa;

public class ContaCorrente extends Conta {

    public ContaCorrente(String agencia, String numero, Float saldo, Pessoa pessoa) {
        super(agencia, numero, saldo, pessoa);
    }

    @Override
    public Float getSaldo() {
        System.out.println("Saldo da sua conta Corrente: ");
        return super.getSaldo();
    }

}

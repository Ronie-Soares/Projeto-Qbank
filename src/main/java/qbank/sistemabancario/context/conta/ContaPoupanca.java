package qbank.sistemabancario.context.conta;

import qbank.sistemabancario.context.pessoa.Pessoa;

public class ContaPoupanca extends Conta{

    public ContaPoupanca(String agencia, String numero, Float saldo, Pessoa pessoa) {
        super(agencia, numero, saldo, pessoa);
    }

    @Override
    public Float getSaldo() {
        System.out.println("Saldo da sua conta Poupança: ");
        return super.getSaldo();
    }

}

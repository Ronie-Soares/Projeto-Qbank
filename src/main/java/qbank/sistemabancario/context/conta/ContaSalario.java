package qbank.sistemabancario.context.conta;

import qbank.sistemabancario.context.pessoa.Pessoa;

public class ContaSalario extends Conta {
    public ContaSalario(String agencia, String numero, Float saldo, Pessoa pessoa) {
        super(agencia, numero, saldo, pessoa);
    }

    @Override
    public Float getSaldo() {
        System.out.println("Saldo da sua conta Salario: ");
        return super.getSaldo();
    }
}

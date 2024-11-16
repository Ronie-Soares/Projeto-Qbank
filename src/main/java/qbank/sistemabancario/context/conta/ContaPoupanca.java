package qbank.sistemabancario.context.conta;

import qbank.sistemabancario.context.pessoa.Pessoa;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(String agencia, String numero, Float saldo, Pessoa pessoa) {
        super(agencia, numero, BigDecimal.valueOf(saldo), pessoa, "Poupança");
    }

    @Override
    public BigDecimal getSaldo() {
        return super.getSaldo();
    }
}

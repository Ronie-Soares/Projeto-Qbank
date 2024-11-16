package qbank.sistemabancario.context.conta;

import qbank.sistemabancario.context.pessoa.Pessoa;

import java.math.BigDecimal;

public class ContaPoupança extends Conta {
    public ContaPoupança(String agencia, String numero, Float saldo, Pessoa pessoa) {
        super(agencia, numero, BigDecimal.valueOf(saldo), pessoa, "Poupança");
    }

    @Override
    public BigDecimal getSaldo() {
        return super.getSaldo();
    }
}

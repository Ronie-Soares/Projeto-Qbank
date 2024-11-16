package qbank.sistemabancario.context.conta;

import qbank.sistemabancario.context.pessoa.Pessoa;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {
    public ContaCorrente(String agencia, String numero, Float saldo, Pessoa pessoa) {
        super(agencia, numero, BigDecimal.valueOf(saldo), pessoa, "Corrente");
    }

    @Override
    public BigDecimal getSaldo() {
        return super.getSaldo();
    }
}

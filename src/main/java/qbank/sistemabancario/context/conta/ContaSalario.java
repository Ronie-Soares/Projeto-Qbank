package qbank.sistemabancario.context.conta;

import qbank.sistemabancario.context.pessoa.Pessoa;

import java.math.BigDecimal;

public class ContaSalario extends Conta {
    public ContaSalario(String agencia, String numero, Float saldo, Pessoa pessoa) {
        super(agencia, numero, BigDecimal.valueOf(saldo), pessoa, "Salário");
    }

    @Override
    public BigDecimal getSaldo() {
        return super.getSaldo();
    }
}

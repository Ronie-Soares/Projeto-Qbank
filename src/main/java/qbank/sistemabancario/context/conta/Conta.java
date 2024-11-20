package qbank.sistemabancario.context.conta;

import qbank.sistemabancario.context.pessoa.Pessoa;
import qbank.sistemabancario.strategy.transacao.Transacao;

import java.math.BigDecimal;
import java.util.List;

public abstract class Conta {
    private String agencia;
    private String numero;
    private BigDecimal saldo;
    private Pessoa pessoa;
    private String tipoConta;

    public Conta(String agencia, String numero, BigDecimal saldo, Pessoa pessoa, String tipoConta) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.pessoa = pessoa;
        this.tipoConta = tipoConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}


package qbank.sistemabancario.context.conta;

import qbank.sistemabancario.context.pessoa.Pessoa;
import qbank.sistemabancario.strategy.transacao.Transacao;

public abstract class Conta {

    private Transacao transacao;
    private String agencia;
    private String numero;
    private Float saldo;
    private Pessoa pessoa;

    public Conta(String agencia, String numero, Float saldo, Pessoa pessoa) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.pessoa = pessoa;
    }

    public String getAgencia() {
        return agencia;
    }
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Float getSaldo() {
        System.out.println(saldo);
        return saldo;
    }
    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String DadosConta() {
        // Verificação simples para evitar NullPointerException
        String nome = (pessoa != null) ? pessoa.getNome() : "N/A";
        String mensagem = String.format("AG: %s \nCONTA: %s \nNOME: %s \nSALDO: %.2f",
                agencia, numero, nome, saldo);
        return mensagem;
    }

    public boolean contaCriada(){
        return true;
    }
}

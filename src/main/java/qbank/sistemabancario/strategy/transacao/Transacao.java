package qbank.sistemabancario.strategy.transacao;

import qbank.sistemabancario.context.conta.Conta;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transacao {
    private String id;
    private Conta contaOrigem;
    private Conta contaDestino;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private TipoTransacao tipo;

    public enum TipoTransacao {
        PIX, TED;
    }

    public Transacao(Conta contaOrigem, Conta contaDestino, BigDecimal valor, TipoTransacao tipo) {
        this.id = UUID.randomUUID().toString();
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id='" + id + '\'' +
                ", contaOrigem=" + contaOrigem.getNumero() +
                ", contaDestino=" + contaDestino.getNumero() +
                ", valor=" + valor +
                ", dataHora=" + dataHora +
                ", tipo=" + tipo +
                '}';
    }
}

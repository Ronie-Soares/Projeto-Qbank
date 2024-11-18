package qbank.sistemabancario.strategy.transacao;

import qbank.sistemabancario.context.conta.Conta;
import java.math.BigDecimal;

public interface TransacaoStrategy {
    void realizarTransacao(BigDecimal valor, Conta contaOrigem, Conta contaDestino);
}

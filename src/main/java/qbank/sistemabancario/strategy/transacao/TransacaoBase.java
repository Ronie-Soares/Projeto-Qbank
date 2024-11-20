package qbank.sistemabancario.strategy.transacao;

import qbank.sistemabancario.context.conta.Conta;
import java.math.BigDecimal;

public abstract class TransacaoBase implements TransacaoStrategy {

    protected abstract Transacao.TipoTransacao getTipoTransacao();

    @Override
    public void realizarTransacao(BigDecimal valor, Conta contaOrigem, Conta contaDestino) {
        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            System.out.println("Saldo insuficiente para transação tipo: " + getTipoTransacao());
            return;
        }

        Transacao transacao = new Transacao(contaOrigem, contaDestino, valor, getTipoTransacao());
        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));

        System.out.printf("Transferência tipo %s realizada com sucesso!%nTransação: %s%nNovo saldo de %s: %s%nNovo saldo de %s: %s%n",
                getTipoTransacao(), transacao, contaOrigem.getPessoa().getNome(), contaOrigem.getSaldo(),
                contaDestino.getPessoa().getNome(), contaDestino.getSaldo());
    }
}

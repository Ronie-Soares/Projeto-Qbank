package qbank.sistemabancario.strategy.transacao;

import qbank.sistemabancario.context.conta.Conta;
import java.math.BigDecimal;

public class Pix implements TransacaoStrategy {

    @Override
    public void realizarTransacao(BigDecimal valor, Conta contaOrigem, Conta contaDestino) {
        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            System.out.println("Saldo insuficiente para realizar o Pix!");
            return;
        }

        Transacao transacao = new Transacao(contaOrigem, contaDestino, valor, Transacao.TipoTransacao.PIX);

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));

        System.out.println("Transferência via Pix realizada com sucesso!");
        System.out.println("Transação: " + transacao);
        System.out.println("Novo saldo da conta de " + contaOrigem.getPessoa().getNome() + ": " + contaOrigem.getSaldo());
        System.out.println("Novo saldo da conta de " + contaDestino.getPessoa().getNome() + ": " + contaDestino.getSaldo());
    }
}

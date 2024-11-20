package qbank.sistemabancario.strategy.transacao;

public class TransacaoFactory {
    public static TransacaoStrategy criarTransacao(int tipoTransacao) {
        if (tipoTransacao == 1) return new Pix();
        if (tipoTransacao == 2) return new TED();
        throw new IllegalArgumentException("Tipo de transação inválido");
    }
}

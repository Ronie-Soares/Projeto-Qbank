package qbank.sistemabancario.strategy.transacao;

public class TED extends TransacaoBase {

    @Override
    protected Transacao.TipoTransacao getTipoTransacao() {
        return Transacao.TipoTransacao.TED;
    }
}

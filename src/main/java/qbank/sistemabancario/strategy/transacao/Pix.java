package qbank.sistemabancario.strategy.transacao;

public class Pix extends TransacaoBase {

    @Override
    protected Transacao.TipoTransacao getTipoTransacao() {
        return Transacao.TipoTransacao.PIX;
    }
}

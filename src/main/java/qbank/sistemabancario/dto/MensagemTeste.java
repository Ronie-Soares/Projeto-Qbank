package qbank.sistemabancario.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class MensagemTeste {

    private String MensagemTeste;

    public MensagemTeste(String MensagemTeste) {
        this.MensagemTeste = MensagemTeste;
    }

    public String getMensagem() {
        return MensagemTeste;
    }

    public void setMensagem(String MensagemTeste) {
        this.MensagemTeste = MensagemTeste;
    }
}

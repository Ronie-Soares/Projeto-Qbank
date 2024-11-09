package qbank.sistemabancario.strategy.transacao;

import qbank.sistemabancario.context.conta.Conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {
    private String id;
    private Conta contaOrigem;
    private String Destino; 
    private BigDecimal valor;
    private LocalDateTime dataHora;
    //private TipoTransacao tipo;
}

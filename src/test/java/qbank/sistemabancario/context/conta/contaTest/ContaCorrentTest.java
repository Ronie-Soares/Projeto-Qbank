package qbank.sistemabancario.context.conta.contaTest;

import org.junit.jupiter.api.Test;
import qbank.sistemabancario.context.conta.ContaCorrente;
import qbank.sistemabancario.context.pessoa.Pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContaCorrentTest {

    Pessoa pessoatest = new Pessoa("Rafael","00100100100","000111000","rafael@gmail","31123456789");
    ContaCorrente conta = new ContaCorrente("001","123",50000F,pessoatest);

    @Test
    void RetornaSaldoDaContaCorrente() {
        float saldoConta = conta.getSaldo();

        assertEquals(saldoConta,50000F);
    }
    @Test
    void RetornaAgenciDaContaCorrente() {
        assertEquals(conta.getAgencia(),"001");
    }
    @Test
    void RetornaNumeroContaCorrente() {
        assertEquals(conta.getNumero(),"123");
    }
    @Test
    void RetornaDadosPessoais() { assertEquals(conta.getPessoa(),pessoatest); }
    @Test
    void AlterandoSaldoContaCorrente() {
        conta.setSaldo(100.50F);
        assertEquals(conta.getSaldo(),100.50F);
    }
    @Test
    void AlterandoAgenciaContaCorrente() {
        conta.setAgencia("003");
        assertEquals(conta.getAgencia(),"003");
    }
    @Test
    void AlterandoNumeroContaCorrente() {
        conta.setNumero("456");
        assertEquals(conta.getNumero(),"456");
    }
}

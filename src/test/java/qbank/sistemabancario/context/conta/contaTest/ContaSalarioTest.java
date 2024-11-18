package qbank.sistemabancario.context.conta.contaTest;

import org.junit.jupiter.api.Test;
import qbank.sistemabancario.context.conta.ContaSalario;
import qbank.sistemabancario.context.pessoa.Pessoa;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContaSalarioTest {
    Pessoa pessoatest = new Pessoa("Rafael","00100100100","000111000","rafael@gmail","31123456789");
    ContaSalario conta = new ContaSalario("001","123",50000F,pessoatest);

    @Test
    void RetornaSaldoDaContaSalario() {
        BigDecimal saldoConta = conta.getSaldo();
        assertEquals(saldoConta,BigDecimal.valueOf(50000F));
    }
    @Test
    void RetornaAgenciaDaContaSalario() {
        assertEquals(conta.getAgencia(),"001");
    }
    @Test
    void RetornaDadosPessoais() {
        assertEquals(conta.getPessoa(), pessoatest);
    }
    @Test
    void AlterandoSaldoDaContaSalario() {
        conta.setSaldo(BigDecimal.valueOf(10.20F));
        assertEquals(conta.getSaldo(),BigDecimal.valueOf(10.20F));
    }
    @Test
    void AlterandoAgenciaDaContaSalario() {
        conta.setAgencia("002");
        assertEquals(conta.getAgencia(),"002");
    }
    @Test
    void AlterandoNumeroDaContaSalario() {
        conta.setNumero("003");
        assertEquals(conta.getNumero(),"003");
    }
}

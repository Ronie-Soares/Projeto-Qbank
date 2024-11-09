package qbank.sistemabancario.context.conta.contaTest;

import org.junit.jupiter.api.Test;
import qbank.sistemabancario.context.conta.ContaPoupanca;
import qbank.sistemabancario.context.pessoa.Pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContaPoupancaTest  {

    Pessoa pessoatest = new Pessoa("Rafael","00100100100","000111000","rafael@gmail","31123456789");
    ContaPoupanca conta = new ContaPoupanca("001","123",50000F,pessoatest);
    @Test
    void RetornaSaldoDaContaPoupanca() {
        float saldoConta = conta.getSaldo();
        assertEquals(saldoConta,50000F);
    }
    @Test
    void RetornaAgenciaDaContaPoupanca() {
        assertEquals(conta.getAgencia(),"001");
    }
    @Test
    void RetornaDadosPessoais(){
        assertEquals(conta.getPessoa(), pessoatest);
    }
    @Test
    void AlterandoSaldoDaContaPoupanca() {
        conta.setSaldo(200.30F);
        assertEquals(conta.getSaldo(),200.30F);
    }
    @Test
    void AlterandoAgenciaDaContaPoupanca() {
        conta.setAgencia("010");
        assertEquals(conta.getAgencia(),"010");
    }
    @Test
    void AlterandoNumeroDaContaPoupanca() {
        conta.setNumero("0111");
        assertEquals(conta.getNumero(),"0111");
    }
}

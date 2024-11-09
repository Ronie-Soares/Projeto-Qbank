package qbank.sistemabancario.context.conta.pessoaTest;

import org.junit.jupiter.api.Test;
import qbank.sistemabancario.context.pessoa.Pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PessoaTest {

    Pessoa pessoatest = new Pessoa("Rafael","00100100100","000111000","rafael@gmail","31123456789");

    @Test
    void RetornaNome() {
        assertEquals(pessoatest.getNome(), "Rafael");
    }
    @Test
    void RetornaCPF() {
        assertEquals(pessoatest.getCpf(), "00100100100");
    }
    @Test
    void RetornaRG() {
        assertEquals(pessoatest.getRg(), "000111000");
    }
    @Test
    void RetornaEmail() {
        assertEquals(pessoatest.getEmail(), "rafael@gmail");
    }
    @Test
    void RetornaTelefone() {
        assertEquals(pessoatest.getTelefone(), "31123456789");
    }
    @Test
    void AlterandoNome() {
        pessoatest.setNome("Ronie");
        assertEquals(pessoatest.getNome(), "Ronie");
    }
    @Test
    void AlterandoCPF() {
        pessoatest.setCpf("00123400789");
        assertEquals(pessoatest.getCpf(), "00123400789");
    }
    @Test
    void AlterandoRG() {
        pessoatest.setRg("001234007");
        assertEquals(pessoatest.getRg(), "001234007");
    }
    @Test
    void AlterandoEmail() {
        pessoatest.setEmail("ronie@gmail.com");
        assertEquals(pessoatest.getEmail(), "ronie@gmail.com");
    }
    @Test
    void AlterandoTelefone() {
        pessoatest.setTelefone("31999999999");
        assertEquals(pessoatest.getTelefone(), "31999999999");
    }
}

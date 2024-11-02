package qbank.sistemabancario.context.conta;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qbank.sistemabancario.context.pessoa.Pessoa;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ContaTest {

    @InjectMocks
    ContaCorrente conta;

    @Mock
    Pessoa pessoa;

    @Test
    void RetornaTrueAoCriarConta(){

        assertTrue(conta.contaCriada());
    }

}

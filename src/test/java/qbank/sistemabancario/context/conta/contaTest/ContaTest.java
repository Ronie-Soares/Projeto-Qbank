package qbank.sistemabancario.context.conta.contaTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qbank.sistemabancario.context.conta.ContaCorrente;
import qbank.sistemabancario.context.pessoa.Pessoa;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ContaTest {

    @InjectMocks
    ContaCorrente conta;

    @Mock
    Pessoa pessoa;

    @Mock
    ContaCorrente contaCorrente;


//    @Test
//    void RetornaTrueAoCriarConta(){
//        assertTrue(conta.contaCriada());
//    }

}

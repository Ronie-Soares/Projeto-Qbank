package qbank.sistemabancario.controllers;


import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import qbank.sistemabancario.context.conta.Conta;
import qbank.sistemabancario.context.conta.ContaCorrente;
import qbank.sistemabancario.context.pessoa.Pessoa;
import qbank.sistemabancario.dto.MensagemTeste;

@Controller("/teste")
public class ControllerTest {

    @Get(produces = MediaType.APPLICATION_JSON)
    public MensagemTeste index() {
        Pessoa pessoaTeste = new Pessoa("Ronie Soares","00100200355","12345678","ronie.profissional@gmail.com","31993143539");
        Conta continha = new ContaCorrente("0001","123456-7", 1000.00F,pessoaTeste);

        String mensagem = String.format("Olá %s, você possui um saldo de %.2f",continha.getPessoa().getNome(),continha.getSaldo());
        System.out.println(mensagem);
        MensagemTeste mensagem2 = new MensagemTeste(mensagem);
        return mensagem2;
    }


}


package qbank.sistemabancario.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import qbank.sistemabancario.context.conta.Conta;
import qbank.sistemabancario.dto.TransacaoService;
import qbank.sistemabancario.dto.ContaService;
import qbank.sistemabancario.strategy.transacao.Transacao;

@Controller("/transacoes")
public class TransacoesController {

    @Post(value = "/deposito/{numeroConta}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.TEXT_PLAIN)
    HttpResponse<String> realizarDeposito(@Body Transacao deposito,
                                          @PathVariable("numeroConta") String numeroConta) {
        TransacaoService transacaoService = new TransacaoService();

        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return HttpResponse.ok(String.format("Depósito de R$%.2f realizado com sucesso na conta %s.",
                deposito.getValor(),
                numeroConta));
    }

    @Get(value = "/{numeroTransacao}", consumes = MediaType.APPLICATION_JSON)
    HttpResponse<String> verificarTransacao(@PathVariable String numeroTransacao) {

        TransacaoService transacaoService = new TransacaoService();

        Transacao transacao = TransacaoService.buscarTransacoesPorNumero(numeroTransacao);


        ObjectMapper mapper = new ObjectMapper();
        String jsonResult;
        try {
            jsonResult = mapper.writeValueAsString(transacao);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.serverError("Erro ao converter transações em JSON");
        }

        return HttpResponse.ok(jsonResult);

    }








    //============== APENAS PARA TESTE ========

    @Get(value = "/conta/{numeroConta}", consumes = MediaType.APPLICATION_JSON)
    HttpResponse<String> verificarContaID(@PathVariable String numeroConta) {

        ContaService contaService = new ContaService();
        Conta conta = ContaService.buscarContaPorNumero(numeroConta);


        ObjectMapper mapper = new ObjectMapper();
        String jsonResult;
        try {
            jsonResult = mapper.writeValueAsString(conta);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.serverError("Erro ao converter transações em JSON");
        }

        return HttpResponse.ok(jsonResult);

    }
}

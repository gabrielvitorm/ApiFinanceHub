package br.com.financehub.ApiFinanceHub.controller;

import br.com.financehub.ApiFinanceHub.model.Transacao;
import br.com.financehub.ApiFinanceHub.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
@Tag(name = "Transações", description = "Endpoints para gerenciamento de transações")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar Transação", description = "Endpoint para criar uma transação")
    public void criarTransacao(@RequestBody Transacao transacao){
        transacaoService.criarTransacao(transacao);
    }
}

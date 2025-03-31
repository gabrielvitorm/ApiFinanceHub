package br.com.financehub.ApiFinanceHub.controller;

import br.com.financehub.ApiFinanceHub.model.Transacao;
import br.com.financehub.ApiFinanceHub.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar Transação", description = "Endpoint para listar todas as transações")
    public List<Transacao> listarTodasTransacoes(){
        return transacaoService.listarTodasTransacoes();
    }

    @GetMapping("/{idTransacao}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar Transação por Id", description = "Endpoint para listar as transações por Id")
    public Optional<Transacao> listarTransacaoPorId(@PathVariable Long idTransacao){
        return transacaoService.listarTransacaoPorId(idTransacao);
    }
}

package br.com.financehub.ApiFinanceHub.controller;

import br.com.financehub.ApiFinanceHub.enums.CategoriaTransacaoEnum;
import br.com.financehub.ApiFinanceHub.enums.TipoTransacaoEnum;
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
@RequestMapping("/api/transacoes")
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

    @GetMapping("/id/{idTransacao}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar Transação por Id", description = "Endpoint para listar as transações por Id")
    public Optional<Transacao> listarTransacaoPorId(@PathVariable Long idTransacao){
        return transacaoService.listarTransacaoPorId(idTransacao);
    }

    @GetMapping("/categoria/{tipoCategoria}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar Categorias", description = "Endpoint para listar por categorias")
    public Optional<Transacao> listarPorTipoCategoria(@PathVariable CategoriaTransacaoEnum tipoCategoria) {
        return transacaoService.listarPorTipoCategoria(tipoCategoria);
    }

    @GetMapping("/tipo/{tipoTransacao}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar Transações pelo tipo", description = "Endpoint para listar trasações pelo tipo")
    public List<Transacao> listarTransacaoPorTipo(@PathVariable TipoTransacaoEnum tipoTransacao) {
        return transacaoService.listarTransacaoPorTipo(tipoTransacao);
    }

    @DeleteMapping("/id/{idTransacao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar transação por Id", description = "Endpoint para deleta a transação pelo Id da transação")
    public void deletarTransacaoPorId(@PathVariable Long idTransacao) {
        transacaoService.deletarTransacaoPorId(idTransacao);
    }

    @PutMapping("/id/{idTransacao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Atualizar transação por Id", description = "Endpoint para atualiza todos os dados da Transação pelo Id da Transação")
    public void atualizarTransacaoPorId(@PathVariable Long idTransacao, @RequestBody Transacao transacao) {
        transacaoService.atualizarTransacaoPorId(idTransacao, transacao);
    }
}

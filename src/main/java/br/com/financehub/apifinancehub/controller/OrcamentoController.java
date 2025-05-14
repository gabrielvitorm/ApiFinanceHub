package br.com.financehub.apifinancehub.controller;

import br.com.financehub.apifinancehub.dto.OrcamentoDTO;
import br.com.financehub.apifinancehub.model.Orcamento;
import br.com.financehub.apifinancehub.service.OrcamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orcamentos")
@Tag(name = "Orçamento", description = "Endpoints para gerenciamento da Api de Orçamento")
public class OrcamentoController {

    @Autowired
    OrcamentoService orcamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar orçamento", description = "Endpoint para criar um Orçamento")
    public Orcamento criarOrcamento(@RequestBody OrcamentoDTO orcamentoDTO){
        Orcamento orcamento = new Orcamento();
        orcamento.setLimiteOrcamento(orcamentoDTO.getLimiteOrcamento());

        return orcamentoService.criarOrcamento(orcamento, orcamentoDTO.getIdUsuario(), orcamentoDTO.getMesReferencia());
    }

    @GetMapping("/usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista todos os orçamento", description = "Lista todos os orçamentos do usuário, podendo filtrar pelo mês também")
    public List<Orcamento> listarOrcamentosPorUsuario(@PathVariable Long idUsuario, @RequestParam(required = false)LocalDate mesReferencia){
        return orcamentoService.listarOrcamentosPorUsuario(idUsuario, mesReferencia);
    }

    @GetMapping("/{idOrcamento}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista Orçamento por Id", description = "Lista os orçamento pelo Id so orçamento")
    public Optional<Orcamento> listarOrcamentoPorId(@PathVariable Long idOrcamento){
        return orcamentoService.listarOrcamentoPorId(idOrcamento);
    }

    @DeleteMapping("/{idOrcamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar Orçamento por Id", description = "Faz a deletação do orçamento pelo Id do Orçamento")
    public void deletarOrcamentoPorId(@PathVariable Long idOrcamento){
        orcamentoService.deletarOrcamentoPorId(idOrcamento);
    }

    @PutMapping("/atualizar/{idOrcamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Atualizar Orçamento", description = "Atualizar o limite definido e o mês de referência")
    public void atualizarOrcamentoPorId(@RequestBody Orcamento orcamento, @PathVariable Long idOrcamento){
        orcamentoService.atualizarOrcamentoPorId(orcamento, idOrcamento);
    }
}

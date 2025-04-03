package br.com.financehub.ApiFinanceHub.controller;

import br.com.financehub.ApiFinanceHub.dto.OrcamentoDTO;
import br.com.financehub.ApiFinanceHub.model.Orcamento;
import br.com.financehub.ApiFinanceHub.service.OrcamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}

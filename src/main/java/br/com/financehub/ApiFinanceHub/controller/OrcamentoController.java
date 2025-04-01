package br.com.financehub.ApiFinanceHub.controller;

import br.com.financehub.ApiFinanceHub.enums.CategoriaTransacaoEnum;
import br.com.financehub.ApiFinanceHub.model.Transacao;
import br.com.financehub.ApiFinanceHub.service.OrcamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orcamento")
@Tag(name = "Orçamento", description = "Endpoints para gerenciamento do orçamento")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @PutMapping("/limite")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Definir Limite Mensal", description = "Define o limite mensal de gastos")
    public void definirLimiteMensal(@RequestParam double limite) {
        orcamentoService.definirLimiteMensal(limite);
    }

    @PostMapping("/gasto")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Registrar Gasto", description = "Registra um gasto no orçamento e alerta se atingir 80% do limite")
    public void registrarGasto(@RequestParam double valor) {
        orcamentoService.registrarGasto(valor);
    }

    @PutMapping("/meta")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Definir Meta de Economia", description = "Define uma meta de economia mensal")
    public void definirMetaEconomia(@RequestParam double meta) {
        orcamentoService.definirMetaEconomia(meta);
    }

    @GetMapping("/cortes")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Sugerir Cortes de Gastos", description = "Sugere maneiras de economizar")
    public void sugerirCortes() {
        orcamentoService.sugerirCortes();
    }

    @GetMapping("/relatorio")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Gerar Relatório Mensal", description = "Gera um relatório de gastos dos últimos meses")
    public void gerarRelatorioMensal(@RequestBody Map<String, Double> historicoGastos) {
        orcamentoService.gerarRelatorioMensal(historicoGastos);
    }

    @GetMapping("/categoria/{tipoCategoria}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar Transações por Categoria", description = "Lista todas as transações de uma categoria específica")
    public List<Transacao> listarPorTipoCategoria(@PathVariable CategoriaTransacaoEnum tipoCategoria) {
        return orcamentoService.listarPorTipoCategoria(tipoCategoria);
    }
}

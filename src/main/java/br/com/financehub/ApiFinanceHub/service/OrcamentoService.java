package br.com.financehub.ApiFinanceHub.service;

import br.com.financehub.ApiFinanceHub.model.Orcamento;
import br.com.financehub.ApiFinanceHub.repository.OrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @PostMapping("/definir")
    public Orcamento definirOrcamentoMensal(@RequestParam Long usuarioId, @RequestParam double limiteMensal) {
        Optional<Orcamento> orcamentoExistente = orcamentoRepository.findByUsuarioId(usuarioId);

        Orcamento orcamento = orcamentoExistente.orElse(new Orcamento());
        orcamento.setUsuarioId(usuarioId);
        orcamento.setLimiteMensal(limiteMensal);
        orcamento.setGastoAtual(0);

        return orcamentoRepository.save(orcamento);
    }

    @PutMapping("/registrar-gasto")
    public void registrarGasto(@RequestParam Long usuarioId, @RequestParam double valorGasto) {
        Orcamento orcamento = orcamentoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orçamento não encontrado!"));

        double novoGasto = orcamento.getGastoAtual() + valorGasto;
        orcamento.setGastoAtual(novoGasto);

        if (novoGasto >= orcamento.getLimiteMensal() * 0.8) {
            System.out.println("Atenção: Você atingiu 80% do seu orçamento mensal!");
        }

        orcamentoRepository.save(orcamento);
    }

    @PutMapping("/definir-meta")
    public Orcamento definirMetaFinanceira(@RequestParam Long usuarioId, @RequestParam double metaEconomia) {
        Orcamento orcamento = orcamentoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orçamento não encontrado!"));

        orcamento.setMetaEconomia(metaEconomia);
        return orcamentoRepository.save(orcamento);
    }

    @GetMapping("/sugerir-cortes")
    public String sugerirCortesDeGastos(@RequestParam Long usuarioId) {
        Orcamento orcamento = orcamentoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orçamento não encontrado!"));

        if (orcamento.getGastoAtual() > orcamento.getLimiteMensal() * 0.8) {
            return "Sugestão: Considere reduzir gastos com lazer e refeições fora de casa.";
        }
        return "Seus gastos estão sob controle!";
    }

    @DeleteMapping("/excluir")
    public void excluirOrcamento(@RequestParam Long usuarioId) {
        Orcamento orcamento = orcamentoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orçamento não encontrado!"));

        orcamentoRepository.delete(orcamento);
    }
}

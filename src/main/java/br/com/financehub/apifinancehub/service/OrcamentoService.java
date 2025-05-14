package br.com.financehub.apifinancehub.service;

import br.com.financehub.apifinancehub.model.Orcamento;
import br.com.financehub.apifinancehub.model.Usuario;
import br.com.financehub.apifinancehub.repository.OrcamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrcamentoService {

    @Autowired
    OrcamentoRepository orcamentoRepository;

    @Autowired
    TransacaoService transacaoService;

    @Autowired
    UsuarioService usuarioService;

    @Transactional
    public Orcamento criarOrcamento(Orcamento orcamento, Long idUsuario, LocalDate mesReferencia){
        Optional<Usuario> usuarioBancoDeDados = usuarioService.listarUsuarioPorId(idUsuario);

        if (usuarioBancoDeDados.isEmpty()){
            throw new RuntimeException("Usuário não encontrado");
        }

        if (orcamento.getLimiteOrcamento() == null || orcamento.getLimiteOrcamento().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Limite não pode ser nulo ou negativo");
        }

        LocalDate mesAtual = LocalDate.now().withDayOfMonth(1);
        if (mesReferencia.isBefore(mesAtual)){
            throw new RuntimeException("O mês de referência não pode ser no passado");
        }

        Optional<Orcamento> orcamentoBancoDeDados = orcamentoRepository.findByUsuarioIdUsuarioAndMesReferencia(idUsuario, mesReferencia);
        if (orcamentoBancoDeDados.isPresent()) {
            throw new RuntimeException("Já existe um orçamento para o mês " + mesReferencia + " para este usuário");
        }

        orcamento.setUsuario(usuarioBancoDeDados.get());
        orcamento.setMesReferencia(mesReferencia);
        orcamento.setDataCriacao(LocalDateTime.now());

        return orcamentoRepository.save(orcamento);
    }

    public List<Orcamento> listarOrcamentosPorUsuario(Long idUsuario, LocalDate mesReferencia){
        Optional<Usuario> usuarioBancoDeDados = usuarioService.listarUsuarioPorId(idUsuario);

        if (usuarioBancoDeDados.isEmpty()){
            throw new RuntimeException("Usuário não encontrado");
        }

        if (mesReferencia != null){
            return orcamentoRepository.findAllByUsuarioIdUsuarioAndMesReferencia(idUsuario, mesReferencia);
        }else {
            return orcamentoRepository.findByUsuarioIdUsuario(idUsuario);
        }
    }

    public Optional<Orcamento> listarOrcamentoPorId(Long idOrcamento){
        Optional<Orcamento> orcamentoBancoDeDados = orcamentoRepository.findById(idOrcamento);
        if (orcamentoBancoDeDados.isEmpty()){
            throw new RuntimeException("Orçamento não encontrado!");
        }

        return orcamentoRepository.findById(idOrcamento);
    }

    public void deletarOrcamentoPorId(Long idOrcamento){
        Optional<Orcamento> orcamentoBancoDeDados = orcamentoRepository.findById(idOrcamento);
        if (orcamentoBancoDeDados.isEmpty()){
            throw new RuntimeException("Orçamento não encontrado!");
        }

        orcamentoRepository.deleteById(idOrcamento);
    }

    public void atualizarOrcamentoPorId(Orcamento orcamento, Long idOrcamento){
        Optional<Orcamento> orcamentoBancoDeDados = orcamentoRepository.findById(idOrcamento);
        if (orcamentoBancoDeDados.isEmpty()){
            throw new RuntimeException("Orçamento não encontrado");
        }

        Orcamento orcamentoEditado = orcamentoBancoDeDados.get();

        orcamentoEditado.setLimiteOrcamento(orcamento.getLimiteOrcamento());
        orcamentoEditado.setMesReferencia(orcamento.getMesReferencia());

        orcamentoRepository.save(orcamentoEditado);
    }
}

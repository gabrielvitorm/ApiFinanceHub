package br.com.financehub.ApiFinanceHub.repository;

import br.com.financehub.ApiFinanceHub.enums.CategoriaTransacaoEnum;
import br.com.financehub.ApiFinanceHub.enums.TipoTransacaoEnum;
import br.com.financehub.ApiFinanceHub.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    Optional<Transacao> findByTipoCategoria(CategoriaTransacaoEnum tipoCategoria);

    List<Transacao> findAllByTipoTransacao(TipoTransacaoEnum tipoTransacao);

    List<Transacao> findByUsuarioIdUsuarioAndDataCriacaoBetween(Long idUsuario, LocalDateTime dataInicio, LocalDateTime dataFim);
}

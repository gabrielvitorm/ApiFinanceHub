package br.com.financehub.apifinancehub.repository;

import br.com.financehub.apifinancehub.enums.CategoriaTransacaoEnum;
import br.com.financehub.apifinancehub.enums.TipoTransacaoEnum;
import br.com.financehub.apifinancehub.model.Transacao;
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

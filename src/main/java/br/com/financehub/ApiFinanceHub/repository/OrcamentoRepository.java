package br.com.financehub.ApiFinanceHub.repository;

import br.com.financehub.ApiFinanceHub.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

    Optional<Orcamento> findByUsuarioIdUsuarioAndMesReferencia(Long idUsuario, LocalDate mesReferencia);
    List<Orcamento> findByUsuarioIdUsuario(Long usuarioId);
    List<Orcamento> findAllByUsuarioIdUsuarioAndMesReferencia(Long usuarioId, LocalDate mesReferencia);
}

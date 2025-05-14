package br.com.financehub.apifinancehub.repository;

import br.com.financehub.apifinancehub.model.AlertaOrcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaOrcamentoRepository extends JpaRepository<AlertaOrcamento, Long> {
}

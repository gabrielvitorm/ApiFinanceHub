package br.com.financehub.ApiFinanceHub.repository;

import br.com.financehub.ApiFinanceHub.model.AlertaOrcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaOrcamentoRepository extends JpaRepository<AlertaOrcamento, Long> {
}

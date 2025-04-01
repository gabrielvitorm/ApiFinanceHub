package br.com.financehub.ApiFinanceHub.repository;

import br.com.financehub.ApiFinanceHub.model.Orcamento;
import br.com.financehub.ApiFinanceHub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

    Optional<Orcamento> findByUsuarioAndMesAndAno(Usuario usuario, int mes, int ano);
    
    List<Orcamento> findByUsuario(Usuario usuario);
}

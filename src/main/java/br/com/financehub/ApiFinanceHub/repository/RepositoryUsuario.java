package br.com.financehub.ApiFinanceHub.repository;

import br.com.financehub.ApiFinanceHub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUsuario extends JpaRepository<Usuario, Long> {
}

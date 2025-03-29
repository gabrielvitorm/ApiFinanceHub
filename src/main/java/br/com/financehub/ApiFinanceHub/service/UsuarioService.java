package br.com.financehub.ApiFinanceHub.service;

import br.com.financehub.ApiFinanceHub.model.Usuario;
import br.com.financehub.ApiFinanceHub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
 public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void criarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}

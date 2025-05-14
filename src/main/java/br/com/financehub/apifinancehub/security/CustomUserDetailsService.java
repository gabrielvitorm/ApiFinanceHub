package br.com.financehub.apifinancehub.security;

import br.com.financehub.apifinancehub.repository.UsuarioRepository;
import br.com.financehub.apifinancehub.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepo;

    @Autowired
    public CustomUserDetailsService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByEmailUsuario(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
        // Aqui usamos o User do Spring Security, mas você pode customizar os authorities
        return new org.springframework.security.core.userdetails.User(
                usuario.getEmailUsuario(),
                usuario.getSenhaUsuario(),
                Collections.emptyList()
        );
    }
}

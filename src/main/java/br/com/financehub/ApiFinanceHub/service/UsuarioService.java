package br.com.financehub.ApiFinanceHub.service;

import br.com.financehub.ApiFinanceHub.model.Usuario;
import br.com.financehub.ApiFinanceHub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
 public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void criarUsuario(Usuario usuario){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(usuario.getSenhaUsuario());

        usuario.setSenhaUsuario(senhaCriptografada);
        usuarioRepository.save(usuario);
    }
}

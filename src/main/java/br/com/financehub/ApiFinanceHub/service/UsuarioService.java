package br.com.financehub.ApiFinanceHub.service;

import br.com.financehub.ApiFinanceHub.model.Usuario;
import br.com.financehub.ApiFinanceHub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    public List<Usuario> listarTodosUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> listarUsuarioPorId(Long idUsuario){
        if (usuarioRepository.existsById(idUsuario)){
            return usuarioRepository.findById(idUsuario);
        }else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public Optional<Usuario> findByEmailUsuario(String emailUsuario){
        return usuarioRepository.findByEmailUsuario(emailUsuario);
    }

    public void deletarUsuarioPorId(Long idUsuario){
        if (usuarioRepository.existsById(idUsuario)){
            usuarioRepository.deleteById(idUsuario);
        }else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public void deletarUsuarioPorEmail(String emailUsuario){
        Optional<Usuario> usuarioDeletar = usuarioRepository.findByEmailUsuario(emailUsuario);

        if (usuarioDeletar.isPresent()) {
            usuarioRepository.delete(usuarioDeletar.get());
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }
}

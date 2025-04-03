package br.com.financehub.ApiFinanceHub.service;

import br.com.financehub.ApiFinanceHub.dto.AtualizarSenhaDTO;
import br.com.financehub.ApiFinanceHub.model.Usuario;
import br.com.financehub.ApiFinanceHub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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

    public void atualizarUsuarioPorId(Long idUsuario, Usuario usuario){
        Optional<Usuario> usuarioBancoDeDados = listarUsuarioPorId(idUsuario);

        if (usuarioBancoDeDados.isEmpty()){
            throw new RuntimeException("Usuário não encontrado no Banco de dados");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(usuario.getSenhaUsuario());

        Usuario usuarioEditado = usuarioBancoDeDados.get();

        usuarioEditado.setNomeUsuario(usuario.getNomeUsuario());
        usuarioEditado.setEmailUsuario(usuario.getEmailUsuario());
        usuarioEditado.setCpfUsuario(usuario.getCpfUsuario());
        usuarioEditado.setSenhaUsuario(senhaCriptografada);
        usuarioEditado.setDataModificacao(LocalDateTime.now());

        usuarioRepository.save(usuarioEditado);
    }

    public void atualizarUsuarioPorEmail(String emailUsuario, Usuario usuario){

        Optional<Usuario> usuarioBancoDeDados = findByEmailUsuario(emailUsuario);

        if (usuarioBancoDeDados.isEmpty()){
            throw new RuntimeException("Usuário não encontrado no Banco de dados");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(usuario.getSenhaUsuario());

        Usuario usuarioEditado = usuarioBancoDeDados.get();

        usuarioEditado.setNomeUsuario(usuario.getNomeUsuario());
        usuarioEditado.setEmailUsuario(usuario.getEmailUsuario());
        usuarioEditado.setCpfUsuario(usuario.getCpfUsuario());
        usuarioEditado.setSenhaUsuario(senhaCriptografada);
        usuarioEditado.setDataModificacao(LocalDateTime.now());

        usuarioRepository.save(usuarioEditado);
    }

    public void atualizarEmailPorEmail(String emailUsuario, Usuario usuario){
        Optional<Usuario> usuarioBancoDeDados = findByEmailUsuario(emailUsuario);

        if (usuarioBancoDeDados.isEmpty()){
            throw new RuntimeException("Usuário não encontrado");
        }

        Usuario usuarioEditado = usuarioBancoDeDados.get();

        Optional<Usuario> emailExistente = findByEmailUsuario(usuario.getEmailUsuario());
        if (emailExistente.isPresent()){
            throw new RuntimeException("Email já está em uso!");
        }

        usuarioEditado.setEmailUsuario(usuario.getEmailUsuario());
        usuarioEditado.setDataModificacao(LocalDateTime.now());

        usuarioRepository.save(usuarioEditado);
    }

    public void atualizarSenhaPorEmail(String emailUsuario, Usuario usuario){
        Optional<Usuario> usuarioBancoDeDados = findByEmailUsuario(emailUsuario);

        if (usuarioBancoDeDados.isEmpty()){
            throw new RuntimeException("Usuário não encontrado!");
        }

        Usuario usuarioEditado = usuarioBancoDeDados.get();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(usuario.getSenhaUsuario());

        usuarioEditado.setSenhaUsuario(senhaCriptografada);
        usuarioEditado.setDataModificacao(LocalDateTime.now());

        usuarioRepository.save(usuarioEditado);
    }

    public Usuario autenticarLoginUsuario(String emailUsuario, String senhaUsuario){
        Optional<Usuario> usuarioBancoDeDados = findByEmailUsuario(emailUsuario);

        if (usuarioBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        Usuario usuarioAutenticado = usuarioBancoDeDados.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(senhaUsuario, usuarioAutenticado.getSenhaUsuario())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        return usuarioAutenticado;
    }

    public void atualizarSenhaPorEmailCpf(AtualizarSenhaDTO dto) {
        Optional<Usuario> usuarioBancoDeDados = usuarioRepository.findByEmailUsuarioAndCpfUsuario(dto.getEmailUsuario(), dto.getCpfUsuario());

        if (usuarioBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou CPF inválidos");
        }

        Usuario usuarioEditado = usuarioBancoDeDados.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senhaCriptografada = encoder.encode(dto.getNovaSenha());
        usuarioEditado.setSenhaUsuario(senhaCriptografada);
        usuarioEditado.setDataModificacao(LocalDateTime.now());

        usuarioRepository.save(usuarioEditado);
    }
}

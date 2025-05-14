package br.com.financehub.apifinancehub.controller;

import br.com.financehub.apifinancehub.dto.AtualizarSenhaDTO;
import br.com.financehub.apifinancehub.dto.LoginDTO;
import br.com.financehub.apifinancehub.model.Usuario;
import br.com.financehub.apifinancehub.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um novo usuário", description = "Adiciona um novo usuário ao sistema.")
    public void criarUsuario(@RequestBody Usuario usuario){
        usuarioService.criarUsuario(usuario);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista todos os Usuários", description = "Lista todos os usuários do sistema")
    public List<Usuario> listarTodosUsuarios(){
        return usuarioService.listarTodosUsuarios();
    }

    @GetMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar usuários por Id", description = "Lista os usuários pelo id do usuário")
    public Optional<Usuario> listarUsuarioPorId(@PathVariable Long idUsuario){
        return usuarioService.listarUsuarioPorId(idUsuario);
    }

    @GetMapping("/emails/{emailUsuario}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar usuários por Email", description = "Lista os usuários pelo email")
    public Optional<Usuario> findByEmailUsuario(@PathVariable String emailUsuario){
        return usuarioService.findByEmailUsuario(emailUsuario);
    }

    @DeleteMapping("/ids/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar usuário por Id", description = "Deleta o usário pelo Id do usuário")
    public void deletarUsuarioPorId(@PathVariable Long idUsuario){
        usuarioService.deletarUsuarioPorId(idUsuario);
    }

    @DeleteMapping("/emails/{emailUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar usuário por Email", description = "Deleta o usário pelo Email do usuário")
    public void deletarUsuarioPorEmail(@PathVariable String emailUsuario){
        usuarioService.deletarUsuarioPorEmail(emailUsuario);
    }

    @PutMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Atualizar usuário por Id", description = "Atualiza todos os dados do usário pelo Id do usuário")
    public void atualizarUsuarioPorId(@PathVariable Long idUsuario, @RequestBody Usuario usuario){
        usuarioService.atualizarUsuarioPorId(idUsuario, usuario);
    }

    @PutMapping("/emails/{emailUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Atualizar usuário por Email", description = "Atualiza todos os dados do usário pelo Email do usuário")
    public void atualizarUsuarioPorEmail(@PathVariable String emailUsuario, @RequestBody Usuario usuario){
        usuarioService.atualizarUsuarioPorEmail(emailUsuario, usuario);
    }

    @PatchMapping("/emails/{emailUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Atualizar email por Email", description = "Atualiza somente o email do usário pelo Email atual do usuário")
    public void atualizarEmailPorEmail(@PathVariable String emailUsuario,@RequestBody Usuario usuario){
        usuarioService.atualizarEmailPorEmail(emailUsuario, usuario);
    }

    @PatchMapping("/atualizar-senha/{emailUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Atualizar senha por Email", description = "Atualiza somente a senha do usário pelo Email atual do usuário")
    public void atualizarSenhaPorEmail(@PathVariable String emailUsuario, @RequestBody Usuario usuario){
        usuarioService.atualizarSenhaPorEmail(emailUsuario,usuario);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Login Usuário", description = "Autenticação de login do usuário do sistema, através do email e da senha")
    public ResponseEntity<Map<String, String>> autenticarLoginUsuario(@RequestBody LoginDTO loginDTO) {
        usuarioService.autenticarLoginUsuario(loginDTO.getEmailUsuario(), loginDTO.getSenhaUsuario());
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login realizado com sucesso");
        response.put("email", loginDTO.getEmailUsuario());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/atualizar-senha-email-cpf")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Atualizar senha por Email e CPF", description = "Atualiza a senha do usuário validando o Email e CPF")
    public void atualizarSenhaPorEmailCpf(@RequestBody AtualizarSenhaDTO atualizarSenhaDTO) {
        usuarioService.atualizarSenhaPorEmailCpf(atualizarSenhaDTO);
    }
}

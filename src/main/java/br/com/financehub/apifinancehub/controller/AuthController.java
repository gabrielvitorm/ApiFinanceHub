package br.com.financehub.apifinancehub.controller;

import br.com.financehub.apifinancehub.dto.LoginDTO;
import br.com.financehub.apifinancehub.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            // 1) autentica usuário
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    loginDTO.getEmailUsuario(),
                    loginDTO.getSenhaUsuario()
            );
            authManager.authenticate(auth);

            // 2) gera JWT
            String token = jwtUtil.generateToken(loginDTO.getEmailUsuario());

            // 3) retorna token num JSON
            return ResponseEntity.ok(Map.of("token", token));

        } catch (AuthenticationException ex) {
            // 4) em caso de falha, retorna 401 com mensagem padronizada
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciais inválidas"));
        }
    }
}

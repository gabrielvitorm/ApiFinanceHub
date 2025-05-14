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

    @Autowired AuthenticationManager authManager;
    @Autowired JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getEmailUsuario(),
                            login.getSenhaUsuario()
                    )
            );
            String token = jwtUtil.generateToken(login.getEmailUsuario());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciais inválidas"));
        }
    }
}

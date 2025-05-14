package br.com.financehub.apifinancehub.config;

import br.com.financehub.apifinancehub.security.JwtFilter;
import br.com.financehub.apifinancehub.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1) Encoder de senha BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2) Carrega o usuário do DB para as autenticações
    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository repo) {
        return email -> repo.findByEmailUsuario(email)
                .map(u -> User.withUsername(u.getEmailUsuario())
                        .password(u.getSenhaUsuario())
                        .authorities("USER")
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    // 3) Expõe o AuthenticationManager pro AuthController
    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService
    ) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    // 4) Define a cadeia de filtros: CORS → CSRF off → Stateless → regras de rota → JWT
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
                // usa o CORS configurado em WebMvcConfig
                .cors().and()
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        // libera opções para preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // libera login e cadastro
                        .requestMatchers(HttpMethod.POST, "/auth/**", "/api/usuarios").permitAll()
                        // libera listagem de usuários sem token
                        .requestMatchers(HttpMethod.GET, "/api/usuarios").permitAll()
                        // todas as demais exigem token válido
                        .anyRequest().authenticated()
                )
                // injeta seu filtro JWT antes do filtro padrão de login
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

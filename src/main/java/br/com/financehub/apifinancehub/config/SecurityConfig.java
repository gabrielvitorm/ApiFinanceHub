package br.com.financehub.apifinancehub.config;

import br.com.financehub.apifinancehub.security.JwtFilter;
import br.com.financehub.apifinancehub.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1) Como o Spring vai comparar sua senha raw com o hash no banco:
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2) UserDetailsService: transforma seu Usuario → UserDetails
    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository repo) {
        return username -> repo.findByEmailUsuario(username)
                .map(u -> User.withUsername(u.getEmailUsuario())
                        .password(u.getSenhaUsuario())
                        .authorities("USER")  // ou pegue roles do seu modelo
                        .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    // 3) AuthenticationManager pra poder injetar no AuthController
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

    // 4) Filtro de segurança HTTP + JWT
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(auth -> auth
                        // libera login e cadastro
                        .requestMatchers(HttpMethod.POST, "/auth/**", "/api/usuarios").permitAll()
                        // todo o resto exige token
                        .anyRequest().authenticated()
                )
                // joga seu filtro JWT antes do UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

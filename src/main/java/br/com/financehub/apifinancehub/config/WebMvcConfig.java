package br.com.financehub.apifinancehub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // origens que podem chamar sua API (inclua localhost e seu domínio Vercel)
                .allowedOriginPatterns(
                        "http://localhost:5173",
                        "https://financehub-frontend.vercel.app",
                        "https://financehub-frontend-dev-git-dev-gabrielvitorms-projects.vercel.app/"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}

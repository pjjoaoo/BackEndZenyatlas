package com.itb.tcc.mif3an.pizzaria.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Libera para TODOS os endpoints (rotas) da API
                .allowedOrigins("*") // Libera acesso de qualquer site (React, Angular, etc)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT", "PATCH") // Libera todos os verbos HTTP
                .allowedHeaders("*"); // Libera todos os cabeçalhos
    }
}
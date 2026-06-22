package com.itb.tcc.mif3an.pizzaria.security.config;

import com.itb.tcc.mif3an.pizzaria.security.exceptions.CustomAcessDeniedHandler;
import com.itb.tcc.mif3an.pizzaria.security.exceptions.CustomAuthenticationEntryPoint;
import com.itb.tcc.mif3an.pizzaria.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static com.itb.tcc.mif3an.pizzaria.model.enums.Permission.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final String[] WHITE_LIST_URL = {
            "/api/v1/index",
            "/images/**",
            "/error"
    };

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final AccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthFilter,
            AuthenticationProvider authenticationProvider,
            LogoutHandler logoutHandler,
            CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
            AccessDeniedHandler customAccessDeniedHandler) {

        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
        this.logoutHandler = logoutHandler;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers("/api/v1/auth/**").permitAll()

                                // cliente
                                .requestMatchers(HttpMethod.GET, "/api/v1/clientes/*/pedidos/**").hasAuthority(PEDIDO_READ.name())
                                // gestao do adm
                                .requestMatchers(HttpMethod.PATCH, "/api/v1/clientes/*/status").hasAuthority(CLIENTE_MANAGER.name())
                                .requestMatchers(HttpMethod.PATCH, "/api/v1/clientes/*/bloqueio").hasAuthority(CLIENTE_MANAGER.name())
                                .requestMatchers(HttpMethod.GET, "/api/v1/clientes").hasAnyAuthority(CLIENTE_LIST.name())

                                // destinos
                                .requestMatchers(HttpMethod.GET, "/destinos/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/destinos").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/destinos/**").permitAll()
                                .requestMatchers(HttpMethod.PATCH, "/destinos/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/destinos/**").permitAll()

                                // hospedagens
                                .requestMatchers(HttpMethod.GET, "/hospedagens/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/hospedagens").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/hospedagens/**").permitAll()
                                .requestMatchers(HttpMethod.PATCH, "/hospedagens/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/hospedagens/**").permitAll()

                                // pacotes
                                .requestMatchers(HttpMethod.GET, "/pacotes/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/pacotes").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/pacotes/**").permitAll()
                                .requestMatchers(HttpMethod.PATCH, "/pacotes/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/pacotes/**").permitAll()

                                // promocoes
                                .requestMatchers(HttpMethod.GET, "/promocoes/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/promocoes").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/promocoes/**").permitAll()
                                .requestMatchers(HttpMethod.PATCH, "/promocoes/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/promocoes/**").permitAll()

                                // criação de cliente
                                .requestMatchers(HttpMethod.GET, "/api/v1/clientes/**").hasAuthority(CLIENTE_READ.name())
                                .requestMatchers(HttpMethod.PUT, "/api/v1/clientes/**").hasAuthority(CLIENTE_UPDATE.name())
                                .requestMatchers(HttpMethod.POST, "/api/v1/clientes/**").permitAll()

                                .requestMatchers(WHITE_LIST_URL).permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler(
                                        (request, response, authentication) ->
                                                SecurityContextHolder.clearContext())
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(
                "http://localhost:8686",
                "http://localhost:5173",
                "http://localhost:5174"
        ));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

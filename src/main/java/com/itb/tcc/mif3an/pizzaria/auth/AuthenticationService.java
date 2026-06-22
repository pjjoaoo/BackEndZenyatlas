package com.itb.tcc.mif3an.pizzaria.auth;

import com.itb.tcc.mif3an.pizzaria.dto.auth.AuthenticationRequest;
import com.itb.tcc.mif3an.pizzaria.dto.auth.AuthenticationResponse;
import com.itb.tcc.mif3an.pizzaria.exceptions.BadRequest;
import com.itb.tcc.mif3an.pizzaria.exceptions.Unauthorized;
import com.itb.tcc.mif3an.pizzaria.dto.auth.RegisterRequest;
import com.itb.tcc.mif3an.pizzaria.model.entity.Usuario;
import com.itb.tcc.mif3an.pizzaria.model.repository.UsuarioRepository;
import com.itb.tcc.mif3an.pizzaria.security.jwt.JwtService;
import com.itb.tcc.mif3an.pizzaria.security.token.Token;
import com.itb.tcc.mif3an.pizzaria.security.token.TokenRepository;
import com.itb.tcc.mif3an.pizzaria.security.token.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Service
public class AuthenticationService {
    private final UsuarioRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UsuarioRepository repository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        try {
            // 1. Verifica se o email já existe
            var usuarioDb = repository.findByEmail(request.getEmail());
            if (usuarioDb.isPresent()) {
                throw new BadRequest("Já existe este email cadastrado em nossa base de dados");
            }

            // 2. Cria a classe dinamicamente (Cliente, Funcionario, etc.)
            String tipo = request.getTipoUsuario().name();
            String nomeClass = tipo.substring(0, 1).toUpperCase() + tipo.substring(1).toLowerCase();

            Class<?> clazz = Class.forName("com.itb.tcc.mif3an.pizzaria.model.entity." + nomeClass);
            Usuario usuario = (Usuario) clazz.newInstance();

            // 3. Preenche os dados do usuário
            usuario.setCodStatus(true);
            usuario.setNome(request.getNome());
            usuario.setEmail(request.getEmail());
            // Pegando a senha usando getSenha() minha dto para autenticação da senha (via postman ou endpoint il)
            usuario.setSenha(passwordEncoder.encode(request.getSenha()));

            // Agrada o banco de dados e o JWT
            usuario.setTipoUsuario(request.getTipoUsuario());

            // 4. Salva no banco de dados
            repository.save(usuario);

            // 5. Gera os tokens usando o objeto 'usuario' (que não está nulo!)
            var jwtToken = jwtService.generateToken(usuario);
            var refreshToken = jwtService.generateRefreshToken(usuario);

            // 6. Salva o token no banco (tabela de tokens)
            saveUserToken(usuario, jwtToken);

            return new AuthenticationResponse(jwtToken, refreshToken);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao instanciar classe do usuário: " + e.getMessage());
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getSenha()
                    )
            );
        } catch (Exception e) {
            throw new BadRequest("Email ou Password Incorreto");
        }
        Usuario user = (Usuario) repository.findByEmail(request.getEmail()).get();
        if (!user.isCodStatus()) {
            throw new Unauthorized("Conta inativa, por favor procurar o administrador da conta");
        }

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    private void saveUserToken(Usuario usuario, String jwtToken) {
        var token = new Token();
        token.setUsuario(usuario);
        token.setToken(jwtToken);
        token.setTokenType(TokenType.BEARER);
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Usuario usuario) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(usuario.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            Usuario user = (Usuario) this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = new AuthenticationResponse(accessToken, refreshToken);
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
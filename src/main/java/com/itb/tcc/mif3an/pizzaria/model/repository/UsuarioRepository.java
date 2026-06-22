package com.itb.tcc.mif3an.pizzaria.model.repository;

import com.itb.tcc.mif3an.pizzaria.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    static Optional<Usuario> findByEmailAndSenha(
            String Usuario,
            String senha) {
        return null;
    }

    @PostMapping("/login")
    public default ResponseEntity<?> fazerLogin(@RequestBody Usuario usuarioLogin) {

        Optional<Usuario> usuarioLogado = UsuarioRepository.findByEmailAndSenha(usuarioLogin.getEmail(), usuarioLogin.getSenha());

        if (usuarioLogado.isPresent()) {
            return ResponseEntity.ok(usuarioLogado.get());
        } else {
            // Se não achou (senha ou e-mail errados), o Java barra a entrada (Status 401 Unauthorized)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha incorretos.");
        }
    }

    Optional<Usuario> findByEmail(String email);
}

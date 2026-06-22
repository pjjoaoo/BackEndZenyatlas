package com.itb.tcc.mif3an.pizzaria.security.token;

import com.itb.tcc.mif3an.pizzaria.model.entity.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter; // 💡 Adicionei o Setter

@Entity
@Table(name = "tokens") // 💡 É boa prática dar um nome no plural para a tabela
@Getter
@Setter // 💡 Adicionei o Setter para você poder alterar os valores depois
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String token;
    private boolean revoked;
    private boolean expired;
    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
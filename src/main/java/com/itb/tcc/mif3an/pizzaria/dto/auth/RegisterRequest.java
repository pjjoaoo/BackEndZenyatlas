package com.itb.tcc.mif3an.pizzaria.dto.auth;

import com.itb.tcc.mif3an.pizzaria.model.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String nome;


    private String email;

    // Método ajustado (caso o seu serviço chame getPassword() ao invés de getSenha())
    private String senha;

    private TipoUsuario tipoUsuario;

}
package com.itb.tcc.mif3an.pizzaria.dto.auth;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("acess_token")
    private String acessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;
}

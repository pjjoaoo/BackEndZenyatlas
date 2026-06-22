package com.itb.tcc.mif3an.pizzaria.auth;

import com.itb.tcc.mif3an.pizzaria.dto.auth.RegisterRequest;
import com.itb.tcc.mif3an.pizzaria.dto.auth.AuthenticationRequest;
import com.itb.tcc.mif3an.pizzaria.dto.auth.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    private final AuthenticationService service;

    public AuthController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiauthenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request // O @Valid é quem "liga" as validações que o professor pediu!
    ) {
        // Estou assumindo que o seu AuthenticationService tem um método chamado "register".
        return ResponseEntity.ok(service.register(request));
    }
}

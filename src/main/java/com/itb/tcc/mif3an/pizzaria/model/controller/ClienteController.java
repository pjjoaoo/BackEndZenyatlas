package com.itb.tcc.mif3an.pizzaria.model.controller;


import com.itb.tcc.mif3an.pizzaria.dto.auth.AuthenticationResponse;
import com.itb.tcc.mif3an.pizzaria.auth.AuthenticationService;
import com.itb.tcc.mif3an.pizzaria.dto.auth.RegisterRequest;
import com.itb.tcc.mif3an.pizzaria.dto.pedido.PedidoResponse;
import com.itb.tcc.mif3an.pizzaria.exceptions.BadRequest;
import com.itb.tcc.mif3an.pizzaria.exceptions.Forbidden;
import com.itb.tcc.mif3an.pizzaria.model.entity.Usuario;
import com.itb.tcc.mif3an.pizzaria.model.enums.TipoUsuario;
import com.itb.tcc.mif3an.pizzaria.model.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final AuthenticationService authenticationService;
    private final ClienteService clienteService;

    public ClienteController(AuthenticationService authenticationService, ClienteService clienteService) {
        this.authenticationService = authenticationService;
        this.clienteService = clienteService;
    }

    //@PermitAll
    // @PreAuthorize("hasAuthority('USER_CREATE')")
    @PostMapping
    public ResponseEntity<AuthenticationResponse> registerCliente (@RequestBody RegisterRequest registerRequest) {
        registerRequest.setTipoUsuario(TipoUsuario.CLIENTE);
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PreAuthorize("isAuthenticated() and T (java.lang.Long).parseLong(#id) == principaç.id")
    @GetMapping("/{id}/pedidos")
    public ResponseEntity<List<PedidoResponse>> findAllPedidoByClienteId(@PathVariable(value = "id") String id) {

        try {
            Long clienteId = Long.parseLong(id);
            Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!usuarioLogado.getTipoUsuario().equals(clienteId)) {
                throw new Forbidden("Voce só pode acessar seus proprios pedidos");
            }
            return ResponseEntity.ok().body(clienteService.findAllPedidosByClienteId(clienteId));


        }catch (NumberFormatException e){


            throw new BadRequest("'" + id + "'não é um numero inteiro invalido'");
        }

    }
}
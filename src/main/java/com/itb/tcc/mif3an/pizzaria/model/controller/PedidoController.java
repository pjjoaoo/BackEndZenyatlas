package com.itb.tcc.mif3an.pizzaria.model.controller;

import com.itb.tcc.mif3an.pizzaria.dto.auth.PedidoRequest; // Confirme se o pacote do seu DTO é esse mesmo
import com.itb.tcc.mif3an.pizzaria.model.entity.Pedido;
import com.itb.tcc.mif3an.pizzaria.model.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos") // Rota profissional
public class PedidoController {

    // Injetando o SERVICE em vez do REPOSITORY
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // 1. Rota para CRIAR a viagem (Passando pela lógica do Service)
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoRequest request) {
        Pedido novoPedido = pedidoService.save(request);
        return ResponseEntity.status(201).body(novoPedido);
    }

    // 2. Rota para ATIVIDADE NOTA 9 (Listar viagens do cliente)
    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Pedido>> listarPorCliente(@PathVariable Long id) {
        List<Pedido> minhasViagens = pedidoService.listarViagensDoCliente(id);

        if (minhasViagens.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 se estiver vazio
        }

        return ResponseEntity.ok(minhasViagens); // Retorna 200 com a lista de viagens
    }
}
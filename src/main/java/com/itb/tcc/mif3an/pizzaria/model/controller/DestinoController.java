package com.itb.tcc.mif3an.pizzaria.model.controller;

import com.itb.tcc.mif3an.pizzaria.dto.auth.DestinoRequest;
import com.itb.tcc.mif3an.pizzaria.model.entity.Destino;
import com.itb.tcc.mif3an.pizzaria.model.services.DestinoService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

    private final DestinoService destinoService;

    public DestinoController(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    @GetMapping
    public ResponseEntity<List<Destino>> getDestinos() {
        return ResponseEntity.ok(destinoService.listarTodos());
    }



    @PostMapping
    public ResponseEntity<Destino> criarDestino(@RequestBody DestinoRequest request) {
        Destino novoDestino = destinoService.salvar(request);
        return ResponseEntity.status(201).body(novoDestino);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destino> atualizar(@PathVariable Long id, @RequestBody DestinoRequest request) {
        Destino destinoAtualizado = destinoService.atualizar(id, request);
        return ResponseEntity.ok(destinoAtualizado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Destino> atualizarParcial(@PathVariable Long id, @RequestBody DestinoRequest request) {
        Destino destinoAtualizado = destinoService.atualizar(id, request);
        return ResponseEntity.ok(destinoAtualizado);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        destinoService.deletar(id);
        return ResponseEntity.noContent().build();
    }




}

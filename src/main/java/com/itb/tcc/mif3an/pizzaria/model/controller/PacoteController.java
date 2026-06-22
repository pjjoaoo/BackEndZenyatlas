package com.itb.tcc.mif3an.pizzaria.model.controller;

import com.itb.tcc.mif3an.pizzaria.model.entity.Pacote;
import com.itb.tcc.mif3an.pizzaria.model.repository.PacoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacotes")
public class PacoteController {

    private final PacoteRepository repository;

    public PacoteController(PacoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Pacote>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Pacote> criar(@RequestBody Pacote pacote) {
        pacote.setAtivo(true);
        return ResponseEntity.status(201).body(repository.save(pacote));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<Pacote> editar(@PathVariable Long id, @RequestBody Pacote dados) {
        return repository.findById(id).map(p -> {
            if (dados.getNome() != null) p.setNome(dados.getNome());
            if (dados.getDescricao() != null) p.setDescricao(dados.getDescricao());
            if (dados.getPreco() != null) p.setPreco(dados.getPreco());
            if (dados.getImagem() != null) p.setImagem(dados.getImagem());
            if (dados.getAereo() != null) p.setAereo(dados.getAereo());
            if (dados.getSaida() != null) p.setSaida(dados.getSaida());
            if (dados.getAtivo() != null) p.setAtivo(dados.getAtivo());
            return ResponseEntity.ok(repository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }
}

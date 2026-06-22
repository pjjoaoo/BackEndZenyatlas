package com.itb.tcc.mif3an.pizzaria.model.controller;

import com.itb.tcc.mif3an.pizzaria.model.entity.Hospedagem;
import com.itb.tcc.mif3an.pizzaria.model.repository.HospedagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospedagens")
public class HospedagemController {

    private final HospedagemRepository repository;

    public HospedagemController(HospedagemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Hospedagem>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }
    @PostMapping
    public ResponseEntity<Hospedagem> criar(@RequestBody Hospedagem hospedagem) {
        hospedagem.setAtivo(true);
        return ResponseEntity.status(201).body(repository.save(hospedagem));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        repository.findById(id).ifPresent(h -> {
            h.setAtivo(false);
            repository.save(h);
        });
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<Hospedagem> editar(@PathVariable Long id, @RequestBody Hospedagem dados) {
        return repository.findById(id).map(h -> {
            if (dados.getNome() != null) h.setNome(dados.getNome());
            if (dados.getDescricao() != null) h.setDescricao(dados.getDescricao());
            if (dados.getPreco() != null) h.setPreco(dados.getPreco());
            if (dados.getImagem() != null) h.setImagem(dados.getImagem());
            if (dados.getAllInclusive() != null) h.setAllInclusive(dados.getAllInclusive());
            if (dados.getPiscina() != null) h.setPiscina(dados.getPiscina());
            if (dados.getCheckin() != null) h.setCheckin(dados.getCheckin());
            if (dados.getAtivo() != null) h.setAtivo(dados.getAtivo());
            return ResponseEntity.ok(repository.save(h));
        }).orElse(ResponseEntity.notFound().build());
    }

}

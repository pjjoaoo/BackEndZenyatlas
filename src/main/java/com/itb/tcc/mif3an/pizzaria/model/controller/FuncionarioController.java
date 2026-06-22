package com.itb.tcc.mif3an.pizzaria.model.controller;

import com.itb.tcc.mif3an.pizzaria.model.entity.Funcionario;
import com.itb.tcc.mif3an.pizzaria.model.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/funcionarios") // http://localhost:8080/funcionarios
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @GetMapping
    public List<Funcionario> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Funcionario salvar(@RequestBody Funcionario novoFuncionario) {
        return repository.save(novoFuncionario);
    }
}
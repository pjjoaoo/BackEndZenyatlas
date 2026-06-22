package com.itb.tcc.mif3an.pizzaria.model.controller;

import com.itb.tcc.mif3an.pizzaria.model.entity.Admin;
import com.itb.tcc.mif3an.pizzaria.model.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins") /* http://localhost:8080/admins */
public class AdminController {

    @Autowired
    private AdminRepository repository;

    @GetMapping
    public List<Admin> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Admin salvar(@RequestBody Admin novoAdmin) {
        return repository.save(novoAdmin);
    }
}
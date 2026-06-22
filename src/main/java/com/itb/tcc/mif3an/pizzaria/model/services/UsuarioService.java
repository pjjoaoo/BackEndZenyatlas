package com.itb.tcc.mif3an.pizzaria.model.services;

import com.itb.tcc.mif3an.pizzaria.exceptions.NotFound;
import com.itb.tcc.mif3an.pizzaria.model.entity.Usuario;
import com.itb.tcc.mif3an.pizzaria.model.repository.UsuarioRepository;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findByEmail(String email){
        try {
            return usuarioRepository.findByEmail(email).get();
        }catch (Exception e){
            throw new NotFound("Usuario não encontrado com o e-mail " + email);
        }
    }

}

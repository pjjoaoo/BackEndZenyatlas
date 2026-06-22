package com.itb.tcc.mif3an.pizzaria.model.services;

import com.itb.tcc.mif3an.pizzaria.dto.auth.DestinoRequest;
import com.itb.tcc.mif3an.pizzaria.exceptions.NotFound;
import com.itb.tcc.mif3an.pizzaria.model.entity.Destino;
import com.itb.tcc.mif3an.pizzaria.model.repository.DestinoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DestinoService {

    private final DestinoRepository destinoRepository;

    public DestinoService(DestinoRepository destinoRepository) {
        this.destinoRepository = destinoRepository;
    }

    public List<Destino> listarDestinosAtivos() {
        return destinoRepository.findByAtivoTrue();
    }

    public Destino buscarPorId(Long id) {
        return destinoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Destino salvar(DestinoRequest request) {
        Destino destino = new Destino();
        destino.setNome(request.getNome());
        destino.setDescricao(request.getDescricao());
        destino.setAereo(request.getAereo());
        destino.setImagem(request.getImagem());
        destino.setPreco(request.getPreco());
        destino.setSaida(request.getSaida());
        destino.setAtivo(request.getAtivo() != null ? request.getAtivo() : true);
        return destinoRepository.save(destino);
    }

    @Transactional
    public Destino atualizar(Long id, DestinoRequest request) {
        Destino destino = destinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado com o ID: " + id));

        destino.setNome(request.getNome());
        destino.setDescricao(request.getDescricao());
        destino.setAereo(request.getAereo());
        destino.setImagem(request.getImagem());
        destino.setPreco(request.getPreco());
        destino.setSaida(request.getSaida());
        destino.setAtivo(request.getAtivo() != null ? request.getAtivo() : destino.getAtivo());
        return destinoRepository.save(destino);
    }


    @Transactional
    public void deletar(Long id) {
        Destino destino = destinoRepository.findById(id)
                .orElseThrow(() -> new NotFound("Destino não encontrado com ID: " + id));
        destinoRepository.delete(destino);
    }
    public List<Destino> listarTodos() {
        return destinoRepository.findAll();
    }


}

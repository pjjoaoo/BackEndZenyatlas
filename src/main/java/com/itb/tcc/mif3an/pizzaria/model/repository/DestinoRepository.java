package com.itb.tcc.mif3an.pizzaria.model.repository;

import com.itb.tcc.mif3an.pizzaria.model.entity.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long> {

    // 💡 Dica de Ouro: Esse método é criado magicamente pelo Spring!
    // Ele vai buscar no banco apenas os destinos onde a coluna 'ativo' for igual a 1 (true)
    List<Destino> findByAtivoTrue();
}
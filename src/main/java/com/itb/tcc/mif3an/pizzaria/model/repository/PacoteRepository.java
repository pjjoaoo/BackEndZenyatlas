package com.itb.tcc.mif3an.pizzaria.model.repository;

import com.itb.tcc.mif3an.pizzaria.model.entity.Pacote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PacoteRepository extends JpaRepository<Pacote, Long> {
    List<Pacote> findByAtivoTrue();
}

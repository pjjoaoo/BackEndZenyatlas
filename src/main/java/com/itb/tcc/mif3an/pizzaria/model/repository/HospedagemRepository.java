package com.itb.tcc.mif3an.pizzaria.model.repository;

import com.itb.tcc.mif3an.pizzaria.model.entity.Hospedagem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HospedagemRepository extends JpaRepository<Hospedagem, Long> {
    List<Hospedagem> findByAtivoTrue();
}

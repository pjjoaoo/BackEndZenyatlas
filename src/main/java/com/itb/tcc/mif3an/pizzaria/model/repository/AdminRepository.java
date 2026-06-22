package com.itb.tcc.mif3an.pizzaria.model.repository;

import com.itb.tcc.mif3an.pizzaria.model.entity.Admin; // Certifique-se de importar o Admin
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
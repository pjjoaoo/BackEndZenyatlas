package com.itb.tcc.mif3an.pizzaria.model.repository;

import com.itb.tcc.mif3an.pizzaria.model.entity.Cliente; // Importa a entidade Cliente
import com.itb.tcc.mif3an.pizzaria.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.id = ?1 AND c.codStatus = true")    Optional<Cliente> findById(Long id);


    @Query(value = "SELECT p from Pedido p JOIN FETCH p.cliente c WHERE c.id= :id")
    public List<Pedido> findAllPedidosByCliente(@Param("id") Long clienteId);

}


/* DROP TABLE IF EXISTS tokens;
DROP TABLE IF EXISTS pedidos;
DROP TABLE IF EXISTS destinos;
DROP TABLE IF EXISTS usuarios; */
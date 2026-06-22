package com.itb.tcc.mif3an.pizzaria.model.repository;

import com.itb.tcc.mif3an.pizzaria.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT * FROM pedido p WHERE p.numero_pedido= ?1 AND p.cod_status='1'", nativeQuery = true)
        public Pedido findByNumeroPedido(String numeroPedido);


    List<Pedido> findByClienteId(Long clienteId);
}
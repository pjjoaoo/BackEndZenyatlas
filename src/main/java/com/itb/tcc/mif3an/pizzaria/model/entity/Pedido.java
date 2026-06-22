package com.itb.tcc.mif3an.pizzaria.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "pedidos")

public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preco", precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "metodo_pagamento", length = 50)
    private String MetodoPagamento;

    @Column(name = "data_pedido", insertable = false, updatable = false)
    private LocalDateTime dataPedido;

    @Column(length = 20)
    private String status;

    // 🔗 relacionamento com usuário
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // 🔗 relacionamento com destino
    @ManyToOne
    @JoinColumn(name = "destino_id")
    private Destino destino;

    private boolean codstatus;

}

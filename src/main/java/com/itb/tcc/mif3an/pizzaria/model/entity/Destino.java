package com.itb.tcc.mif3an.pizzaria.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "destinos")
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(length = 500)
    private String imagem;

    @Column(columnDefinition = "VARCHAR(MAX)")
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(length = 255)
    private String aereo;

    private LocalDate saida;

    @Column(columnDefinition = "BIT DEFAULT 1")
    private Boolean ativo;

}

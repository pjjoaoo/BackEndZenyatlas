package com.itb.tcc.mif3an.pizzaria.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper=true)
@Entity
@DiscriminatorValue("CLIENTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Usuario {

    @Column(length = 10)
    private String numeroPontos;
}
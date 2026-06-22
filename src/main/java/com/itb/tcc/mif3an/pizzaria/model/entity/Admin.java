package com.itb.tcc.mif3an.pizzaria.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper=true)
@Entity
@DiscriminatorValue("ADMIN")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends Usuario {

    @Column(length = 20)
    private String nivelAcesso;
}
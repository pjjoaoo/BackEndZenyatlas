package com.itb.tcc.mif3an.pizzaria.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper=true)
@Entity
@DiscriminatorValue("FUNCIONARIO")
@Data
@NoArgsConstructor
public class Funcionario extends Usuario {
}
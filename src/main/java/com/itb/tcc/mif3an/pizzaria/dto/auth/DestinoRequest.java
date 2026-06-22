package com.itb.tcc.mif3an.pizzaria.dto.auth;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DestinoRequest {
    private String aereo;
    private Boolean ativo;
    private String descricao;
    private String imagem;
    private String nome;
    private BigDecimal preco;
    private LocalDate saida;
}

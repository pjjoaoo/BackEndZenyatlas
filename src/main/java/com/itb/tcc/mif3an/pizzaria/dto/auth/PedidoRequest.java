package com.itb.tcc.mif3an.pizzaria.dto.auth;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PedidoRequest {
    private Long clienteId;
    private Long destinoId;
    private BigDecimal formaPagamento;

    public String getMetodoPagamento() {
        return null;
    }
}

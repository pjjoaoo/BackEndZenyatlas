package com.itb.tcc.mif3an.pizzaria.dto.pedido;

import com.itb.tcc.mif3an.pizzaria.model.entity.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoResponse {

    private Long id;
    private String clienteNome;
    private Long destinoId;
    private LocalDateTime dataPedido;
    private BigDecimal precoPedido;
    private String status;

    public PedidoResponse(Pedido pedido) {
        this.id = pedido.getId();
        this.destinoId = pedido.getDestino().getId();
        this.clienteNome = pedido.getCliente().getNome();
        this.dataPedido = pedido.getDataPedido();
        this.precoPedido = pedido.getPreco();
        this.status = pedido.getStatus();

    }
}

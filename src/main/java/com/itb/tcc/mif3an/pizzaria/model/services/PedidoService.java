package com.itb.tcc.mif3an.pizzaria.model.services;

import com.itb.tcc.mif3an.pizzaria.dto.auth.PedidoRequest;
import com.itb.tcc.mif3an.pizzaria.model.entity.Cliente;
import com.itb.tcc.mif3an.pizzaria.model.entity.Destino;
import com.itb.tcc.mif3an.pizzaria.model.entity.Pedido;
import com.itb.tcc.mif3an.pizzaria.model.repository.PedidoRepository;
import com.itb.tcc.mif3an.pizzaria.model.repository.DestinoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;

@Service
public class PedidoService {

    ZoneId zoneIdBrazil = ZoneId.of("America/Sao_Paulo");
    private Random random = new Random();

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final DestinoRepository destinoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteService clienteService, DestinoRepository destinoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
        this.destinoRepository = destinoRepository;
    }

    @Transactional
    public Pedido save(PedidoRequest request){

            Cliente cliente = clienteService.findById(request.getClienteId());

            Destino destino = destinoRepository.findById(request.getDestinoId())
                    .orElseThrow(() -> new RuntimeException("Destino não encontrado"));

            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setDestino(destino);
            pedido.setMetodoPagamento(request.getMetodoPagamento());
            pedido.setPreco(destino.getPreco());

            pedido.setCodstatus(true);
            pedido.setStatus("PENDENTE");

            java.time.ZoneId zoneIdBrazilLocal = java.time.ZoneId.of("America/Sao_Paulo");
            pedido.setDataPedido(java.time.LocalDateTime.now(zoneIdBrazilLocal));

            return pedidoRepository.save(pedido);
        }
    public List<Pedido> listarViagensDoCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }
}
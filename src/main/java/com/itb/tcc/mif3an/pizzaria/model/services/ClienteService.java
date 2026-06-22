package com.itb.tcc.mif3an.pizzaria.model.services;


import com.itb.tcc.mif3an.pizzaria.dto.pedido.PedidoResponse;
import com.itb.tcc.mif3an.pizzaria.exceptions.NotFound;
import com.itb.tcc.mif3an.pizzaria.model.entity.Cliente;
import com.itb.tcc.mif3an.pizzaria.model.entity.Pedido;
import com.itb.tcc.mif3an.pizzaria.model.entity.Usuario;
import com.itb.tcc.mif3an.pizzaria.model.repository.ClienteRepository;
import com.itb.tcc.mif3an.pizzaria.model.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<PedidoResponse> findAllPedidosByClienteId(Long clienteId) {

        try {
            Usuario usuario = clienteRepository.findById(clienteId).get();
            List<Pedido> pedidos = clienteRepository.findAllPedidosByCliente(usuario.getId());

            return pedidos.stream()
                    .map(PedidoResponse::new)
                    .toList();

        } catch (Exception e) {
            throw new NotFound("Cliente não encontrado com o id: " + clienteId);
        }
    }

    public Cliente findById(Long id){
        if(!clienteRepository.findById(id).isPresent()){
            throw (new NotFound("Cliente não encontrado com o id " + id));
        }
        return clienteRepository.findById(id).get();
    }
}
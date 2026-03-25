package com.example.demo.services;

import com.example.demo.models.PedidoModel;
import com.example.demo.models.ProdutoModel;
import com.example.demo.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoModel criarPedido(PedidoModel pedidoModel){
        return pedidoRepository.save(pedidoModel);
    }

    public List<PedidoModel> listarPedido(){
        return pedidoRepository.findAll();
    }

    public PedidoModel buscarPedido(Long id){
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não Encontrado"));
    }

    public void deletarPedido(Long id){
        if (!pedidoRepository.existsById(id)){
            throw new RuntimeException("Pedido nao encontrado");
        }
        pedidoRepository.deleteById(id);
    }

    public PedidoModel atualizarPedido(Long id, PedidoModel pedidoModel){
        PedidoModel model = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido nao encontrado"));

        model.setData(pedidoModel.getData());
        model.setValorTotal(pedidoModel.getValorTotal());
        model.setStatus(pedidoModel.getStatus());

        return pedidoRepository.save(model);
    }
}

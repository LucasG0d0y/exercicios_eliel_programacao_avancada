package com.example.demo.controllers;

import com.example.demo.models.PedidoModel;
import com.example.demo.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoModel> criarPedido(@RequestBody PedidoModel pedidoModel) {
        PedidoModel request = pedidoService.criarPedido(pedidoModel);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(request.getId())
                .toUri();

        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<PedidoModel>> listarPedido(){

        return ResponseEntity.ok(pedidoService.listarPedido());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> buscarPedido(@PathVariable Long id){
        PedidoModel pedido = pedidoService.buscarPedido(id);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoModel> atualizarPedido(@PathVariable Long id, @RequestBody PedidoModel pedidoModel){
        PedidoModel atualizado = pedidoService.atualizarPedido(id, pedidoModel);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }

}

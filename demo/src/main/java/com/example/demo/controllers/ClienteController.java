package com.example.demo.controllers;

import com.example.demo.models.ClienteModel;
import com.example.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    public ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteModel> criarCliente(@RequestBody ClienteModel clienteModel){
        ClienteModel request = clienteService.criarCliente(clienteModel);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(request.getId())
                .toUri();

        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<ClienteModel>> listarCliente(){
        return ResponseEntity.ok(clienteService.listarCliente());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> buscarCliente(@PathVariable Long id){
        ClienteModel cliente = clienteService.buscarCliente(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<ClienteModel> atualizarCliente(@PathVariable Long id, @RequestBody ClienteModel clienteModel){
        ClienteModel atualizado = clienteService.atualizarCliente(id, clienteModel);
        return ResponseEntity.ok(atualizado);

    }
}

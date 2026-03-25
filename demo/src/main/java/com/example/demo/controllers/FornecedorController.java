package com.example.demo.controllers;

import com.example.demo.models.FornecedorModel;
import com.example.demo.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<FornecedorModel> criarFornecedor(@RequestBody FornecedorModel fornecedorModel){
        FornecedorModel request = fornecedorService.criarFornecedor(fornecedorModel);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(request.getId())
                .toUri();

        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<FornecedorModel>> listarFornecedor(){
        return ResponseEntity.ok(fornecedorService.listarFornecedor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorModel> buscarFornecedor(@PathVariable Long id){
        FornecedorModel fornecedor = fornecedorService.buscarFornecedor(id);
        return ResponseEntity.ok(fornecedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFornecedor(@PathVariable Long id){
        fornecedorService.deletarFornecedor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorModel> atualizarFornecedor(@PathVariable Long id, @RequestBody FornecedorModel fornecedorModel){
        FornecedorModel atualizado = fornecedorService.atualizarFornecedor(id, fornecedorModel);
        return ResponseEntity.ok(atualizado);
    }
}

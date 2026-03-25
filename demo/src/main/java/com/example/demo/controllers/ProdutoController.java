package com.example.demo.controllers;

import com.example.demo.models.ProdutoModel;
import com.example.demo.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoModel> criarProduto(@RequestBody ProdutoModel produtoModel){
        ProdutoModel request = produtoService.criarProduto(produtoModel);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(request.getId())
                .toUri();

        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> buscarTodosProduto(){
        return ResponseEntity.ok(produtoService.listarProduto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> buscarProduto(@PathVariable Long id){
        ProdutoModel produto = produtoService.buscarProduto(id);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoModel> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoModel produtoModel){
        ProdutoModel atualizado = produtoService.atualizarProduto(id, produtoModel);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
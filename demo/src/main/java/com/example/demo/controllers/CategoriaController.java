package com.example.demo.controllers;

import com.example.demo.models.CategoriaModel;
import com.example.demo.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaModel> criarCategoria(@RequestBody CategoriaModel categoriaModel){
        CategoriaModel request = categoriaService.criarCategoria(categoriaModel);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(request.getId())
                .toUri();

        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaModel>> listarCategoria(){
        return ResponseEntity.ok(categoriaService.listarCategoria());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaModel> buscarCategoria(@PathVariable Long id){
        CategoriaModel categoria = categoriaService.buscarCategoria(id);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id){
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaModel> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaModel categoriaModel){
        CategoriaModel atualizado = categoriaService.atualizarCategoria(id, categoriaModel);
        return ResponseEntity.ok(atualizado);
    }
}

package com.example.demo.controllers;


import com.example.demo.models.AutorModel;
import com.example.demo.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<AutorModel> criarAutor(@RequestBody AutorModel autorModel){
        AutorModel request = autorService.criarAutor(autorModel);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(request.getId())
                .toUri();

        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<AutorModel>> listarAutor(){
        return ResponseEntity.ok(autorService.listarAutor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorModel> buscarAutor(@PathVariable Long id){
        AutorModel autor = autorService.buscarAutor(id);
        return ResponseEntity.ok(autor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id){
        autorService.deletarAutor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorModel> atualizarAutor(@PathVariable Long id, @RequestBody AutorModel autorModel){
        AutorModel atualizado = autorService.atualizarAutor(id, autorModel);
        return ResponseEntity.ok(atualizado);
    }
}

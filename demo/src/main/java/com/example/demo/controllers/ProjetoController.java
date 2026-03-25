package com.example.demo.controllers;

import com.example.demo.models.ProjetoModel;
import com.example.demo.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public ResponseEntity<ProjetoModel> criarProjeto(@RequestBody ProjetoModel projetoModel){
        ProjetoModel request = projetoService.criarProjeto(projetoModel);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(request.getId())
                .toUri();

        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<ProjetoModel>> listarProjeto(){
        return ResponseEntity.ok(projetoService.listarProjeto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoModel> buscarProjeto(@PathVariable Long id){
        return ResponseEntity.ok(projetoService.buscarProjeto(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id){
        projetoService.deletarProjeto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoModel> alteralProjeto(@PathVariable Long id, @RequestBody ProjetoModel projetoModel){
        return ResponseEntity.ok(projetoService.atualizarProjeto(id, projetoModel));
    }
}

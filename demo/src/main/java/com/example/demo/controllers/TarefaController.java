package com.example.demo.controllers;


import com.example.demo.models.TarefaModel;
import com.example.demo.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaModel> criarTarefa(@RequestBody TarefaModel tarefaModel) {
        TarefaModel request = tarefaService.criarTarefa(tarefaModel);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(request.getId())
                .toUri();

        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<TarefaModel>> listarTarefa(){
        return ResponseEntity.ok(tarefaService.listarTarefa());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaModel> buscarTarefa(@PathVariable Long id){
        return ResponseEntity.ok(tarefaService.buscarTarefa(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id){
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaModel> atualizarTarefa(@PathVariable Long id, @RequestBody TarefaModel tarefaModel){
        return ResponseEntity.ok(tarefaService.atualizarTarefa(id, tarefaModel));
    }
}

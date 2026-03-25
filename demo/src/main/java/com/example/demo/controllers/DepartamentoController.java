package com.example.demo.controllers;

import com.example.demo.models.DepartamentoModel;
import com.example.demo.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @PostMapping
    public ResponseEntity<DepartamentoModel> criarDepartamento(@RequestBody DepartamentoModel departamentoModel){
        DepartamentoModel request = departamentoService.criarDepartamento(departamentoModel);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(request.getId())
                .toUri();

        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoModel>> listarDepartamento(){
        return ResponseEntity.ok(departamentoService.listarDepartamento());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoModel> buscarDepartamento(@PathVariable Long id){
        DepartamentoModel departamento = departamentoService.buscarDepartamento(id);
        return ResponseEntity.ok(departamento);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deletarDepartamento(@PathVariable Long id){
        departamentoService.deletarDepartemento(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoModel> atualizarDepartamento(@PathVariable Long id, @RequestBody DepartamentoModel departamentoModel){
        DepartamentoModel atualizado = departamentoService.atualizarDepartamento(id, departamentoModel);
        return ResponseEntity.ok(atualizado);
    }

}

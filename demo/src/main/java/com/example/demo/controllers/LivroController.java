    package com.example.demo.controllers;

    import com.example.demo.models.LivroModel;
    import com.example.demo.services.LivroService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

    import java.net.URI;
    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping(path = "/livros")
    public class LivroController {

        @Autowired
        private LivroService livroService;

        @PostMapping
        public ResponseEntity<LivroModel> criarLivro(@RequestBody LivroModel livroModel){
            LivroModel request = livroService.criarLivro(livroModel);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(request.getId())
                    .toUri();

            return ResponseEntity.created(uri).body(request);
        }

        @GetMapping
        public ResponseEntity<List<LivroModel>> buscarTodosLivro(){
            List<LivroModel> request = livroService.findAll();
            return ResponseEntity.ok(request);
        }

        @DeleteMapping("/{id}")
        public void deletarLivro(@PathVariable Long id){
            livroService.deletarLivro(id);
        }

        @GetMapping("/{id}")
        public Optional<LivroModel>findById(@PathVariable Long id){
            return livroService.findById(id);
        }

        @PutMapping("/{id}")
        public LivroModel alterar(@PathVariable Long id, @RequestBody LivroModel livroModel){
            return livroService.atualizar(id, livroModel);
        }
    }

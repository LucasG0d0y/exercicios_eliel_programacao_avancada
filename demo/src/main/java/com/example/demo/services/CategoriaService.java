package com.example.demo.services;

import com.example.demo.models.CategoriaModel;
import com.example.demo.models.PedidoModel;
import com.example.demo.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaModel criarCategoria(CategoriaModel categoriaModel){
        return categoriaRepository.save(categoriaModel);
    }

    public List<CategoriaModel> listarCategoria(){
        return categoriaRepository.findAll();
    }

    public CategoriaModel buscarCategoria(Long id){
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria nao encontrada"));
    }

    public void deletarCategoria(Long id){
        if (!categoriaRepository.existsById(id)){
            throw new RuntimeException("Categoria nao encontrada");
        }
        categoriaRepository.deleteById(id);
    }

    public CategoriaModel atualizarCategoria(Long id, CategoriaModel categoriaModel) {
        CategoriaModel model = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria nao encontrada"));

        model.setNome(categoriaModel.getNome());
        model.setDescricao(categoriaModel.getDescricao());

        return categoriaRepository.save(model);
    }
}

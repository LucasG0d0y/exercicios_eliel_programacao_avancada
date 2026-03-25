package com.example.demo.services;

import com.example.demo.models.ProdutoModel;
import com.example.demo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoModel criarProduto(ProdutoModel produtoModel){
        return produtoRepository.save(produtoModel);
    }

    public List<ProdutoModel> listarProduto(){
        return produtoRepository.findAll();
    }

    public ProdutoModel buscarProduto(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void deletarProduto(Long id){
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    public ProdutoModel atualizarProduto(Long id, ProdutoModel produtoModel){
        ProdutoModel model = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        model.setNome(produtoModel.getNome());
        model.setPreco(produtoModel.getPreco());
        model.setEstoque(produtoModel.getEstoque());

        return produtoRepository.save(model);
    }
}
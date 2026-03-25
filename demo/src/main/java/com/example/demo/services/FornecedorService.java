package com.example.demo.services;

import com.example.demo.models.FornecedorModel;
import com.example.demo.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public FornecedorModel criarFornecedor(FornecedorModel fornecedorModel){
        return fornecedorRepository.save(fornecedorModel);
    }

    public List<FornecedorModel> listarFornecedor(){
        return fornecedorRepository.findAll();
    }

    public FornecedorModel buscarFornecedor(Long id){
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor nao encontrado"));
    }

    public void deletarFornecedor(Long id){
        if(fornecedorRepository.existsById(id)){
            throw  new RuntimeException("Fornecedor nao encontrado");
        }
        fornecedorRepository.deleteById(id);
    }

    public FornecedorModel atualizarFornecedor(Long id, FornecedorModel fornecedorModel){
        FornecedorModel model = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor nao encontrado"));

        model.setNomeFantasia(fornecedorModel.getNomeFantasia());
        model.setCnpj(fornecedorModel.getCnpj());
        model.setContato(fornecedorModel.getContato());

        return fornecedorRepository.save(model);
    }
}

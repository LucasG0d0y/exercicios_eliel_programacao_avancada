package com.example.demo.services;

import com.example.demo.models.AutorModel;
import com.example.demo.models.PedidoModel;
import com.example.demo.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public AutorModel criarAutor(AutorModel autorModel){
        return autorRepository.save(autorModel);
    }

    public List<AutorModel> listarAutor(){
        return autorRepository.findAll();
    }

    public AutorModel buscarAutor(Long id){
        return autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor nao encontrado"));
    }

    public void deletarAutor(Long id){
        if (!autorRepository.existsById(id)){
            throw new RuntimeException("Autor nao encontrado");
        }
        autorRepository.deleteById(id);
    }

    public AutorModel atualizarAutor(Long id, AutorModel autorModel) {
        AutorModel model = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor nao encontrado"));

        model.setNome(autorModel.getNome());
        model.setNacionalidade(autorModel.getNacionalidade());
        model.setDataNascimento(autorModel.getDataNascimento());

        return autorRepository.save(model);
    }
}

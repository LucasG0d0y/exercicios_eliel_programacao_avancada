package com.example.demo.services;

import com.example.demo.models.ProjetoModel;
import com.example.demo.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public ProjetoModel criarProjeto(ProjetoModel projetoModel){
        return projetoRepository.save(projetoModel);
    }

    public List<ProjetoModel> listarProjeto(){
        return projetoRepository.findAll();
    }

    public ProjetoModel buscarProjeto(Long id){
        return projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto nao encontrado"));
    }

    public void deletarProjeto(Long id){
        if(projetoRepository.existsById(id)){
            throw new RuntimeException("Projeto nao encontrado");
        }
        projetoRepository.deleteById(id);
    }

    public ProjetoModel atualizarProjeto(Long id, ProjetoModel projetoModel){
        ProjetoModel model = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto nao encontrado"));

        model.setNome(projetoModel.getNome());
        model.setDataFim(projetoModel.getDataFim());
        model.setDataInicio(projetoModel.getDataInicio());

        return projetoRepository.save(model);
    }

}

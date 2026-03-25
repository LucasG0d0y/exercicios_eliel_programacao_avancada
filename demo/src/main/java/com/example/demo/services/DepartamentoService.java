package com.example.demo.services;

import com.example.demo.models.DepartamentoModel;
import com.example.demo.repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public DepartamentoModel criarDepartamento(DepartamentoModel departamentoModel){
        return departamentoRepository.save(departamentoModel);
    }

    public List<DepartamentoModel> listarDepartamento(){
        return departamentoRepository.findAll();
    }

    public DepartamentoModel buscarDepartamento(Long id){
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento nao encontrado"));
    }

    public void deletarDepartemento(Long id){
        if (!departamentoRepository.existsById(id)){
            throw new RuntimeException("Departamento nao encontrado");
        }
        departamentoRepository.deleteById(id);
    }

    public DepartamentoModel atualizarDepartamento(Long id, DepartamentoModel departamentoModel){
        DepartamentoModel model = departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento nao encontrado"));

        model.setNome(departamentoModel.getNome());
        model.setLocalizacao(departamentoModel.getLocalizacao());

        return departamentoRepository.save(model);
    }
}

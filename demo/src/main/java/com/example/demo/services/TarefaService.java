package com.example.demo.services;

import com.example.demo.models.TarefaModel;
import com.example.demo.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public TarefaModel criarTarefa(TarefaModel tarefaModel){
        return tarefaRepository.save(tarefaModel);
    }

    public List<TarefaModel> listarTarefa(){
        return tarefaRepository.findAll();
    }

    public TarefaModel buscarTarefa(Long id){
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa nao encontrada"));
    }

    public void deletarTarefa(Long id){
        if(!tarefaRepository.existsById(id)){
            throw new RuntimeException("Tarefa nao encontrada");
        }
        tarefaRepository.deleteById(id);
    }

    public TarefaModel atualizarTarefa(Long id, TarefaModel tarefaModel){
        TarefaModel model = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa nao encontrada"));

        model.setDescricao(tarefaModel.getDescricao());
        model.setDataVencimento(tarefaModel.getDataVencimento());
        model.setConcluida(tarefaModel.getConcluida());

        return tarefaRepository.save(model);
    }
}

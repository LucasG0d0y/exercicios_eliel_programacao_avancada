package com.example.demo.services;

import com.example.demo.models.ClienteModel;
import com.example.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    public ClienteRepository clienteRepository;

    public ClienteModel criarCliente(ClienteModel clienteModel){
        return clienteRepository.save(clienteModel);
    }

    public List<ClienteModel> listarCliente(){
        return clienteRepository.findAll();
    }

    public ClienteModel buscarCliente(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));
    }

    public void deletarCliente (Long id){
        if (!clienteRepository.existsById(id)){
            throw new RuntimeException("Cliente nao encontrado");
        }
        clienteRepository.deleteById(id);
    }

    public ClienteModel atualizarCliente(Long id, ClienteModel clienteModel){
        ClienteModel model = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));

        model.setNome(clienteModel.getNome());
        model.setEmail(clienteModel.getEmail());
        model.setTelefone(clienteModel.getTelefone());

        return clienteRepository.save(model);
    }
}

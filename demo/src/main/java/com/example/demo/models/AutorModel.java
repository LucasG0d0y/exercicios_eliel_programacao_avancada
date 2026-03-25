package com.example.demo.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "Autor")
@Table(name = "tb_autor")
public class AutorModel {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "nome_autor")
    private String nome;
    private String nacionalidade;
    @Column (name = "data_nascimento")
    private LocalDateTime dataNascimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}

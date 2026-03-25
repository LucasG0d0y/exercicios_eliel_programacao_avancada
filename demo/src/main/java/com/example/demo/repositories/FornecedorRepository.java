package com.example.demo.repositories;

import com.example.demo.models.FornecedorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<FornecedorModel, Long> {
}

package com.example.demo.repositories;

import com.example.demo.models.ProjetoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<ProjetoModel, Long> {
}

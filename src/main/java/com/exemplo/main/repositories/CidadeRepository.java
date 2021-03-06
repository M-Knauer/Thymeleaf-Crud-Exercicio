package com.exemplo.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.main.models.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}

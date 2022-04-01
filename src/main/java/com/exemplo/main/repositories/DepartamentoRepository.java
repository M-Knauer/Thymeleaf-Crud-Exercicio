package com.exemplo.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exemplo.main.models.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
	
	@Query("select d from Departamento d where lower(d.nome) like lower(concat(:termo, '%'))")
	List<Departamento> serchByName(@Param("termo") String termo);
}

package com.rp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rp.model.Libro;

public interface LibroJpaRepository extends JpaRepository<Libro, Long> , JpaSpecificationExecutor<Libro>  {

	
}

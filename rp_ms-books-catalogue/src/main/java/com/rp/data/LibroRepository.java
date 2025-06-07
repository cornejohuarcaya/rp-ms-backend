package com.rp.data;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
 
import com.rp.data.utils.Consts;
import com.rp.data.utils.SearchCriteria;
import com.rp.data.utils.SearchOperation;
import com.rp.data.utils.SearchStatement;
import com.rp.model.Libro;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LibroRepository {
	 private final LibroJpaRepository repository;

	    public List<Libro> getLibros() {
	        return repository.findAll();
	    }

	    public Libro getById(Long id) {
	        return repository.findById(id).orElse(null);
	    }

	    public Libro save(Libro Libro) {
	        return repository.save(Libro);
	    }

	    public void delete(Libro Libro) {
	        repository.delete(Libro);
	    }

	    public List<Libro> search(String titulo, String autor, String categoria, String genero) {
	        SearchCriteria<Libro> spec = new SearchCriteria<>();

	        if (StringUtils.isNotBlank(titulo)) {
	            spec.add(new SearchStatement(Consts.TITULO, titulo, SearchOperation.MATCH));
	        }

	        if (StringUtils.isNotBlank(autor)) {
	            spec.add(new SearchStatement(Consts.AUTOR, autor, SearchOperation.MATCH));
	        }

	        if (StringUtils.isNotBlank(categoria)) {
	            spec.add(new SearchStatement(Consts.CATEGORIA, categoria, SearchOperation.EQUAL));
	        }

	        if (StringUtils.isNotBlank(genero)) {
	            spec.add(new SearchStatement(Consts.GENERO , genero, SearchOperation.EQUAL));
	        }

	        return repository.findAll(spec);
	    }

	}
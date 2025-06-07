package com.rp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rp.controller.data.CreateLibroRequest;
import com.rp.dto.LibroDto;
import com.rp.model.Libro;

 public interface LibrosService {

	List<Libro> getLibros(String titulo, String autor, String categoria, String genero);
		
	Libro getLibro(String LibroId);
	
	Boolean removeLibro(String LibroId);
	
	Libro createLibro(CreateLibroRequest request);
	Libro updateLibro(String id, String updateRequest);

	Libro updateLibro(String id, LibroDto updateRequest);

}

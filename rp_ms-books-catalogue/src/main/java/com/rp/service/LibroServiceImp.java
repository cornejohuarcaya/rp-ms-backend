package com.rp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
 
import lombok.extern.slf4j.Slf4j; 

import com.rp.controller.data.CreateLibroRequest;
import com.rp.data.LibroRepository;
import com.rp.dto.LibroDto;
import com.rp.model.Libro;
@Service
public class LibroServiceImp implements LibrosService {

	@Autowired
	LibroRepository libroRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public List<Libro> getLibros(String titulo, String autor, String categoria, String genero) {
		
		if (StringUtils.hasLength(titulo) || StringUtils.hasLength(autor) || StringUtils.hasLength(categoria) || StringUtils.hasLength(genero)
				 ) {
			return libroRepository.search(titulo, autor, categoria, genero);
		}

		List<Libro> Libros = libroRepository.getLibros();
		return Libros.isEmpty() ? null : Libros;	}

	@Override
	public Libro getLibro(String libroId) {
		return libroRepository.getById(Long.valueOf(libroId));
	}

	@Override
	public Boolean removeLibro(String libroId) {
		Libro product = libroRepository.getById(Long.valueOf(libroId));

		if (product != null) {
			libroRepository.delete(product);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Libro createLibro(CreateLibroRequest request) {
		if (request != null && StringUtils.hasLength(request.getTitulo().trim())
				&& StringUtils.hasLength(request.getAutor().trim())
				&& StringUtils.hasLength(request.getCodigoISBN().trim()) && request.getResumen() != null) {

			Libro libro = Libro.builder().autor(request.getAutor()).titulo(request.getTitulo())
					.fechaPublicacion(request.getFechaPublicacion()).categoria(request.getCategoria())
					.genero(request.getGenero()).codigoISBN(request.getCodigoISBN())
					.stock(request.getStock()).resumen(request.getResumen())
					.puntuacion(request.getPuntuacion()).habilitado(request.getHabilitado())
					.tipo(request.getTipo())
					.build();

			return libroRepository.save(libro);
		} else {
			return null;
		}
	}

	@Override
	public Libro updateLibro(String id, String request) {
		Libro product = libroRepository.getById(Long.valueOf(id));
		if (product != null) {
			try {
				JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
				JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(product)));
				Libro patched = objectMapper.treeToValue(target, Libro.class);
				libroRepository.save(patched);
				return patched;
			} catch (JsonProcessingException | JsonPatchException e) {
                return null;
            }
        } else {
			return null;
		}
	}

	@Override
	public Libro updateLibro(String id, LibroDto updateRequest) {
		Libro product = libroRepository.getById(Long.valueOf(id));
		if (product != null) {
			product.update(updateRequest);
			libroRepository.save(product);
			return product;
		} else {
			return null;
		}
	}

}

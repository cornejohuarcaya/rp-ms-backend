package com.rp.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rp.controller.data.CreateLibroRequest;
import com.rp.dto.LibroDto;
import com.rp.model.Libro;
import com.rp.service.LibrosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Libros Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre libros alojados en una base de datos H2")
public class LibrosController {

	private final LibrosService librosService;
	
	@GetMapping("/libros")
	@Operation(
            operationId = "Filtrar un libro",
            description = "Operacion de búsqueda ",
            summary = "Se busca un libro a través de varios campos")
	public ResponseEntity<List<Libro>> getLibros( @RequestHeader Map<String, String> headers,
            @Parameter(name = "titulo", description = "Título del libro. No tiene por que ser exacto", example = "Principito", required = false)
            @RequestParam(required = false) String titulo,
            @Parameter(name = "autor", description = "Autor del libro. No tiene por que ser exacto", example = "Antoine", required = false)
    		@RequestParam(required = false) String autor,
    		@Parameter(name = "categoria", description = "Categoria del libro. No tiene por que ser exacto", example = "", required = false)
			@RequestParam(required = false) String categoria,
			@Parameter(name = "genero", description = "Genero del libro. No tiene por que ser exacto", example = "", required = false)
			@RequestParam(required = false) String genero
			
			) {

        List<Libro> libros = librosService.getLibros(titulo, autor, categoria, genero);

        if (libros != null) {
            return ResponseEntity.ok(libros);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }	}
	
	 @GetMapping("/libros/{libroId}")
	    @Operation(
	            operationId = "Obtener un libro",
	            description = "Operacion de lectura",
	            summary = "Se devuelve un libroo a partir de su identificador.")
	    @ApiResponse(
	            responseCode = "200",
	            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)))
	    @ApiResponse(
	            responseCode = "404",
	            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)),
	            description = "No se ha encontrado el libro con el identificador indicado.")
	    public ResponseEntity<Libro> getlibro(@PathVariable String libroId) {

 	        Libro libro = librosService.getLibro(libroId);

	        if (libro != null) {
	            return ResponseEntity.ok(libro);
	        } else {
	            return ResponseEntity.notFound().build();
	        }

	    }

	    @DeleteMapping("/libros/{libroId}")
	    @Operation(
	            operationId = "Eliminar un libro",
	            description = "Operacion de escritura",
	            summary = "Se elimina un libroo a partir de su identificador.")
	    @ApiResponse(
	            responseCode = "200",
	            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
	    @ApiResponse(
	            responseCode = "404",
	            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
	            description = "No se ha encontrado el libro con el identificador indicado.")
	    public ResponseEntity<Void> deletelibro(@PathVariable String libroId) {

	        Boolean removed = librosService.removeLibro(libroId);

	        if (Boolean.TRUE.equals(removed)) {
	            return ResponseEntity.ok().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }

	    }

	    @PostMapping("/libros")
	    @Operation(
	            operationId = "Insertar un libroo",
	            description = "Operacion de escritura",
	            summary = "Se crea un libroo a partir de sus datos.",
	            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
	                    description = "Datos del libroo a crear.",
	                    required = true,
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateLibroRequest.class))))
	    @ApiResponse(
	            responseCode = "201",
	            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)))
	    @ApiResponse(
	            responseCode = "400",
	            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
	            description = "Datos incorrectos introducidos.")
	    @ApiResponse(
	            responseCode = "404",
	            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
	            description = "No se ha encontrado el libroo con el identificador indicado.")
	    public ResponseEntity<Libro> addlibro(@RequestBody CreateLibroRequest request) {

	        Libro createdlibro = librosService.createLibro(request);

	        if (createdlibro != null) {
	            return ResponseEntity.status(HttpStatus.CREATED).body(createdlibro);
	        } else {
	            return ResponseEntity.badRequest().build();
	        }
	    }


	    @PatchMapping("/libros/{libroId}")
	    @Operation(
	            operationId = "Modificar parcialmente un libro",
	            description = "RFC 7386. Operacion de escritura",
	            summary = "RFC 7386. Se modifica parcialmente un libro.",
	            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
	                    description = "Datos del libroo a crear.",
	                    required = true,
	                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = String.class))))
	    @ApiResponse(
	            responseCode = "200",
	            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)))
	    @ApiResponse(
	            responseCode = "400",
	            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
	            description = "libroo inválido o datos incorrectos introducidos.")
	    public ResponseEntity<Libro> patchlibro(@PathVariable String libroId, @RequestBody String patchBody) {

	        Libro patched = librosService.updateLibro(libroId, patchBody);
	        if (patched != null) {
	            return ResponseEntity.ok(patched);
	        } else {
	            return ResponseEntity.badRequest().build();
	        }
	    }


	    @PutMapping("/libros/{libroId}")
	    @Operation(
	            operationId = "Modificar totalmente un libro",
	            description = "Operacion de escritura",
	            summary = "Se modifica totalmente un libro.",
	            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
	                    description = "Datos del libroo a actualizar.",
	                    required = true,
	                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = LibroDto.class))))
	    @ApiResponse(
	            responseCode = "200",
	            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)))
	    @ApiResponse(
	            responseCode = "404",
	            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
	            description = "libroo no encontrado.")
	    public ResponseEntity<Libro> updatelibro(@PathVariable String libroId, @RequestBody LibroDto body) {

	    	Libro updated = librosService.updateLibro(libroId, body);
	        if (updated != null) {
	            return ResponseEntity.ok(updated);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

}

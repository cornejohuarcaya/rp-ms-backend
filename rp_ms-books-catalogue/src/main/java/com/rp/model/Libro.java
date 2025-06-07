package com.rp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rp.dto.LibroDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="libros")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String titulo;
	private String autor;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fechaPublicacion;
	private String categoria ;
	private String genero ;
	private String codigoISBN;
	private Integer stock;
	private String 	resumen;
	private Integer puntuacion;
	private Boolean habilitado;
	private String tipo;
	
	public void update(LibroDto libroDto) {
		this.titulo = libroDto.getTitulo();
		this.autor = libroDto.getAutor();
		this.fechaPublicacion = libroDto.getFechaPublicacion();
		this.categoria = libroDto.getCategoria();
		this.genero = libroDto.getGenero();
		this.codigoISBN = libroDto.getCodigoISBN();
		this.stock = libroDto.getStock();
		this.resumen = libroDto.getResumen();
		this.puntuacion = libroDto.getPuntuacion();
		this.habilitado = libroDto.getHabilitado();
		this.tipo = libroDto.getTipo();
	}
	
	 
	
}

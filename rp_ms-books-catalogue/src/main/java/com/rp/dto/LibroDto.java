package com.rp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LibroDto {

	private String titulo;
	private String autor;
	private Date fechaPublicacion;
	private String categoria ;
	private String genero ;
	private String codigoISBN;
	private Integer stock;
	private String 	resumen;
	private Integer puntuacion;
	private Boolean Habilitado;
	private String tipo;
}

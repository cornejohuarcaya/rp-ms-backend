package com.rp.ordenes.facade;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Libro {

	private Long id;
		private String titulo;
	private String autor;
	private Date fechaPublicacion;
	private String categoria ;
	private String genero ;
	private String codigoISBN;
	private Integer stock;
	private String 	resumen;
	private Integer puntuacion;
	private Boolean habilitado;
	private String tipo;
		 
	
}

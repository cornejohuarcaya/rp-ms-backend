package com.rp.controller.data;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLibroRequest {

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

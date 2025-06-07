package com.rp.controller.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenCompraRequest {

	private List<DetalleOrdenRequest> libros;
	private String direccion;
	private String medioPago;
	private String idCliente;
	
	@Getter
	@Setter
	public static class DetalleOrdenRequest{
		private String idLibro;
		private Integer cantidad;
		private Double precioUnitario;		
		
	}
}


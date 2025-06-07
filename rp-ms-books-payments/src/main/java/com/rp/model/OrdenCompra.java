package com.rp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "orden_compras")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenCompra {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private Long idUsuario;
    private Date fechaCompra;
    private String direccion;
	private String medioPago;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_orden" )
    private List<Detalle> detalles=new ArrayList<>();
    
 
    
    
}

package com.rp.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.controller.data.OrdenCompraRequest;
import com.rp.model.Detalle;
import com.rp.model.OrdenCompra;
import com.rp.ordenes.facade.Libro;
import com.rp.ordenes.facade.LibroFacade;
import com.rp.repository.OrdenCompraRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrdenComprasServiceImp implements OrdenComprasService {

	@Autowired
	LibroFacade librosFacade;
	
	@Autowired
	OrdenCompraRepository ordenCompraRepository;
	
	@Override
	public OrdenCompra createOrdenCompra(OrdenCompraRequest request) {
		List<Libro> libros = request.getLibros().stream().map(detalle -> librosFacade.getLibro(detalle.getIdLibro()))
			    .filter(Objects::nonNull)
			    .toList();
		log.info("Libros: " + libros);
		
		
		boolean excedeStock = request.getLibros().stream().anyMatch(pedido -> {
		    Libro libro = libros.stream()
		        .filter(l -> l.getId().equals(Long.valueOf(pedido.getIdLibro())))
		        .findFirst()
		        .orElse(null);

		    log.info("id "+pedido.getIdLibro() +  " ped: "+   pedido.getCantidad() +"  stock: "+ (libro!=null?libro.getStock(): 0)   );
		    return libro == null || pedido.getCantidad() > libro.getStock();
		});
		
	    if(libros.size() != request.getLibros().size() || libros.stream().anyMatch(product -> !product.getHabilitado()) || excedeStock	    		
	    		) {
	    	log.info("Algún libro no esta disponible "+ excedeStock  );
	      return null;
	    } else {
	      OrdenCompra order = OrdenCompra.builder()
	    		  .idUsuario(Long.valueOf(request.getIdCliente()))
	    		  .fechaCompra(new Date())
	    		  .direccion(request.getDireccion())
	    		  .medioPago(request.getMedioPago()).build();

	      List<Detalle> detalleOrden = request.getLibros().stream()
	    		    .<Detalle>map(item -> Detalle.builder()
	    		        .idLibro(item.getIdLibro()) 
	    		        .cantidad(item.getCantidad())
	    		        .precioUnitario(item.getPrecioUnitario())
	    		        .build()
	    		    )
	    		    .collect(Collectors.toList());
	    		  
	      order.setDetalles(detalleOrden);
	      ordenCompraRepository.save(order);
	      
	      //actualizando stock y bloqueando
	      request.getLibros().forEach(pedido -> {
			    Libro libro = libros.stream()
			        .filter(l -> l.getId().equals(Long.valueOf(pedido.getIdLibro())))
			        .findFirst()
			        .orElse(null);

			    if (libro!=null)
			    {
			    	int stock=libro.getStock()-pedido.getCantidad();
			    	librosFacade.updateLibro(pedido.getIdLibro(),stock  , stock<=0?false:true);
			    }
			});
	      return order;
	    }
 	}

	@Override
	public OrdenCompra getOrdenCompra(String id) {
		return ordenCompraRepository.findById(Long.valueOf(id)).orElse(null);
	}

	@Override
	public List<OrdenCompra> getOrdenes() {
		return ordenCompraRepository.findAll();
	}

}

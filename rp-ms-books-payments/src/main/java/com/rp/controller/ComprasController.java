package com.rp.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rp.controller.data.OrdenCompraRequest;
import com.rp.model.OrdenCompra;
import com.rp.service.OrdenComprasService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ComprasController {

	 private final OrdenComprasService service; //Inyeccion por constructor mediante @RequiredArgsConstructor. Y, también es inyección por interfaz.

	    @PostMapping("/ordenes")
	    public ResponseEntity<OrdenCompra> createOrder(@RequestBody @Valid OrdenCompraRequest request) { //Se valida con Jakarta Validation API

	        log.info("Creando COMPRA...");
	        OrdenCompra created = service.createOrdenCompra(request);

	        if (created != null) {
	            return ResponseEntity.ok(created);
	        } else {
	            return ResponseEntity.badRequest().build();
	        }
	    }

	    @GetMapping("/ordenes")
	    public ResponseEntity<List<OrdenCompra>> getOrders() {

	        List<OrdenCompra> orders = service.getOrdenes();
	        if (orders != null) {
	            return ResponseEntity.ok(orders);
	        } else {
	            return ResponseEntity.ok(Collections.emptyList());
	        }
	    }

	    @GetMapping("/ordenes/{id}")
	    public ResponseEntity<OrdenCompra> getOrder(@PathVariable String id) {

	    	OrdenCompra order = service.getOrdenCompra(id);
	        if (order != null) {
	            return ResponseEntity.ok(order);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}

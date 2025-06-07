package com.rp.service;

import java.util.List;

import com.rp.controller.data.OrdenCompraRequest;
import com.rp.model.OrdenCompra;

public interface OrdenComprasService {

	OrdenCompra createOrdenCompra(OrdenCompraRequest request);

	OrdenCompra getOrdenCompra(String id);

	List<OrdenCompra> getOrdenes();
}

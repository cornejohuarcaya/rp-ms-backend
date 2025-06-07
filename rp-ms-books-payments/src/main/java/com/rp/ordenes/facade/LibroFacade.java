package com.rp.ordenes.facade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class LibroFacade {

	 @Value("${getLibro.url}")
	 private String getProductUrl;
	 
	 private final String pathBuscar="/libros/%s"; //se separa para usos adicionales
	 private final String pathUpdate="/libros/%s"; //se separa para usos adicionales

	private final WebClient.Builder webClient;

	  public Libro getLibro(String id) {

	    try {
	      String url = String.format(getProductUrl+pathBuscar, id);
	      log.info("Getting libro with ID {}. Request to {}", id, url);
	      return webClient.build()
	              .get()
	              .uri(url)
	              .retrieve()
	              .bodyToMono(Libro.class)
	              .block();
	    } catch (HttpClientErrorException e) {
	      log.error("Client Error: {}, Libro with ID {}", e.getStatusCode(), id);
	      return null;
	    } catch (HttpServerErrorException e) {
	      log.error("Server Error: {}, Libro with ID {}", e.getStatusCode(), id);
	      return null;
	    } catch (Exception e) {
	      log.error("Error: {}, Libro with ID {}", e.getMessage(), id);
	      return null;
	    }
	  }
	  public Libro updateLibro(String id, Integer stock, Boolean habilitado) {

		    try {
		    	Map<String, Object> camposActualizados = new HashMap();
		    	camposActualizados.put("habilitado", habilitado);
		    	camposActualizados.put("stock", stock);
		    	
		      String url = String.format(getProductUrl+pathUpdate, id);
		      log.info("Getting libro with ID {}. Request to {}", id, url);
		      return webClient.build()
		              .patch()
		              .uri(url)
		              .bodyValue(camposActualizados)
		              .retrieve()
		              .bodyToMono(Libro.class)
		              .block();
		    } catch (HttpClientErrorException e) {
		      log.error("Client Error: {}, Libro with ID {}", e.getStatusCode(), id);
		      return null;
		    } catch (HttpServerErrorException e) {
		      log.error("Server Error: {}, Libro with ID {}", e.getStatusCode(), id);
		      return null;
		    } catch (Exception e) {
		      log.error("Error: {}, Libro with ID {}", e.getMessage(), id);
		      return null;
		    }
		  }
}

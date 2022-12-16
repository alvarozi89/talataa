package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entidad.Movies;

@RestController
public class MovieController {
private final RestTemplate restTemplate;

@Autowired
public MovieController(RestTemplate restTemplate) {
	this.restTemplate=restTemplate;
}

@GetMapping("/movies")
public Object getApi() {
	String url = "https://api.themoviedb.org/3/movie/18/lists?api_key=b7a5c39d7e19ecb80395e46a6f4a7cc7&language=en-US&page=1";
	return restTemplate.getForObject(url, Object.class);
}

@GetMapping("/movies/{id}")
public Object getApiId(@PathVariable int id) {
	String url = "https://api.themoviedb.org/3/movie/18?api_key=b7a5c39d7e19ecb80395e46a6f4a7cc7&language=en-US";
	System.out.print(id);
	return restTemplate.getForObject(url, Object.class);
}


@PostMapping("/movies/{name},{description},{language}")
public Object postApi(@PathVariable String name, String description, String language) {
	String url = "https://api.themoviedb.org/3/list?api_key=b7a5c39d7e19ecb80395e46a6f4a7cc7";
	return restTemplate.getForObject(url, Object.class);
}

@PostMapping("/movies")
public ResponseEntity<?> create(@RequestBody Movies movies){
	String url = "https://api.themoviedb.org/3/list?api_key=b7a5c39d7e19ecb80395e46a6f4a7cc7";
	Map<String,Object> response = new HashMap<>();
	return new  ResponseEntity<Map>(response, HttpStatus.CREATED);		
}

@PutMapping("/movies/{id}")
public ResponseEntity<?> update(@RequestBody Movies movies, @PathVariable Long id) {
	String url = "https://api.themoviedb.org/3/list?api_key=b7a5c39d7e19ecb80395e46a6f4a7cc7";
	Movies movieActual = null;
   Map<String,Object> response = new HashMap<>();

try {
	movieActual.setDescription(movies.getDescription());
	movieActual.setLanguage(movies.getLanguage());
	movieActual.setName(movies.getName());

	
} catch (Exception e) {
	response.put("Mensaje", "Error en al actualizar");
	response.put("Error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
	return new  ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
}

response.put("mensaje", "la pelicula ha sido actualizado con exito");


return new  ResponseEntity<Map>(response, HttpStatus.CREATED);
}

@DeleteMapping("/clientes/{id}")
public ResponseEntity<?> delete(@PathVariable Long id) {
	String url = "https://api.themoviedb.org/3/list?api_key=b7a5c39d7e19ecb80395e46a6f4a7cc7";
	Map<String,Object> response = new HashMap<>();
	try {
	
		
	} catch (Exception e) {
		response.put("Mensaje", "Error en eliminar");
		response.put("Error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
		return new  ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	response.put("mensaje", "la pelicula ha sido eliminada con exito");
	return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	
}



}

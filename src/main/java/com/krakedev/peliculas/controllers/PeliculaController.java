package com.krakedev.peliculas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.peliculas.entidades.Pelicula;
import com.krakedev.peliculas.services.ServicioPelicula;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

	private final ServicioPelicula servicio;

	public PeliculaController(ServicioPelicula servicio) {
		this.servicio = servicio;
	}

	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Pelicula pelicula) {
		try {
			Pelicula creado = servicio.crear(pelicula);
			return ResponseEntity.status(HttpStatus.CREATED).body(creado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la película");
		}
	}

	@GetMapping
	public ResponseEntity<?> listar() {
		try {
			List<Pelicula> peliculas = servicio.listar();
			return ResponseEntity.ok(peliculas);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar las películas");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		try {
			Pelicula encontrado = servicio.buscarPorId(id);

			if (encontrado == null) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(encontrado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar la película por id");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Pelicula pelicula) {
		try {
			Pelicula actualizado = servicio.actualizar(id, pelicula);
			
			if(actualizado == null) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(actualizado);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la película");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		try {
			boolean eliminar = servicio.eliminar(id);
			if(!eliminar) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok("Se eliminó la película");
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la película");
		}
	}
	
	@GetMapping("/genero")
	public ResponseEntity<?> buscarPorGenero(@RequestParam(required=false) String genero){
		try {
			List<Pelicula> peliculas = new ArrayList<Pelicula>();
			
			if(genero != null) {
				peliculas = servicio.buscarPorGenero(genero);
			}
			
			if (peliculas.size() <= 0) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(peliculas);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar la película por género");
		}
	}
	
	@GetMapping("/disponible")
	public ResponseEntity<?> buscarPorDisponibilidad(@RequestParam(required=false) Boolean disponible){//Boolean con b mayúscula nos permite aceptar null
		try {
			List<Pelicula> peliculas = new ArrayList<Pelicula>();
			
			if(disponible != null) {
				peliculas = servicio.buscarPorDisponible(disponible);
			}

			if (peliculas.size() <= 0) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(peliculas);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar la película por disponibilidad");
		}
	}
}

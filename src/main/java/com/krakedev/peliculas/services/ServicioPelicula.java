package com.krakedev.peliculas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.peliculas.entidades.Pelicula;
import com.krakedev.peliculas.repository.PeliculaRepository;

@Service
public class ServicioPelicula {
	public final PeliculaRepository peliculaRepository;

	public ServicioPelicula(PeliculaRepository peliculaRepository) {
		this.peliculaRepository = peliculaRepository;
	}

	public Pelicula crear(Pelicula pelicula) {
		return peliculaRepository.save(pelicula);// Si existe id lo actualiza. Caso contrario lo crea
	}

	public List<Pelicula> listar() {
		return peliculaRepository.findAll();
	}

	public Pelicula buscarPorId(Long id) {
		Optional<Pelicula> resultado = peliculaRepository.findById(id);// Acepta una película o acepta null. Si hay un
																		// error al buscar por id le decimos que nos
																		// devuelva null

		return resultado.orElse(null);
	}

	public Pelicula actualizar(Long id, Pelicula pelicula) {
		Pelicula encontrado = buscarPorId(id);

		if (encontrado == null) {
			return null;
		}
		encontrado.setNombre(pelicula.getNombre());
		encontrado.setDirector(pelicula.getDirector());
		encontrado.setGenero(pelicula.getGenero());
		encontrado.setDuracion(pelicula.getDuracion());
		encontrado.setDisponible(pelicula.isDisponible());

		return peliculaRepository.save(encontrado);// Recibe a encontrado pporque si recibe, porque si solo llamamos al
													// save va a crear otra porque no existe
	}
	
	public boolean eliminar(Long id) {
		Pelicula encontrado = buscarPorId(id);
		
		if(encontrado == null) {
			return false;
		}
		
		peliculaRepository.delete(encontrado);
		return true;
	}
	
	public List<Pelicula> buscarPorGenero(String genero, Pelicula pelicula ){
		return peliculaRepository.findByGenero(genero);
	}
	
	public List<Pelicula> buscarPorDisponible(boolean disponible, Pelicula pelicula ){
		return peliculaRepository.findByDisponible(disponible);
	}
}

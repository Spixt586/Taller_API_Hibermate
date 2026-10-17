package com.krakedev.peliculas.services;

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
		return peliculaRepository.save(pelicula);
	}
}

package com.krakedev.peliculas.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.krakedev.peliculas.entidades.*;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{//Es una interface porque solo se pondrán las firmas de los métodos
	
	List<Pelicula> findByGenero(String genero);
	
	List<Pelicula> findByDisponible(boolean disponible);
}

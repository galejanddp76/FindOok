package com.dawes.servicio;

import java.time.LocalDate;


import java.util.List;
import java.util.Optional;

import com.dawes.modelo.PublicacionVO;

public interface ServicioPublicacion {

	<S extends PublicacionVO> S save(S entity);

	<S extends PublicacionVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<PublicacionVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<PublicacionVO> findAll();

	Iterable<PublicacionVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(PublicacionVO entity);

	void deleteAll(Iterable<? extends PublicacionVO> entities);

	void deleteAll();
	
	List<PublicacionVO> findByTitulo(String titulo);
	
	void eliminarComentarioPublicacion(PublicacionVO publicacion);
	
	List<PublicacionVO> findByfechacreacionBetween(LocalDate fecha1, LocalDate fecha2);

}
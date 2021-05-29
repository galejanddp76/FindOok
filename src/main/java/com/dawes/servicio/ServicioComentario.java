package com.dawes.servicio;

import java.util.List;


import java.util.Optional;

import com.dawes.modelo.ComentarioVO;
import com.dawes.modelo.PublicacionVO;

public interface ServicioComentario {

	<S extends ComentarioVO> S save(S entity);

	<S extends ComentarioVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<ComentarioVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<ComentarioVO> findAll();

	Iterable<ComentarioVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(ComentarioVO entity);

	void deleteAll(Iterable<? extends ComentarioVO> entities);

	void deleteAll();

	List<ComentarioVO> findByPublicacion(PublicacionVO publicacion);
	

}
package com.dawes.servicio;

import java.util.List;
import java.util.Optional;

import com.dawes.modelo.PublicacionVO;
import com.dawes.modelo.UsuarioVO;

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
	
	void eliminarOfertaPublicacion(PublicacionVO publicacion);
	
	List<PublicacionVO> findByUsuario(UsuarioVO usuario);

}
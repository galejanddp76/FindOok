package com.dawes.servicio;

import java.util.List;
import java.util.Optional;

import com.dawes.modelo.OfertaVO;
import com.dawes.modelo.UsuarioVO;

public interface ServicioOferta {

	<S extends OfertaVO> S save(S entity);

	<S extends OfertaVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<OfertaVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<OfertaVO> findAll();

	Iterable<OfertaVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(OfertaVO entity);

	void deleteAll(Iterable<? extends OfertaVO> entities);

	void deleteAll();
	
	List<OfertaVO> findByUsuario(UsuarioVO usuario);

}
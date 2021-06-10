package com.dawes.servicioImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.modelo.OfertaVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.repositorio.OfertaRepositorio;
import com.dawes.servicio.ServicioOferta;


@Service
public class ServicioOfertaImpl implements ServicioOferta {

	@Autowired
	OfertaRepositorio or;

	@Override
	public <S extends OfertaVO> S save(S entity) {
		return or.save(entity);
	}

	@Override
	public <S extends OfertaVO> Iterable<S> saveAll(Iterable<S> entities) {
		return or.saveAll(entities);
	}

	@Override
	public Optional<OfertaVO> findById(Integer id) {
		return or.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return or.existsById(id);
	}

	@Override
	public Iterable<OfertaVO> findAll() {
		return or.findAll();
	}

	@Override
	public Iterable<OfertaVO> findAllById(Iterable<Integer> ids) {
		return or.findAllById(ids);
	}

	@Override
	public long count() {
		return or.count();
	}

	@Override
	public void deleteById(Integer id) {
		or.deleteById(id);
	}

	@Override
	public void delete(OfertaVO entity) {
		or.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends OfertaVO> entities) {
		or.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		or.deleteAll();
	}

	public List<OfertaVO> findByUsuario(UsuarioVO usuario) {
		return or.findByUsuario(usuario);
	}
	
	
}

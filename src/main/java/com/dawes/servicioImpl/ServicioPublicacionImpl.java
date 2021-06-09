package com.dawes.servicioImpl;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.modelo.PublicacionVO;
import com.dawes.repositorio.PublicacionRepositorio;
import com.dawes.servicio.ServicioPublicacion;

@Service
public class ServicioPublicacionImpl implements ServicioPublicacion {

	@Autowired
	PublicacionRepositorio pr;
	

	@Override
	public <S extends PublicacionVO> S save(S entity) {
		return pr.save(entity);
	}

	@Override
	public <S extends PublicacionVO> Iterable<S> saveAll(Iterable<S> entities) {
		return pr.saveAll(entities);
	}

	@Override
	public Optional<PublicacionVO> findById(Integer id) {
		return pr.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return pr.existsById(id);
	}

	@Override
	public Iterable<PublicacionVO> findAll() {
		return pr.findAll();
	}

	@Override
	public Iterable<PublicacionVO> findAllById(Iterable<Integer> ids) {
		return pr.findAllById(ids);
	}

	@Override
	public long count() {
		return pr.count();
	}
	@Override
	public void deleteById(Integer id) {
		pr.deleteById(id);
	}

	@Override
	public void delete(PublicacionVO entity) {
		pr.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends PublicacionVO> entities) {
		pr.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		pr.deleteAll();
	}

	public List<PublicacionVO> findByTitulo(String titulo) {
		return pr.findByTitulo(titulo);
	}



	public List<PublicacionVO> findByUsuario(int idusuario) {
		return pr.findByUsuario(idusuario);
	}

	public void eliminarComentarioPublicacion(PublicacionVO publicacion) {
		pr.eliminarComentarioPublicacion(publicacion);
	}

	
}

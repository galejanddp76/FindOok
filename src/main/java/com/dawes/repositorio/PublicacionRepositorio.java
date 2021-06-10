package com.dawes.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dawes.modelo.PublicacionVO;
import com.dawes.modelo.UsuarioVO;

@Repository
public interface PublicacionRepositorio extends CrudRepository<PublicacionVO, Integer>{
	
	@Query("select p from PublicacionVO p where p.titulo like %?1%")
	List<PublicacionVO> findByTitulo(String titulo);
	
	List<PublicacionVO> findByUsuario(UsuarioVO usuario);
	
	@Modifying
	@Transactional
	@Query("delete from ComentarioVO where publicacion=:publicacion")
	void eliminarComentarioPublicacion(PublicacionVO publicacion);
	
	@Modifying
	@Transactional
	@Query("delete from OfertaVO where publicacion=:publicacion")
	void eliminarOfertaPublicacion(PublicacionVO publicacion);
}

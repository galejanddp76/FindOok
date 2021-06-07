package com.dawes.repositorio;


import java.time.LocalDate;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dawes.modelo.PublicacionVO;

@Repository
public interface PublicacionRepositorio extends CrudRepository<PublicacionVO, Integer>{
	
	@Query("select p from PublicacionVO p where p.titulo like %?1%")
	List<PublicacionVO> findByTitulo(String titulo);
	
	List<PublicacionVO> findByfechacreacionBetween(LocalDate fecha1, LocalDate fecha2);
	
	@Modifying
	@Transactional
	@Query("delete from ComentarioVO where publicacion=:publicacion")
	void eliminarComentarioPublicacion(PublicacionVO publicacion);
}

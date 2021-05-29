package com.dawes.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.ComentarioVO;
import com.dawes.modelo.PublicacionVO;

@Repository
public interface ComentarioRepositorio extends CrudRepository<ComentarioVO, Integer>{
	
	List<ComentarioVO> findByPublicacion(PublicacionVO publicacion);

}

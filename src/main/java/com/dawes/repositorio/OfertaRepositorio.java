package com.dawes.repositorio;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.OfertaVO;
import com.dawes.modelo.UsuarioVO;

@Repository
public interface OfertaRepositorio extends CrudRepository<OfertaVO, Integer>{

	List<OfertaVO> findByUsuario(UsuarioVO usuario);
}

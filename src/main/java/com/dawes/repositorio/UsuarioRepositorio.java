package com.dawes.repositorio;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.UsuarioVO;

@Repository
public interface UsuarioRepositorio extends CrudRepository<UsuarioVO, Integer>{
	
	Optional<UsuarioVO> findByUsername(String username);

}

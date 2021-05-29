package com.dawes.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.RolVO;
import com.dawes.modelo.UsuarioRolVO;
import com.dawes.modelo.UsuarioVO;

@Repository
public interface UsuarioRolRepositorio extends CrudRepository<UsuarioRolVO, Integer>{

	List<UsuarioRolVO> findByRol(RolVO rol);
	List<UsuarioRolVO> findByUsuario(UsuarioVO usuario);
}

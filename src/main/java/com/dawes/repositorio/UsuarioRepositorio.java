package com.dawes.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dawes.modelo.UsuarioVO;

@Repository
public interface UsuarioRepositorio extends CrudRepository<UsuarioVO, Integer>{
	
	Optional<UsuarioVO> findByUsername(String username);
	Optional<UsuarioVO> findByCorreo(String email);
	
	@Modifying
	@Transactional
	@Query("delete from UsuarioRolVO where usuario=:usuario")
	void eliminarUsuarioRol(UsuarioVO usuario);
	
	@Modifying
	@Transactional
	@Query("delete from PublicacionVO where usuario=:usuario")
	void eliminarPublicacionUsuario(UsuarioVO usuario);
}

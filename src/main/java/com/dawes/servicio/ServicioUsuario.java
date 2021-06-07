package com.dawes.servicio;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dawes.modelo.UsuarioVO;

public interface ServicioUsuario {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	Optional<UsuarioVO> findByUsername(String username);

	<S extends UsuarioVO> S save(S entity);

	<S extends UsuarioVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<UsuarioVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<UsuarioVO> findAll();

	Iterable<UsuarioVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(UsuarioVO entity);

	void deleteAll(Iterable<? extends UsuarioVO> entities);

	void deleteAll();
	
	UsuarioVO createUser(UsuarioVO usuario) throws Exception;
	
	boolean checkPasswordValid(UsuarioVO usuario) throws Exception;
	
	void eliminarUsuarioRol(UsuarioVO usuario);
	
	void eliminarPublicacionUsuario(UsuarioVO usuario);

}
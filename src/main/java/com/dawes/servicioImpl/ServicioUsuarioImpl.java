package com.dawes.servicioImpl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dawes.modelo.UsuarioVO;
import com.dawes.repositorio.UsuarioRepositorio;
import com.dawes.servicio.ServicioUsuario;

@Service
public class ServicioUsuarioImpl implements UserDetailsService, ServicioUsuario {
	
	@Autowired
	UsuarioRepositorio ur;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return ur.findByUsername(username).get();
	}

	@Override
	public Optional<UsuarioVO> findByUsername(String username) {
		return ur.findByUsername(username);
	}

	@Override
	public <S extends UsuarioVO> S save(S entity) {
		return ur.save(entity);
	}


	@Override
	public <S extends UsuarioVO> Iterable<S> saveAll(Iterable<S> entities) {
		return ur.saveAll(entities);
	}


	@Override
	public Optional<UsuarioVO> findById(Integer id) {
		return ur.findById(id);
	}


	@Override
	public boolean existsById(Integer id) {
		return ur.existsById(id);
	}


	@Override
	public Iterable<UsuarioVO> findAll() {
		return ur.findAll();
	}


	@Override
	public Iterable<UsuarioVO> findAllById(Iterable<Integer> ids) {
		return ur.findAllById(ids);
	}


	@Override
	public long count() {
		return ur.count();
	}


	@Override
	public void deleteById(Integer id) {
		ur.deleteById(id);
	}

	@Override
	public void delete(UsuarioVO entity) {
		ur.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends UsuarioVO> entities) {
		ur.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		ur.deleteAll();
	}
	
	public Optional<UsuarioVO> findByCorreo(String email) {
		return ur.findByCorreo(email);
	}

	private boolean checkUsernameAvailable(UsuarioVO usuario) throws Exception {
		Optional<UsuarioVO> userFound = ur.findByUsername(usuario.getUsername());
		if (userFound.isPresent()) {
			throw new Exception("El nombre de usuario no esta disponible");
		}
		return true;
	}
	
	private boolean checkEmailAvailable(UsuarioVO usuario) throws Exception {
		Optional<UsuarioVO> EmailFound = ur.findByCorreo(usuario.getCorreo());
		if (EmailFound.isPresent()) {
			throw new Exception("El correo no esta disponible");
		}
		return true;
	}

	private boolean checkPasswordValid(UsuarioVO usuario) throws Exception {
		if ( !usuario.getPassword().equals(usuario.getConfirmarpassword())) {
			throw new Exception("Las contraseñas no coinciden");
		}
		return true;
	}


	@Override
	public UsuarioVO createUser(UsuarioVO usuario) throws Exception {
		if (checkUsernameAvailable(usuario) && checkPasswordValid(usuario) && checkEmailAvailable(usuario)) {
			BCryptPasswordEncoder encriptador=new BCryptPasswordEncoder();
			String contraseña = encriptador.encode(usuario.getPassword());
			usuario.setPassword(contraseña);
			usuario.setFecharegistro(LocalDate.now());
			usuario = ur.save(usuario);
		}
		return usuario;
	}

	public void eliminarUsuarioRol(UsuarioVO usuario) {
		ur.eliminarUsuarioRol(usuario);
	}

	public void eliminarPublicacionUsuario(UsuarioVO usuario) {
		ur.eliminarPublicacionUsuario(usuario);
	}
	
	
	
}

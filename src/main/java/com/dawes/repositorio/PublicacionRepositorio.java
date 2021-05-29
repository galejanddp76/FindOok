package com.dawes.repositorio;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.PublicacionVO;
import com.dawes.modelo.UsuarioVO;

@Repository
public interface PublicacionRepositorio extends CrudRepository<PublicacionVO, Integer>{
	
	Optional<PublicacionVO> findByTitulo(String titulo);
	
	List<PublicacionVO> findByfechacreacionBetween(LocalDate fecha1, LocalDate fecha2);
	
	List<PublicacionVO> findByUsuario(UsuarioVO usuario);
}

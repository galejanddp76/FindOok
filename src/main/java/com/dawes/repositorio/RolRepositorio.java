package com.dawes.repositorio;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.RolVO;

@Repository
public interface RolRepositorio extends CrudRepository<RolVO, Integer>{
	Optional<RolVO> findByNombrerol(String rol);

}

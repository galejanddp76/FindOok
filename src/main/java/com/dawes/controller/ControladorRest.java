package com.dawes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.modelo.PublicacionVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.servicio.ServicioPublicacion;
import com.dawes.servicio.ServicioUsuario;

@RestController
public class ControladorRest {
	
	@Autowired
	ServicioPublicacion sp;
	@Autowired
	ServicioUsuario su;
	
	@GetMapping("/publicacionesJson")
	 public ResponseEntity<?> todasLasPublicaciones(){
		 List<PublicacionVO> lista=(List<PublicacionVO>) sp.findAll();
		 if (lista.isEmpty()) 
			 return ResponseEntity.notFound().build();
		 else 
			 return ResponseEntity.ok(lista);
	 }
	
	 @GetMapping("/usuariosJson")
	 public ResponseEntity<?> panel(){
		 List<UsuarioVO> lista=(List<UsuarioVO>) su.findAll();
		 if (lista.isEmpty()) return ResponseEntity.notFound().build();
		 else return ResponseEntity.ok(lista);
	 }

}

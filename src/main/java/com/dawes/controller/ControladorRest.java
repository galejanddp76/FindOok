package com.dawes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.modelo.PublicacionVO;
import com.dawes.servicio.ServicioPublicacion;

@RestController
public class ControladorRest {
	
	@Autowired
	ServicioPublicacion sp;
	
	@GetMapping("/publicaciones")
	 public ResponseEntity<?> todasLasPublicaciones(){
		 List<PublicacionVO> lista=(List<PublicacionVO>) sp.findAll();
		 if (lista.isEmpty()) 
			 return ResponseEntity.notFound().build();
		 else 
			 return ResponseEntity.ok(lista);
	 }

}

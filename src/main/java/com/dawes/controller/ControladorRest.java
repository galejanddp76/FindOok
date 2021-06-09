package com.dawes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.modelo.ComentarioVO;
import com.dawes.modelo.PublicacionVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.servicio.ServicioComentario;
import com.dawes.servicio.ServicioPublicacion;
import com.dawes.servicio.ServicioUsuario;

@RestController
public class ControladorRest {
	
	@Autowired
	ServicioPublicacion sp;
	@Autowired
	ServicioUsuario su;
	@Autowired
	ServicioComentario sc;
	
	@GetMapping("/publicacionesJson")
	 public ResponseEntity<?> todasLasPublicaciones(){
		 List<PublicacionVO> lista=(List<PublicacionVO>) sp.findAll();
		 if (lista.isEmpty()) 
			 return ResponseEntity.notFound().build();
		 else 
			 return ResponseEntity.ok(lista);
	 }
	
	 @GetMapping("/publicacionesJson/id/{idpublicacion}")
	 public ResponseEntity<?> findbyId(@PathVariable int idpublicacion) {
		 Optional<PublicacionVO> publicacion=sp.findById(idpublicacion);
		 if (publicacion.isPresent()) return ResponseEntity.ok(publicacion.get());
		 else return ResponseEntity.notFound().build();
	 }
	 
	 @GetMapping("/publicacionesJson/{titulo}")
	 public ResponseEntity<?> buscar(@PathVariable String titulo) {
		 List<PublicacionVO> lista=sp.findByTitulo(titulo);
		 if (lista.isEmpty()) 
			 return ResponseEntity.notFound().build();
		 else 
			 return ResponseEntity.ok(lista);
	 }
	 
	 @GetMapping("/publicacionesJson/usuario/{idusuario}")
	 public ResponseEntity<?> publicacionUsuario(@PathVariable int idusuario) {
		 List<PublicacionVO> lista=sp.findByUsuario(idusuario);
		 if (lista.isEmpty()) 
			 return ResponseEntity.notFound().build();
		 else 
			 return ResponseEntity.ok(lista);
	 }
	 
	 @GetMapping("/usuariosJson")
	 public ResponseEntity<?> todosLosUsuarios(){
		 List<UsuarioVO> lista=(List<UsuarioVO>) su.findAll();
		 if (lista.isEmpty()) return ResponseEntity.notFound().build();
		 else return ResponseEntity.ok(lista);
	 }
	 
	 @GetMapping("/usuariosJson/id/{idusuario}")
	 public ResponseEntity<?> findbyIdusuario(@PathVariable int idusuario) {
		 Optional<UsuarioVO> usuario = su.findById(idusuario);
		 if (usuario.isPresent()) return ResponseEntity.ok(usuario.get());
		 else return ResponseEntity.notFound().build();
	 }
	 
	 @GetMapping("/comentariosJson/id/{idpublicacion}")
	 public ResponseEntity<?> todosLosComentariosDeUnaPublicacion(@PathVariable int idpublicacion){
		 List<ComentarioVO> comentarios=sc.findByPublicacion(sp.findById(idpublicacion).get());
		 if (comentarios.isEmpty()) return ResponseEntity.notFound().build();
		 else return ResponseEntity.ok(comentarios);
	 }
}

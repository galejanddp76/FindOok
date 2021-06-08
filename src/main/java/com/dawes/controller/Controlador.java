package com.dawes.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dawes.modelo.ComentarioVO;
import com.dawes.modelo.PublicacionVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.servicio.ServicioCloudinary;
import com.dawes.servicio.ServicioComentario;
import com.dawes.servicio.ServicioPublicacion;
import com.dawes.servicio.ServicioRol;
import com.dawes.servicio.ServicioUsuario;
import com.dawes.servicio.ServicioUsuarioRol;

@Controller
public class Controlador {
	@Autowired
	ServicioPublicacion sp;
	@Autowired
	ServicioCloudinary sc;
	@Autowired
	ServicioUsuario su;
	@Autowired
	ServicioUsuarioRol sur;
	@Autowired
	ServicioRol sr;
	@Autowired
	ServicioComentario sco;
	
	
	
	@GetMapping("/insertar")
	public String insertar(Model modelo) {
		modelo.addAttribute("publicaciones", new PublicacionVO());
		return "publicacion/insertar";
	}
	
	 @PostMapping("/persistirpublicacion")
	 public String insertar(@RequestParam(name = "file", required = false) MultipartFile file, PublicacionVO publicacion) throws IOException {
		 //Llama servicio cloudinary para subir la imagen
		 try {
		 Map<?, ?> result = sc.upload(file);
		 //Establece url de la imagen de cloudinary para mostrarla
		 publicacion.setImagenpublicacion((String)result.get("url"));
		 //Si el usuario no pone ninguna imagen
		 }catch (FileNotFoundException e) {
		    publicacion.setImagenpublicacion("/images/logo_small.png");
		}
		 //Establece fecha de hoy
		 publicacion.setFechacreacion(LocalDate.now());
		 //obtener usuario logueado
		 Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		    UsuarioVO usuario = su.findByUsername(userDetail.getUsername()).get();
		    publicacion.setUsuario(usuario);
		    sp.save(publicacion);
		 return "redirect:/index";
	 }
	 
		@GetMapping("/verPublicacion")
		public String ver(@RequestParam int idpublicacion,Model modelo) {
			ComentarioVO comentario = new ComentarioVO();
			comentario.setPublicacion(sp.findById(idpublicacion).get());
			modelo.addAttribute("comentarios", comentario);
			return "publicacion/verPublicacion";
		}
		
		@PostMapping("/comentar")
		public String comentar(@ModelAttribute("comentario") ComentarioVO comentario,Model modelo) {
			//Establece fecha de hoy
			 comentario.setFecha(LocalDate.now());
			//obtener usuario logueado
			 Authentication auth = SecurityContextHolder
			            .getContext()
			            .getAuthentication();
			    UserDetails userDetail = (UserDetails) auth.getPrincipal();
			    UsuarioVO usuario = su.findByUsername(userDetail.getUsername()).get();
			    comentario.setUsuario(usuario);
			    sco.save(comentario);
			return "redirect:/verPublicacion?idpublicacion="+comentario.getPublicacion().getIdpublicacion();
		}
		
		
	 
		@GetMapping("/editarusuario")
		public String editarusuario(@RequestParam int idusuario,Model modelo) {
			UsuarioVO usuario = su.findById(idusuario).get();
			modelo.addAttribute("usuario", usuario);
			return "usuario/editarUsuario";
		}
		
		@PostMapping("/editarusuario")
		public String persistir(@Valid @ModelAttribute("usuario") UsuarioVO usuario,BindingResult result,Model model) {
			if(result.hasErrors()) {
					return "usuario/editarUsuario";
				}else {
					try {
						su.checkPasswordValid(usuario);
						BCryptPasswordEncoder encriptador=new BCryptPasswordEncoder();
						String contraseña = encriptador.encode(usuario.getPassword());
						usuario.setPassword(contraseña);
						su.save(usuario);
						return "redirect:/panel";
					} catch (Exception e) {
						model.addAttribute("Error",e.getMessage());
						return "usuario/editarUsuario";
				}
			}
		}
		
	 @GetMapping("/eliminarusuario")
		public String eliminarusuario(@RequestParam int idusuario,Model modelo) {
			UsuarioVO usuario = su.findById(idusuario).get();
			su.eliminarUsuarioRol(usuario);
			su.eliminarPublicacionUsuario(usuario);
			su.deleteById(idusuario);
			return "redirect:/panel";
		}
	 
		@GetMapping("/editarpublicacion")
		public String editarpublicacion(@RequestParam int idpublicacion,Model modelo) {
			PublicacionVO publicacion = sp.findById(idpublicacion).get();
			modelo.addAttribute("publicacion", publicacion);
			return "publicacion/editarPublicacion";
		}
		
		 @PostMapping("/editarpublicacion")
		 public String modificar(@RequestParam(name = "file", required = false) MultipartFile file,  @ModelAttribute PublicacionVO publicacion) throws IOException {
			 //Llama servicio cloudinary para subir la imagen
			 try {
			 Map<?, ?> result = sc.upload(file);
			 //Establece url de la imagen de cloudinary para mostrarla
			 publicacion.setImagenpublicacion((String)result.get("url"));
			 //Si el usuario no pone ninguna imagen
			 }catch (FileNotFoundException e) {
			    publicacion.setImagenpublicacion(publicacion.getImagenpublicacion());
			}
			    sp.save(publicacion);
			 return "redirect:/panel";
		 }
		
		 @GetMapping("/eliminarpublicacion")
			public String eliminarpublicacion(@RequestParam int idpublicacion,Model modelo) {
			 PublicacionVO publicacion = sp.findById(idpublicacion).get();
				sp.eliminarComentarioPublicacion(publicacion);
				sp.deleteById(idpublicacion);
				return "redirect:/panel";
			}
}

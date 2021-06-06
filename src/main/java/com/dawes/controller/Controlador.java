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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dawes.modelo.PublicacionVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.servicio.ServicioCloudinary;
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
		 return "index";
	 }
	 
		@GetMapping("/modificaradmin")
		public String modificar(@RequestParam int idusuario,Model modelo) {
			UsuarioVO usuario = su.findById(idusuario).get();
			modelo.addAttribute("usuario", usuario);
			return "admin/editarUsuario";
		}
		
		@PostMapping("/editarusuario")
		public String persistir(@Valid @ModelAttribute("usuario") UsuarioVO usuario, BindingResult result,Model model) {
			//validacion de los campos del formulario
			if(result.hasErrors()) {
				return "admin/editarUsuario";
			}else {
				try {
					su.save(usuario);
					return "admin/panel";
				} catch (Exception e) {
					model.addAttribute("Error",e.getMessage());
					return "admin/editarUsuario";
				}
			}
		}

	 
	 @GetMapping("/eliminaradmin")
		public String eliminar(@RequestParam int idusuario,Model modelo) {
			UsuarioVO usuario = su.findById(idusuario).get();
			su.eliminarUsuarioRol(usuario);
			su.eliminarPublicacionUsuario(usuario);
			su.deleteById(idusuario);
			return "admin/panel";
		}
}

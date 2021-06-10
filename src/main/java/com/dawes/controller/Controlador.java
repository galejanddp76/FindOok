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
import com.dawes.modelo.OfertaVO;
import com.dawes.modelo.PublicacionVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.servicio.ServicioCloudinary;
import com.dawes.servicio.ServicioComentario;
import com.dawes.servicio.ServicioOferta;
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
	@Autowired
	ServicioOferta so;
	
	@GetMapping("/perfil")
	public String verperfil(Model modelo) {
		try {
		//obtener usuario logueado
		 Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		    UsuarioVO usuario = su.findByUsername(userDetail.getUsername()).get();
		    int idusuario = usuario.getIdusuario();
		    modelo.addAttribute("usuario", su.findById(idusuario).get());
		    return "usuario/perfil";
		    }catch (Exception e) {
				return "logout";
			}
	}
	
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
		 return "redirect:/exito";
	 }
	 
		@GetMapping("/verPublicacion")
		public String ver(@RequestParam int idpublicacion,Model modelo) {
			ComentarioVO comentario = new ComentarioVO();
			comentario.setPublicacion(sp.findById(idpublicacion).get());
			modelo.addAttribute("comentarios", comentario);
			return "publicacion/verPublicacion";
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
						return "redirect:/exito";
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
			su.eliminarComentarioUsuario(usuario);
			su.eliminarOfertaUsuario(usuario);
			su.eliminarPublicacionUsuario(usuario);
			su.deleteById(idusuario);
			return "redirect:/exito";
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
			 return "redirect:/exito";
		 }
		
		 @GetMapping("/eliminarpublicacion")
			public String eliminarpublicacion(@RequestParam int idpublicacion,Model modelo) {
			 PublicacionVO publicacion = sp.findById(idpublicacion).get();
				sp.eliminarComentarioPublicacion(publicacion);
				sp.eliminarOfertaPublicacion(publicacion);
				sp.deleteById(idpublicacion);
				return "redirect:/exito";
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
		 
		 @GetMapping("/eliminarcomentario")
			public String eliminarcomentario(@ModelAttribute("comentario") ComentarioVO comentario,Model modelo) {
				sco.deleteById(comentario.getIdcomentario());
				return "redirect:/exito";
			}
		 
		 
		 @GetMapping("/intercambio")
			public String intercambio(@RequestParam int idpublicacion, Model modelo) {
			 PublicacionVO publicacion = sp.findById(idpublicacion).get();
			 OfertaVO oferta = new OfertaVO();
			 oferta.setPublicacion(publicacion);
			 oferta.setUsuario(publicacion.getUsuario());
				modelo.addAttribute("ofertas", oferta);
				return "pagos/intercambio";
			}
			
		 @PostMapping("/intercambio")
		 public String intercambiar(@RequestParam(name = "file", required = false) MultipartFile file, OfertaVO oferta) throws IOException {
			 	//Llama servicio cloudinary para subir la imagen
				 try {
				 Map<?, ?> result = sc.upload(file);
				 //Establece url de la imagen de cloudinary para mostrarla
				 oferta.setImagenoferta((String)result.get("url"));
				 //Si el usuario no pone ninguna imagen
				 }catch (FileNotFoundException e) {
				 oferta.setImagenoferta("/images/logo_small.png");
				 }
				 //Establece fecha de hoy
				 oferta.setFechaoferta(LocalDate.now());
				 //Establece tipo de oferta
				 oferta.setTipo("Intercambio");
				 //Establece pago
				 oferta.setPago("Libro");
				 //obtener usuario logueado
				 Authentication auth = SecurityContextHolder
				            .getContext()
				            .getAuthentication();
				    UserDetails userDetail = (UserDetails) auth.getPrincipal();
				    UsuarioVO usuario = su.findByUsername(userDetail.getUsername()).get();
				    oferta.setNombreusuario(usuario.getUsername());
				    oferta.setContacto(usuario.getCorreo());
				    so.save(oferta);
				 return "redirect:/exito";
			 }
		 
		 @GetMapping("/compra")
			public String compra(@RequestParam int idpublicacion, Model modelo) {
			 PublicacionVO publicacion = sp.findById(idpublicacion).get();
			 OfertaVO oferta = new OfertaVO();
			 oferta.setPublicacion(publicacion);
			 oferta.setUsuario(publicacion.getUsuario());
				modelo.addAttribute("ofertas", oferta);
				return "pagos/pago";
			}
			
		 @PostMapping("/compra")
		 public String comprar(@ModelAttribute OfertaVO oferta) throws IOException {
				 //Establece una imagen por defecto
				 oferta.setImagenoferta("/images/logo_small.png");
				 //Establece fecha de hoy
				 oferta.setFechaoferta(LocalDate.now());
				 //Establece tipo de oferta
				 oferta.setTipo("Compra");
				 //Establece un titulo
				 oferta.setTitulo("Compra de libro por "+oferta.getPublicacion().getPrecio()+"€");
				 //Establece una decripcion
				 oferta.setDescripcion("Estoy interesado en el libro");
				 //obtener usuario logueado
				 Authentication auth = SecurityContextHolder
				            .getContext()
				            .getAuthentication();
				    UserDetails userDetail = (UserDetails) auth.getPrincipal();
				    UsuarioVO usuario = su.findByUsername(userDetail.getUsername()).get();
				    oferta.setNombreusuario(usuario.getUsername());
				    oferta.setContacto(usuario.getCorreo());
				    so.save(oferta);
				 return "redirect:/exito";
			 }
		 
		 @GetMapping("/eliminaroferta")
			public String eliminaroferta(@RequestParam int idoferta,Model modelo) {
				so.deleteById(idoferta);
				return "redirect:/exito";
			}
}

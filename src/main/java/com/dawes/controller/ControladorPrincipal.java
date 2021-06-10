package com.dawes.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dawes.modelo.UsuarioRolVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.servicio.ServicioRol;
import com.dawes.servicio.ServicioUsuario;
import com.dawes.servicio.ServicioUsuarioRol;

@Controller
public class ControladorPrincipal {
	@Autowired
	ServicioUsuario su;
	@Autowired
	ServicioUsuarioRol sur;
	@Autowired
	ServicioRol sr;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/panel")
	public String panel() {
		return "admin/panel";
	}
	
	@GetMapping("/exito")
	public String exito() {
		return "exito";
	}
	
	@GetMapping("/login")
	public String login() {
		return"login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return"logout";
	}
	
	@GetMapping("/403")
	public String p403() {
		return"403";
	}
	
	@GetMapping("/404")
	public String p404() {
		return "404";
	}
	
	@GetMapping("/registrarse")
	public String registro(Model modelo) {
		modelo.addAttribute("usuario", new UsuarioVO());
		return "registro";
	}
	
	@PostMapping("/persistirusuario")
	public String persistir(@Valid @ModelAttribute("usuario") UsuarioVO usuario, BindingResult result, Model model) {
		//validacion de los campos del formulario
		if(result.hasErrors()) {
			return "registro";
		}else {
			try {
				//compara si el usuario existe, si las contraseñas coinciden o si el correo existe y lo inserta
				su.createUser(usuario);
				sur.save(new UsuarioRolVO(0,sr.findById(2).get(),usuario));
				return "login";
			} catch (Exception e) {
				model.addAttribute("Error",e.getMessage());
				return "registro";
			}
		}

	}

}

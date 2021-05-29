package com.dawes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@GetMapping("/registrarse")
	public String registro(Model modelo) {
		modelo.addAttribute("usuario", new UsuarioVO());
		return "registro";
	}
	
	@PostMapping("/persistirusuario")
	public String persistir(@ModelAttribute UsuarioVO usuario,Model modelo) {
		BCryptPasswordEncoder encriptador=new BCryptPasswordEncoder();
		String contraseña = encriptador.encode(usuario.getPassword());
		usuario.setPassword(contraseña);
		su.save(usuario);
		sur.save(new UsuarioRolVO(0,sr.findById(2).get(),usuario));
		return "index";
	}

}

package com.dawes.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dawes.modelo.PublicacionVO;
import com.dawes.servicio.ServicioPublicacion;

@Controller
public class Controlador {
	@Autowired
	ServicioPublicacion sp;
	
	@GetMapping("/insertar")
	public String insertar(Model modelo) {
		modelo.addAttribute("publicaciones", new PublicacionVO());
		return "publicacion/insertar";
	}
	
	 @PostMapping("/persistirpublicacion")
	 public String insertar(@RequestParam(name = "file", required = false) MultipartFile file, PublicacionVO publicacion) {
		 
		 if(!file.isEmpty()) {
			 String rutaAbsoluta = "C://Temp//uploads";
			 
			 try {
				byte[] bytes = file.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta+"//"+file.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				publicacion.setImagenpublicacion(file.getOriginalFilename());
			 }catch(Exception e) {
				 e.printStackTrace();
			 }

			 sp.save(publicacion);
		 }
		 return "index";
		 
	 }
}

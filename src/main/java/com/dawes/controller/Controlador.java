package com.dawes.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dawes.modelo.PublicacionVO;
import com.dawes.servicio.ServicioCloudinary;
import com.dawes.servicio.ServicioPublicacion;

@Controller
public class Controlador {
	@Autowired
	ServicioPublicacion sp;
	@Autowired
	ServicioCloudinary sc;
	
	@GetMapping("/insertar")
	public String insertar(Model modelo) {
		modelo.addAttribute("publicaciones", new PublicacionVO());
		return "publicacion/insertar";
	}
	
	 @PostMapping("/persistirpublicacion")
	 public String insertar(@RequestParam(name = "file", required = false) MultipartFile file, PublicacionVO publicacion) throws IOException {
		 //Llama servicio cloudinary para subir la imagen
		 Map<?, ?> result = sc.upload(file);
		 //Establece url de la imagen de cloudinary para mostrarla
		 publicacion.setImagenpublicacion((String)result.get("url"));
		 sp.save(publicacion);
		 return "index";
		 
	 }
}

package com.dawes.servicio;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ServicioCloudinary {

	Map<?, ?> upload(MultipartFile multipartFile) throws IOException;

	Map<?, ?> delete(String id) throws IOException;

}
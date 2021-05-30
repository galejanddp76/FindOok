package com.dawes.servicioImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dawes.servicio.ServicioCloudinary;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServicioCloudinaryImpl implements ServicioCloudinary {

    Cloudinary cloudinary;

    private Map<String, String> valuesMap = new HashMap<>();

    public ServicioCloudinaryImpl() {
        valuesMap.put("cloud_name", "hf8lnja5r");
        valuesMap.put("api_key", "586473453784771");
        valuesMap.put("api_secret", "k6hmPkDcpITA_PNACdvQZzNcouQ");
        cloudinary = new Cloudinary(valuesMap);
    }

    @Override
	public Map<?, ?> upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map<?, ?> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
    }

    @Override
	public Map<?, ?> delete(String id) throws IOException {
        Map<?, ?> result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}

package com.distribuida.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ImagenController {

    private static final String UPLOAD_DIR ="uploads/portadas/";
    @PostMapping("/uploads-portada")

    public ResponseEntity<Map<String, String>> uploadPortada(
            @RequestParam("file")MultipartFile file,
            @RequestParam(value = "oldImage", required = false) String oldImage
            ){
        try{
            Files.createDirectory(Paths.get(UPLOAD_DIR));
            String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + nombreArchivo);
            Files.write(path, file.getBytes());
            if(oldImage !=null && !oldImage.isEmpty()){
                Path oldImagePath = Paths.get(oldImage);
                Files.deleteIfExists(oldImagePath);
            }
            Map<String, String> response = new HashMap<>();
            response.put("ruta", "portadas/"+nombreArchivo);
            return ResponseEntity.ok(response);

        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al subir la imagen"+ e.getMessage()));

        }
    }
}

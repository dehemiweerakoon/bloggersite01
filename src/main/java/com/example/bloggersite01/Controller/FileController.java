package com.example.bloggersite01.Controller;


import com.example.bloggersite01.Service.FileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileDataService fileDataService;

    @PostMapping("/{id}")
    public ResponseEntity<?> getFileUpload(@RequestParam("image") MultipartFile file,@PathVariable long id) throws IOException {
       String file_data = fileDataService.uploadImageFileData(file,id);

       return ResponseEntity.status(HttpStatus.OK).body(file_data);
    }
    @GetMapping("/{blogId}")
    public ResponseEntity<?> getImages(@PathVariable long blogId) throws IOException {
        List<byte[]> imageData = fileDataService.getFileData(blogId);
        List<String> base64Images = new ArrayList<>();
        for (byte[] image : imageData) {
            String base64Image = Base64.getEncoder().encodeToString(image);
            base64Images.add(base64Image);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(base64Images);

    }
}

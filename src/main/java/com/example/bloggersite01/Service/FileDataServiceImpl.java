package com.example.bloggersite01.Service;

import com.example.bloggersite01.Entity.Blog;
import com.example.bloggersite01.Entity.FileData;
import com.example.bloggersite01.Repository.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileDataServiceImpl implements FileDataService {

    @Autowired
    private FileDataRepository fileDataRepository;

    final  String currentPath = System.getProperty("user.dir");

    @Override
    public String uploadImageFileData(MultipartFile file,long blogId) throws IOException {
        String file_path = currentPath+"/src/main/resources/static/"+file.getOriginalFilename();
        FileData fileData = fileDataRepository.save(FileData.builder().title(file.getOriginalFilename()).type(file.getContentType()).filePath(Path.of(file_path).toString()).blog(new Blog(blogId)).build());

        file.transferTo(Path.of(file_path));
        return "file uploaded successfully :"+file.getOriginalFilename();
    }



    @Override
    public List<byte[]> getFileData(Long blogId) throws IOException {
        Optional<List<FileData>> dbImageData = fileDataRepository.findByBlogId(blogId);
        List<byte[]> images_list  = new ArrayList<>();
        for (FileData fileData : dbImageData.get()) {
            String file_path = fileData.getFilePath();
            byte[] images = Files.readAllBytes(new File(file_path).toPath());
            images_list.add(images);
        }


        // images added to the image file in the description //...
        // so that need to identify the means for the climate for that part  main
        return images_list;
    }
}
// the main reason for that file upload in multipart file is that image can be solved using the complex blog images that need to identify
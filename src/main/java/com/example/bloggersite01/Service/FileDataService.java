package com.example.bloggersite01.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface FileDataService {
    String uploadImageFileData(MultipartFile file,long blogId) throws IOException;
    List<byte[]> getFileData(Long blogId) throws IOException;
}

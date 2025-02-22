package com.example.bloggersite01.Repository;

import com.example.bloggersite01.Entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface FileDataRepository extends JpaRepository<FileData, Long> {
    Optional<List<FileData>> findByBlogId(long blogId);
}

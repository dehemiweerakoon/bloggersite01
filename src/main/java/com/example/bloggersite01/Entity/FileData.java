package com.example.bloggersite01.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "file")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    private String type;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;
}

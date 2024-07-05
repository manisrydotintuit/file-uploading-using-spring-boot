package com.manibhadra.file_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "ImageData")
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String  name;
    private  String type;
    @Lob
    @Column(name = "imageData", length = 100000000)
    private  byte[] imageData;
}

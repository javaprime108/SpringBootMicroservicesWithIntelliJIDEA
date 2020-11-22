package com.myplantdiary.enterprise.dao;

import com.myplantdiary.enterprise.dto.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class PhotoDAO implements IPhotoDAO {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void save(Photo photo) {

    }


    @Override
    public void saveImage(MultipartFile imageFile, Photo photo) throws IOException {
        Path path = Paths.get(photo.getPath() + imageFile.getOriginalFilename());
        kafkaTemplate.send("photoIn", path.normalize().toString());
    }

}

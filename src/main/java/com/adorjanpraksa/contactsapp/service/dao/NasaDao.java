package com.adorjanpraksa.contactsapp.service.dao;

import com.adorjanpraksa.contactsapp.integration.nasa.NasaRoverImagesClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class NasaDao {

    private final NasaRoverImagesClient nasaRoverImagesClient;

    public Map<String, List<String>> getImages(String rover, String camera) {

        return nasaRoverImagesClient.getPhotos(rover,camera);
    }
}

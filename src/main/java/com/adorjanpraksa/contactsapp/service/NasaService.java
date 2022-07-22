package com.adorjanpraksa.contactsapp.service;

import com.adorjanpraksa.contactsapp.service.dao.NasaDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class NasaService {

    private final NasaDao nasaDao;

    public Map<String, List<String>> getImages(String rover, String camera) {

        return nasaDao.getImages(rover,camera);
    }
}

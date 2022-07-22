package com.adorjanpraksa.contactsapp.integration.nasa;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "nasaRoverFeignClient", url = "http://localhost:8080")
public interface NasaRoverImagesClient {

    @GetMapping(value = "/images")
    Map<String, List<String>> getPhotos(@RequestParam("rover") String rover,
                                        @RequestParam("camera") String camera);
}

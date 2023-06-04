package com.example.blog.service;

import com.example.blog.dto.AirbnbDto;
import com.example.blog.repository.MyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MongoService {
    private final MyRepository myRepository;

    public AirbnbDto getAirbnbData(String name){
        AirbnbDto airbnbDto = myRepository.findByName(name);
        return airbnbDto;
    }

}

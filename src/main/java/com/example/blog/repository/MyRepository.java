package com.example.blog.repository;

import com.example.blog.dto.AirbnbDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends MongoRepository<AirbnbDto, String> {
    AirbnbDto findByName(String name);
}

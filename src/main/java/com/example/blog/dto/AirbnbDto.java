package com.example.blog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "listingsAndReviews")
public class AirbnbDto {

    //mongodb에서 자동적으로 생성하는 id
    @Id
    @NotBlank
    private String id;

    @NotBlank
    private String listing_url;

    @NotBlank
    private String name;

    @NotBlank
    private String summary;

}

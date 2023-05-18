package com.example.blog.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BeanValidationDto {
    //null 불가, "" 가능, " "가능
    @NotNull
    private String name1;

    //null 불가, ""불가, " "가능
    @NotEmpty
    private String name2;

    //null 불가, "" 불가, " "불가
    @NotBlank
    private String name3;

    @Max(9999)
    private int price1;
    @Min(1000)
    private int price2;

}

package com.example.blog.dto;

import lombok.Data;

@Data
public class AccessModifierDto {

    private int priField;

    int defField;

    protected int proField;

    public int pubField;

}

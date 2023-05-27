package com.example.blog.controller;

import com.example.blog.dto.AccessModifierDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api")
public class AccessModifierController extends AccessModifierDto{


    @GetMapping("accessModifier/test")
    public void accessModifierTest() {
        AccessModifierDto accessModifierDto = new AccessModifierDto();

        log.info("Setter, 필드 값 주입 전 public Field = {}",pubField);
        pubField = 10;
        log.info("필드 값 주입 후 pubField = {}",pubField);

        log.info("Setter, 필드 값 주입 전 protected Field = {}",proField);
        proField = 20;
        log.info("필드 값 주입 후 protected Field = {}",proField);

        //log.info("Setter, 필드 값 주입 전 public Field = {}",priField);
        //log.info("Setter, 필드 값 주입 전 public Field = {}",defField);

        accessModifierDto.setPriField(1);
        accessModifierDto.setDefField(2);
        accessModifierDto.setProField(3);
        accessModifierDto.setPubField(4);
        log.info("privateField : {}",accessModifierDto.getPriField());
        log.info("DefaultField : {}",accessModifierDto.getDefField());
        log.info("ProtectedField : {}",accessModifierDto.getProField());
        log.info("PublicField : {}",accessModifierDto.getPubField());

    }

}

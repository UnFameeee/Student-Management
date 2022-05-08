package com.example.springbootcloud.controller;

import com.example.springbootcloud.model.dto.Translate;
import com.example.springbootcloud.service.translate.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/translate")
public class TranslateController {
    @Autowired
    private TranslateService translateService;

    @PostMapping("")
    public ResponseEntity<?> translate(@RequestBody Translate req){
        String finalText = translateService.translate(req.getText(), req.getSourcelang(), req.getTargetlang());
        return ResponseEntity.ok(new HashMap<>() {{put("key", finalText);}});
    }
}

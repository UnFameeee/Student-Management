package com.example.springbootcloud.model.dto;

import com.example.springbootcloud.service.translate.Language;
import org.springframework.stereotype.Component;

@Component
public class Translate {
    private String text;
    private Language sourcelang;
    private Language targetlang;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Language getSourcelang() {
        return sourcelang;
    }

    public void setSourcelang(Language sourcelang) {
        this.sourcelang = sourcelang;
    }

    public Language getTargetlang() {
        return targetlang;
    }

    public void setTargetlang(Language targetlang) {
        this.targetlang = targetlang;
    }
}

package com.example.springbootcloud.service.translate;

import lombok.Getter;

@Getter
public enum Language {
    ARABIC("ar"),
    CHINESE("zh"),
    ENGLISH("en"),
    FRENCH("fr"),
    GERMAN("de"),
    PORTUGUESE("pt"),
    VIETNAMESE("vi"),
    AUTO("auto"),
    SPANISH("es");

    private String langCode;

    Language(String langCode) {
        this.langCode = langCode;
    }
}

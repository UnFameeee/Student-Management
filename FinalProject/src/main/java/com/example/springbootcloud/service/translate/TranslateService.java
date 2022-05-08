package com.example.springbootcloud.service.translate;

import org.springframework.stereotype.Service;

@Service
public interface TranslateService {
    String translate(String text, Language sourceLang, Language targetLang);
}

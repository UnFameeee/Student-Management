package com.example.springbootcloud.service.translate;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import com.example.springbootcloud.config.AwsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class AmazonTranslateImpl implements TranslateService{
    private final AwsConfig config;
    private AmazonTranslate client;
    private static final String REGION = "US_EAST_1";

    public AmazonTranslateImpl(AwsConfig config) {
        this.config = config;
    }

    @PostConstruct
    public void init() {
        BasicSessionCredentials sessionCredentials = new BasicSessionCredentials(
                config.getApiKey(),
                config.getSecretKey(),
                config.getSessionToken());

        client = AmazonTranslateClient.builder()
                .withCredentials(new AWSStaticCredentialsProvider(sessionCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Override
    public String translate(String text, Language sourceLang, Language targetLang) {
        TranslateTextRequest request = new TranslateTextRequest()
                .withText(text)
                .withSourceLanguageCode(sourceLang.getLangCode())
                .withTargetLanguageCode(targetLang.getLangCode());

        try {
            TranslateTextResult result = client.translateText(request);
            System.out.println(result.getTranslatedText());
            log.info("Text: {}, Source Lang: {}, Target Lang: {}, Result: {}", text, sourceLang, targetLang, result.getTranslatedText());
            return result.getTranslatedText();
        } catch (Exception e) {
            log.error("Amazon Translate Exception:", e);
        }
        return null;
    }
}

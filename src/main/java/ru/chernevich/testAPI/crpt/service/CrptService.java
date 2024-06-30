package ru.chernevich.testAPI.crpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateRequestCustomizer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CrptService {
    private final RestTemplate restTemplate;

    @Autowired
    public CrptService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createDocument(Object document, String signature) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(document, headers);

        restTemplate.postForObject("https://ismp.crpt.ru/api/v3/lk/documents/create=" + signature, request, Object.class);
    }
}

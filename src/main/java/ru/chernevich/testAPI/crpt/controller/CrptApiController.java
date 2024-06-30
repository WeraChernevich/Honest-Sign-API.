package ru.chernevich.testAPI.crpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.chernevich.testAPI.crpt.service.CrptService;

@RestController
public class CrptApiController {
    private final CrptService crptService;

    @Autowired
    public CrptApiController(CrptService crptService) {
        this.crptService = crptService;
    }

    @PostMapping("api/createDocument")
    public void handleCreateDocument(@RequestBody Object document, String signature) {
        crptService.createDocument(document, signature);
    }

    @GetMapping("api/createDocument")
    public String apiListener() {
        return "Hello API";

    }
}

package com.pastebin.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.pastebin.JsonViewSchema;
import com.pastebin.entities.Snippet;
import com.pastebin.services.SnippetService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/code/", produces = MediaType.APPLICATION_JSON_VALUE)
public class SnippetRestController {
    private final SnippetService snippetService;
    private final ObjectMapper objectMapper;
    @Autowired
    public SnippetRestController(SnippetService snippetService, ObjectMapper objectMapper) {
        this.snippetService = snippetService;
        this.objectMapper = objectMapper;
    }

    @GetMapping(path = "/{id}")
    public Snippet getSnippetJsonById(@PathVariable int id) {
//        try {
//            return objectMapper.writerWithView(JsonViewSchema.getView.class).writeValueAsString(snippetService.getSnippetById(id));
//        } catch (Exception e) {
//            return e.getMessage();
//        }
        return snippetService.getSnippetById(id);
    }
    public Snippet getSnippetById(@PathVariable int id) {
        return snippetService.getSnippetById(id);
    }

    public List<Snippet> getLastSnippets() {

        return new ArrayList<>(snippetService.getLastSnippets(10));
    }
    @RequestMapping(path = "/latest")
    public String getLastSnippetsJson() {

        List<Snippet> latestSnippets = new ArrayList<>(snippetService.getLastSnippets(10));

        try {
            return objectMapper.writerWithView(JsonViewSchema.getView.class).writeValueAsString(latestSnippets);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping(path = "/new")
    public String addNewSnippet(@RequestBody String newSnippetJson) throws JsonProcessingException {
        try {
            String newCode = objectMapper.readValue(newSnippetJson, NewCode.class).getCode();
            int newSnippetId = snippetService.saveSnippet(new Snippet(newCode));

            return objectMapper
                    .writerWithView(JsonViewSchema.postView.class)
                    .writeValueAsString(snippetService.getSnippetById(newSnippetId));
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class NewCode {
        String code;
    }
}



package com.pastebin.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pastebin.entities.NewCode;
import com.pastebin.entities.Snippet;
import com.pastebin.entities.SnippetDTO;
import com.pastebin.services.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public SnippetDTO getSnippetJsonById(@PathVariable int id) {
        return snippetService.getSnippetById(id);
    }

    @RequestMapping(path = "/latest")
    public List<SnippetDTO> getLastSnippets() {
        return snippetService.getLastSnippets(10);
    }

    @PostMapping(path = "/new")
    public int addNewSnippet(@RequestBody String newSnippetJson) {
        try {
            NewCode newCode = objectMapper.readValue(newSnippetJson, NewCode.class);

            return snippetService.saveSnippet(new Snippet(newCode.getCode()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}



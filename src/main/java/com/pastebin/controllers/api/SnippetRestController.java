package com.pastebin.controllers.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pastebin.models.SnippetDTO;
import com.pastebin.models.SnippetDTOMapper;
import com.pastebin.services.LimitedSnippetService;
import com.pastebin.services.PublicSnippetService;
import com.pastebin.services.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/code/", produces = MediaType.APPLICATION_JSON_VALUE)
public class SnippetRestController {
    private final SnippetService snippetService;

    @Autowired
    public SnippetRestController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public SnippetDTO getSnippetJsonById(@PathVariable String id) {
        return snippetService.getSnippetById(id);
    }
    @GetMapping(value = {"/latest/{quantity}", "/latest"})
    public List<SnippetDTO> getLastSnippets(@PathVariable(required = false) String quantity) {
        return snippetService.getLatestSnippets(quantity != null ? Integer.parseInt(quantity) : 10);
    }
    @GetMapping(path = "/all")
    List<SnippetDTO> getAll() {
        return snippetService.findAll();
    }

   @PostMapping(value = "/new")
   @ResponseBody
    public String getNewCode(@RequestBody String newCodeJson) throws JsonProcessingException {
        return "ID: " + snippetService.saveSnippet(newCodeJson);
    }
}



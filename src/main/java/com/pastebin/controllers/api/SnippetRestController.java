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
    private final ObjectMapper objectMapper;
    private final SnippetDTOMapper snippetDTOMapper;

    @Autowired
    public SnippetRestController(PublicSnippetService publicSnippetService, LimitedSnippetService limitedSnippetService, SnippetService snippetService, ObjectMapper objectMapper, SnippetDTOMapper snippetDTOMapper) {
        this.snippetService = snippetService;
        this.objectMapper = objectMapper;
        this.snippetDTOMapper = snippetDTOMapper;
    }


    @GetMapping(path = "/{id}")
    public SnippetDTO getSnippetJsonById(@PathVariable String id) throws Exception {
       try {
           return snippetDTOMapper.apply(
                   snippetService.getSnippetById(id).get()
           );
       } catch (Exception e) {
           throw e;
       }
    }

//    @RequestMapping(path = "/latest")
//    public List<SnippetDTO> getLastSnippets() {
//        return snippetService.getLastSnippets(10);
//    }
//    @GetMapping(path = "/all")
//    List<SnippetDTO> getAll() {
//        return .getAll().stream().map(snippetDTOMapper).toList();
//    }

   @PostMapping(value = "/new")
    public UUID getNewCode(@RequestBody String json) throws JsonProcessingException {
       return snippetService.saveSnippet(
                snippetDTOMapper.toSnippet(
                        objectMapper.readValue(json, SnippetDTO.class)
                )
        );
    }
}



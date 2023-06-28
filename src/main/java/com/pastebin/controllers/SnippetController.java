package com.pastebin.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pastebin.models.SnippetDTO;
import com.pastebin.models.SnippetDTOMapper;
import com.pastebin.services.PublicSnippetService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping(value = "/code", produces = MediaType.TEXT_HTML_VALUE)
public class SnippetController {

    final private PublicSnippetService publicSnippetService;
    final private SnippetDTOMapper snippetDTOMapper;
    final private ObjectMapper objectMapper;

    public SnippetController(PublicSnippetService publicSnippetService, SnippetDTOMapper snippetDTOMapper, ObjectMapper objectMapper) {
        this.publicSnippetService = publicSnippetService;
        this.snippetDTOMapper = snippetDTOMapper;
        this.objectMapper = objectMapper;
    }

//    @GetMapping(value = "/{id}")
//    public ModelAndView getCodePage(@NotNull HttpServletResponse response, @PathVariable String id)  {
//        response.addHeader("Content-Type", "text/html");
//
//        try {
//            ModelAndView model = new ModelAndView("codePage");
//            model.addObject("pageTitle", "Code");
//            SnippetDTO snippet = snippetService.getSnippetById(id);
//            model.addObject("snippetList", List.of(snippet));
//            model.setViewName("codePage");
//            return model;
//        } catch (Exception e) {
//            return new ModelAndView("404");
//        }
//    }
//    @GetMapping(value = "/latest")
//    public ModelAndView getLatestCodePage(@NotNull HttpServletResponse response)  {
//        response.addHeader("Content-Type", "text/html");
//
//        try {
//
//            ModelAndView model = new ModelAndView("codePage");
//            model.addObject("pageTitle", "Latest");
//            model.addObject("snippetList", snippetService.getLastSnippets(10));
//
//            model.setViewName("codePage");
//            return model;
//        } catch (Exception e) {
//            return new ModelAndView("404");
//        }
//    }


    @PostMapping(value = "/new")
    public UUID getNewCode(@NotNull String json) throws JsonProcessingException {
        return publicSnippetService.saveSnippet(
                snippetDTOMapper.toSnippet(
                        objectMapper.readValue(json, SnippetDTO.class)
                )
        );
    }
}


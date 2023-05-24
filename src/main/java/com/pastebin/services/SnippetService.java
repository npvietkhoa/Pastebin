package com.pastebin.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pastebin.models.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SnippetService {
    private final LimitedSnippetService limitedSnippetService;
    private final PublicSnippetService publicSnippetService;
    private final SnippetDTOMapper snippetDTOMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public SnippetService(LimitedSnippetService limitedSnippetService, PublicSnippetService publicSnippetService) {
        this.limitedSnippetService = limitedSnippetService;
        this.publicSnippetService = publicSnippetService;
        this.snippetDTOMapper = snippetDTOMapper;
        this.objectMapper = objectMapper;
    }


    public UUID saveSnippet(@NotNull String newSnippetJson) throws JsonProcessingException {
        Snippet newSnippet = snippetDTOMapper.toSnippet(
                objectMapper.readValue(newSnippetJson, SnippetDTO.class)
        );

        if (newSnippet instanceof LimitedSnippet) {
            return limitedSnippetService.saveSnippet(newSnippet);
        }

        if (newSnippet instanceof PublicSnippet) {
            return publicSnippetService.saveSnippet(newSnippet);
        }
        return UUID.fromString("0");
    }

    public SnippetDTO getSnippetById(String id) {
        //search in limited database first
        Optional<? extends Snippet> snippet = limitedSnippetService.getSnippetById(id);
        if (snippet.isEmpty()) {// if in limited database not found then in public one
            snippet = publicSnippetService.getSnippetById(id);
            return snippetDTOMapper.apply(snippet.get());
        } else {

            return snippetDTOMapper.apply(snippet.get());
        }
    }

    public List<SnippetDTO> findAll() {
        return publicSnippetService.getAll()
                .stream()
                .map(snippetDTOMapper)
                .toList();
    }

    public List<SnippetDTO> getLatestSnippets(int quantity) {
        int repositorySize = publicSnippetService.getAll().size();

        List<SnippetDTO> latestSnippetDTOs = new ArrayList<>();

        publicSnippetService.getAll()
                .stream()
                .skip(quantity > repositorySize ? 0 : repositorySize - quantity)
                .map(snippetDTOMapper)
                .forEach(latestSnippetDTOs::add);

        Collections.reverse(latestSnippetDTOs);
        return latestSnippetDTOs;
    }

}

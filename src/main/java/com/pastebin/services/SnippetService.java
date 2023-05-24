package com.pastebin.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pastebin.models.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SnippetService implements SnippetServiceInterface {
    private final LimitedSnippetService limitedSnippetService;
    private final PublicSnippetService publicSnippetService;

    @Autowired
    public SnippetService(LimitedSnippetService limitedSnippetService, PublicSnippetService publicSnippetService) {
        this.limitedSnippetService = limitedSnippetService;
        this.publicSnippetService = publicSnippetService;
    }

    @Override
    public UUID saveSnippet(@NotNull Snippet snippet) {
        if (snippet instanceof LimitedSnippet) {
            return limitedSnippetService.saveSnippet(snippet);
        }

        if (snippet instanceof PublicSnippet) {
            return publicSnippetService.saveSnippet(snippet);
        }
        return UUID.randomUUID();
    }

    @Override
    public Optional<? extends Snippet> getSnippetById(String id) {
        //search in limited database first
        Optional<? extends Snippet> snippet = limitedSnippetService.getSnippetById(id);
        if (snippet.isEmpty()) {// if in limited database not found then in public one
            snippet = publicSnippetService.getSnippetById(id);
            return snippet;
        } else {
            return snippet;
        }
    }

}

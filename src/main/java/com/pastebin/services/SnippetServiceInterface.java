package com.pastebin.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.pastebin.models.Snippet;
import org.jetbrains.annotations.NotNull;
//import com.pastebin.models.SnippetDTOMapper;

import java.util.Optional;
import java.util.UUID;

public interface SnippetServiceInterface {
     UUID saveSnippet(Snippet snippet);

    Optional<? extends Snippet> getSnippetById(String id);
}
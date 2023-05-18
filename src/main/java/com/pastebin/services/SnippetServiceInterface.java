package com.pastebin.services;


import com.pastebin.models.Snippet;
//import com.pastebin.models.SnippetDTOMapper;

import java.util.Optional;
import java.util.UUID;

public interface SnippetServiceInterface {
     UUID saveSnippet(Snippet snippet);

    Optional<? extends Snippet> getSnippetById(String id);
}
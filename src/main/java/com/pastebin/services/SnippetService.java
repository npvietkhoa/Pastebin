package com.pastebin.services;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pastebin.entities.*;
import com.pastebin.repositories.SnippetRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class SnippetService {
    private final SnippetRepository snippetRepository;

    @Autowired
    public SnippetService(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }
    public synchronized int saveSnippet(Snippet snippet) {
        snippetRepository.save(convertSnippetToSnippetDTO(snippet));
        return snippetRepository.findAll().size();
    }

//    public List<Snippet> findAll() {
//        return snippetRepository.findAll()
//                .stream()
//                .map(this::convertSnippetDTOToSnippet)
//                .toList();
//    }
    public Snippet getSnippetById(int id) {

        return convertSnippetDTOToSnippet(snippetRepository.getReferenceById(id));
    }

    public List<Snippet> getLastSnippets(int quantity) {
        int repositorySize = snippetRepository.findAll().size();

        List<Snippet> lastSnippets = new ArrayList<>();

        snippetRepository.findAll()
                .stream()
                .skip(quantity > repositorySize ? 0 : repositorySize - quantity)
                .map(this::convertSnippetDTOToSnippet)
                .forEach(lastSnippets::add);

        Collections.reverse(lastSnippets);
        return lastSnippets;
    }
    private @NotNull Snippet convertSnippetDTOToSnippet(@NotNull SnippetDTO snippetDTO) {
        return new Snippet(
                snippetDTO.getSnippet_id(),
                snippetDTO.getCode(),
                convertToLocalDateTimeViaInstant(snippetDTO.getCreatedDateTime())
        );
    }
    private @NotNull SnippetDTO convertSnippetToSnippetDTO(@NotNull Snippet snippet) {
        return new SnippetDTO(
                snippet.getCode(),
                java.sql.Timestamp.valueOf(snippet.getCreatedDateTime())
        );
    }

    private LocalDateTime convertToLocalDateTimeViaInstant(@NotNull Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}

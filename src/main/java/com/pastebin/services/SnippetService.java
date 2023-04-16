package com.pastebin.services;


import com.pastebin.entities.Snippet;
import com.pastebin.entities.SnippetDTO;
import com.pastebin.entities.SnippetDTOMapper;
import com.pastebin.repositories.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class SnippetService {
    private final SnippetRepository snippetRepository;
    private final SnippetDTOMapper snippetDTOMapper;

    @Autowired
    public SnippetService(SnippetRepository snippetRepository, SnippetDTOMapper snippetDTOMapper) {
        this.snippetRepository = snippetRepository;
        this.snippetDTOMapper = snippetDTOMapper;
    }
    public synchronized UUID saveSnippet(Snippet snippet) {
        return snippetRepository.save(snippet).getId();
    }

    public List<SnippetDTO> findAll() {
        return snippetRepository.findAll()
                .stream()
                .map(snippetDTOMapper)
                .toList();
    }

    public SnippetDTO getSnippetById(String id) {
        return snippetDTOMapper.apply(snippetRepository.getReferenceById(UUID.fromString(id)));
    }

    public List<SnippetDTO> getLastSnippets(int quantity) {
        int repositorySize = snippetRepository.findAll().size();

        List<SnippetDTO> lastSnippetDTOs = new ArrayList<>();

        snippetRepository.findAll()
                .stream()
                .skip(quantity > repositorySize ? 0 : repositorySize - quantity)
                .map(snippetDTOMapper)
                .forEach(lastSnippetDTOs::add);

        Collections.reverse(lastSnippetDTOs);
        return lastSnippetDTOs;
    }
//    private LocalDateTime convertToLocalDateTimeViaInstant(@NotNull Date dateToConvert) {
//        return dateToConvert.toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDateTime();
//    }
}

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

@Service
public class SnippetService {
    private final SnippetRepository snippetRepository;
    private final SnippetDTOMapper snippetDTOMapper;

    @Autowired
    public SnippetService(SnippetRepository snippetRepository, SnippetDTOMapper snippetDTOMapper) {
        this.snippetRepository = snippetRepository;
        this.snippetDTOMapper = snippetDTOMapper;
    }
    public synchronized int saveSnippet(Snippet snippet) {
        snippetRepository.save(snippet);
        return snippetRepository.findAll().size();
    }

    public List<SnippetDTO> findAll() {
        return snippetRepository.findAll()
                .stream()
                .map(snippetDTOMapper)
                .toList();
    }

    public SnippetDTO getSnippetById(int id) {
        return snippetDTOMapper.apply(snippetRepository.getReferenceById(id));
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

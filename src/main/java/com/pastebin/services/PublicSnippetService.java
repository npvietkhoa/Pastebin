package com.pastebin.services;

import com.pastebin.models.PublicSnippet;
import com.pastebin.models.Snippet;
import com.pastebin.repositories.PublicSnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PublicSnippetService implements SnippetServiceInterface {
    final private PublicSnippetRepository publicSnippetRepository;

    @Autowired
    public PublicSnippetService(PublicSnippetRepository publicSnippetRepository) {
        this.publicSnippetRepository = publicSnippetRepository;
    }

    @Override
    public UUID saveSnippet(Snippet snippet) {
        PublicSnippet po = (PublicSnippet) snippet;
        return publicSnippetRepository.save((PublicSnippet) snippet).getId();
    }


    @Override
    public Optional<PublicSnippet> getSnippetById(String id) {
        Optional<PublicSnippet> snippet = publicSnippetRepository.findById(UUID.fromString(id));
        if (snippet.isPresent()) {
            snippet.get().updateViewCount();
            publicSnippetRepository.save(snippet.get());
        }
        return snippet;
    }

    public List<PublicSnippet> getAll() {
        return publicSnippetRepository.findAll();
    }
}

package com.pastebin.services;

import com.pastebin.models.LimitedSnippet;
import com.pastebin.models.Snippet;
import com.pastebin.repositories.LimitedSnippetRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LimitedSnippetService implements SnippetServiceInterface {

    final private LimitedSnippetRepository limitedSnippetRepository;

    public LimitedSnippetService(LimitedSnippetRepository limitedSnippetRepository) {
        this.limitedSnippetRepository = limitedSnippetRepository;
    }


    @Override
    public UUID saveSnippet(@NotNull Snippet snippet) {
        LimitedSnippet sp = (LimitedSnippet)  snippet;
        return limitedSnippetRepository.save(sp).getId();
    }

    @Override
    public Optional<LimitedSnippet> getSnippetById(String id) {
        Optional<LimitedSnippet> snippet = limitedSnippetRepository.findById(UUID.fromString(id));

        if (snippet.isPresent()) {
            // check for limitations
            if (snippet.get().getViewLimit() < 1 ||
                    snippet.get().getExpireTime().after(Timestamp.valueOf(LocalDateTime.now()))
            ) {
                return Optional.empty();
            }

        } else {
            return Optional.empty();
        }

        snippet.get().updateViewCount();
        snippet.get().updateViewLimit();
        limitedSnippetRepository.save(snippet.get());
        return snippet;
    }
}

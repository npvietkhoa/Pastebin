package com.pastebin.repositories;

import com.pastebin.models.LimitedSnippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LimitedSnippetRepository extends JpaRepository<LimitedSnippet, UUID> {
}

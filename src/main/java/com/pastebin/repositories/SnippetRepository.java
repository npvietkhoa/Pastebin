package com.pastebin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pastebin.entities.SnippetDTO;

@Repository
public interface SnippetRepository extends JpaRepository<SnippetDTO, Integer> {
}


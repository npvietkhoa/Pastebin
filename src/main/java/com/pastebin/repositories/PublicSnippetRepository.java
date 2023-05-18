package com.pastebin.repositories;

import com.pastebin.models.PublicSnippet;
import com.pastebin.models.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublicSnippetRepository extends JpaRepository<PublicSnippet, UUID> {
}

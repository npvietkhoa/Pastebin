package com.pastebin.repositories;

import com.pastebin.models.PublicSnippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PublicSnippetRepository extends JpaRepository<PublicSnippet, UUID> {
    @Query("SELECT ps FROM PublicSnippet ps WHERE TYPE(ps) = PublicSnippet")
    List<PublicSnippet> getAllPublicSnippet();
}

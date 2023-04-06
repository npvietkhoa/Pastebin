package com.pastebin.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SnippetDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer snippet_id;

    private String code;
    private Timestamp createdDateTime;

    public SnippetDTO(String code, Timestamp createdDateTime) {
        this.code = code;
        this.createdDateTime = createdDateTime;
    }
}

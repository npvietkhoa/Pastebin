package com.pastebin.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Entity
@NoArgsConstructor
public class Snippet {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private String code;

    @Setter(AccessLevel.NONE)
    private Timestamp createdDateTime = Timestamp.valueOf(LocalDateTime.now());
    public Snippet(String code) {
        this.code = code;
    }

}



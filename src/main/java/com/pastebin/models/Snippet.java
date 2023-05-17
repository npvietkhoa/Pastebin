package com.pastebin.models;

import com.pastebin.enums.CodeLang;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Snippet  {

    @Id
    @GeneratedValue
    private UUID id;
    private String code;
    private CodeLang codeSyntax = CodeLang.NONE;
    private final Timestamp createdDateTime = Timestamp.valueOf(LocalDateTime.now());



    public Snippet(String code) {
        this.code = code;
    }

    public abstract void updateViewCount();
}



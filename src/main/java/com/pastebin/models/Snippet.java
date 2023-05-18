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
    @Column(name = "code")
    private String code;
    @Column(name = "code_syntax")
    private CodeLang codeSyntax = CodeLang.NONE;
    @Column(name = "created_time")
    private final Timestamp createdDateTime = Timestamp.valueOf(LocalDateTime.now());


    public abstract void updateViewCount();
}



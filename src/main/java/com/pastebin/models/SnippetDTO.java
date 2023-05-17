package com.pastebin.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pastebin.enums.CodeLang;
import com.pastebin.enums.SnippetCategory;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnippetDTO  {
    @JsonProperty("code")
    private String code;

    @JsonProperty("syntax")
    private CodeLang codeSyntax = CodeLang.NONE;

    @JsonProperty("view")
    private long viewCount = 0;

    @JsonProperty("createdDateTime")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime createdDateTime = LocalDateTime.now();

    @JsonProperty("category")
    private SnippetCategory snippetCategory = SnippetCategory.PUBLIC;

    @JsonProperty("time limit")
    private long timeLimit = 0;

    @JsonProperty("view limit")
    private long viewLimit = 0;



    public SnippetDTO(@NotNull Snippet snippet) {
        this.code = snippet.getCode();
        this.codeSyntax = snippet.getCodeSyntax();
        System.out.println(snippet.getCreatedDateTime());
        this.createdDateTime = snippet.getCreatedDateTime()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        if (snippet.getClass().equals(PublicSnippet.class)) {
            this.viewCount = ((PublicSnippet) snippet).getViewCount();
        } else if (snippet.getClass().equals(LimitedSnippet.class)) {
            this.viewCount = ((PublicSnippet) snippet).getViewCount();
            this.timeLimit = ((LimitedSnippet) snippet).getTimeLimit();
            this.viewLimit = ((LimitedSnippet) snippet).getViewLimit();
        }
    }
}

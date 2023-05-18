package com.pastebin.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pastebin.enums.CodeLang;
import com.pastebin.enums.SnippetCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
    private SnippetCategory snippetCategory;

    @JsonIgnore
    @JsonProperty("time limit")
    private long timeLimit;

    @JsonProperty("view limit")
    private long viewLimit;



    public SnippetDTO(@NotNull Snippet snippet) {
        this.code = snippet.getCode();
        this.codeSyntax = snippet.getCodeSyntax();
        System.out.println(snippet.getCreatedDateTime());
        this.createdDateTime = snippet.getCreatedDateTime()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        if (snippet.getClass().equals(PublicSnippet.class)) {
            this.snippetCategory = SnippetCategory.PUBLIC;
            this.viewCount = ((PublicSnippet) snippet).getViewCount();
        } else if (snippet.getClass().equals(LimitedSnippet.class)) {
            this.snippetCategory = SnippetCategory.LIMITED;
            this.viewCount = ((PublicSnippet) snippet).getViewCount();
            this.timeLimit = ((LimitedSnippet) snippet).getTimeLimit();
            this.viewLimit = ((LimitedSnippet) snippet).getViewLimit();
        }
    }
}

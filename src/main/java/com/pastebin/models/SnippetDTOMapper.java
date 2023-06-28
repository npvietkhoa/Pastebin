package com.pastebin.models;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SnippetDTOMapper implements Function<Snippet, SnippetDTO>{
    /**
     * Applies this function to the given argument.
     *
     * @param snippet the function argument
     * @return the function result
     */
    @Override
    public  SnippetDTO apply(Snippet snippet) {
        return new SnippetDTO(snippet);
    }

   public Snippet toSnippet(@NotNull SnippetDTO snippetDto) {
        return switch (snippetDto.getSnippetCategory()) {
            case PUBLIC -> new PublicSnippet(snippetDto.getCode(), snippetDto.getCodeSyntax());
            case LIMITED -> new LimitedSnippet(snippetDto.getCode(), snippetDto.getCodeSyntax(), snippetDto.getTimeLimit(), snippetDto.getViewLimit());
            case PRIVATE -> null;
        };
   }
}

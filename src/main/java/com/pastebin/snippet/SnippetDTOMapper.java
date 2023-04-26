package com.pastebin.snippet;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SnippetDTOMapper implements Function<Snippet, SnippetDTO> {
    /**
     * Applies this function to the given argument.
     *
     * @param snippet the function argument
     * @return the function result
     */
    @Override
    public SnippetDTO apply(Snippet snippet) {
        return new SnippetDTO(snippet.getCode(), snippet.getCreatedDateTime());
    }
}

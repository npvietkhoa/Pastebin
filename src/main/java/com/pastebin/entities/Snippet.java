package com.pastebin.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.pastebin.JsonViewSchema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Snippet {

    @JsonIgnore
    private int id;



    @JsonPropertyOrder({
            "id",
            "code",
            "date"
    })

    @JsonView(JsonViewSchema.postView.class)
    @JsonProperty("id")
    public String stringifyId() {
        return Integer.toString(this.id);
    }

    @JsonView({JsonViewSchema.getView.class, JsonViewSchema.fullView.class})
    @JsonProperty("code")
    private String code;

    private LocalDateTime createdDateTime;

    @JsonView({JsonViewSchema.getView.class, JsonViewSchema.fullView.class})
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonProperty("date")
    public String formatCreatedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return this.createdDateTime.format(formatter);
    }

    public Snippet(String code) {
        this.code = code;
        this.createdDateTime = LocalDateTime.now();
    }
}



package com.pastebin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pastebin.enums.CodeLang;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@Table(name= "limited_snippet")
public class LimitedSnippet extends PublicSnippet {

    @JsonIgnore
    @Transient
    private transient long timeLimit;

    private long viewLimit;

    private final Timestamp expireTime;


    public LimitedSnippet(String code, CodeLang codeSyntax, long timeLimit, long viewLimit) {
        super.setCode(code);
        super.setCodeSyntax(codeSyntax);
        this.timeLimit = timeLimit;
        this.viewLimit = viewLimit;
        this.expireTime = addMinutes(Timestamp.valueOf(LocalDateTime.now()), timeLimit);
    }

    @Override
    public void updateViewCount() {
        super.updateViewCount();
    }

    public void updateViewLimit() {
        this.viewLimit -= 1;
    }
    static @NotNull Timestamp addMinutes(@NotNull Timestamp timestamp, long numOfMinute) {
        Timestamp newTimestamp = new Timestamp(timestamp.getTime());
        newTimestamp.setTime(timestamp.getTime() + TimeUnit.MINUTES.toMillis(numOfMinute));
        return newTimestamp;
    }
}

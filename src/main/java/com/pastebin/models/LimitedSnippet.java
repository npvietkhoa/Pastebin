package com.pastebin.models;

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

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "private_snippet")
public class LimitedSnippet extends PublicSnippet {

    @Transient
    private long timeLimit;

    private long viewLimit;

    private final Timestamp expireTime = addMinutes(Timestamp.valueOf(LocalDateTime.now()), timeLimit);
    public LimitedSnippet(String code) {
        super.setCode(code);
    }


    public LimitedSnippet(String code, CodeLang codeSyntax, long timeLimit, long viewLimit) {
        super.setCode(code);
        super.setCodeSyntax(codeSyntax);
        this.timeLimit = timeLimit;
        this.viewLimit = viewLimit;
    }

    @Override
    public void updateViewCount() {
        super.updateViewCount();
    }


    static @NotNull Timestamp addMinutes(@NotNull Timestamp timestamp, long numOfMinute) {
        long milliSecInAMin = (long) (60 * 60 * 1000);
        Timestamp newTimestamp = new Timestamp(timestamp.getTime());
        long milliSecToAdd = milliSecInAMin * numOfMinute;
        long newTimeMilliSec = newTimestamp.getTime();
        newTimestamp.setTime(newTimeMilliSec + milliSecToAdd);
        return newTimestamp;
    }
}

package com.pastebin.models;

import com.pastebin.enums.CodeLang;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "public_snippet")
public class PublicSnippet extends Snippet {
    @Column(name = "view_count")
    private long viewCount = 0;

    public PublicSnippet(String code, CodeLang codeSyntax) {
        super.setCode(code);
        super.setCodeSyntax(codeSyntax);
    }

    @Override
    public void updateViewCount() {
        this.viewCount += 1;
    }
}

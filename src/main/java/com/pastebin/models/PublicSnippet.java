package com.pastebin.models;

import com.pastebin.enums.CodeLang;
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
    private long viewCount = 0;

    public PublicSnippet(String code, CodeLang codeSyntax) {
        super.setCode(code);
        super.setCodeSyntax(codeSyntax);
    }

    public PublicSnippet(String code) {
        super.setCode(code);
    }

//    @Override
//    public void setCode(String code) {
//        super.setCode(code);
//    }


    @Override
    public void updateViewCount() {
        this.viewCount += 1;
    }
}

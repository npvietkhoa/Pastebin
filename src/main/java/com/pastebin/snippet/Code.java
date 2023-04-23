package com.pastebin.snippet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Code {
    private String code;
    private CodeLang codeLang;
}

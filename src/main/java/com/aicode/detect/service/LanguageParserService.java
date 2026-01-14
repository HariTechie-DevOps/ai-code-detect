package com.aicode.detect.service;

import com.aicode.detect.util.ASTParserUtil;
import org.springframework.stereotype.Service;

@Service
public class LanguageParserService {

    public Object parseCode(String code, String language) {
        // Converts code into language-neutral AST
        return ASTParserUtil.parse(code, language);
    }
}

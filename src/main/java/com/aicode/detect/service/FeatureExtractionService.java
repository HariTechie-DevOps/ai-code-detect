package com.aicode.detect.service;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.*;

@Service
public class FeatureExtractionService {

    public Map<String, Object> extractFeatures(String code) {
        Map<String, Object> features = new HashMap<>();
        
        // 1. Line Analysis
        String[] lines = code.split("\n");
        int totalLines = lines.length;
        
        // 2. AI Comment Phrase Detection
        String[] aiPhrases = {
            "this function", "the following code", "implements the logic",
            "boilerplate", "demonstrates how to", "note that"
        };
        int aiPhraseCount = 0;
        for (String phrase : aiPhrases) {
            if (code.toLowerCase().contains(phrase)) aiPhraseCount++;
        }

        // 3. Variable Name Length (AI uses long, descriptive names)
        int longVarNames = 0;
        Matcher m = Pattern.compile("\\b[a-zA-Z]{15,}\\b").matcher(code);
        while (m.find()) longVarNames++;

        // 4. Boilerplate Check (AI almost always includes proper imports/headers)
        boolean hasStandardHeader = code.contains("package") || code.contains("import");

        features.put("aiPhrasesFound", aiPhraseCount);
        features.put("avgLineLength", code.length() / Math.max(1, totalLines));
        features.put("longVariableCount", longVarNames);
        features.put("hasProperStructure", hasStandardHeader);

        return features;
    }
}

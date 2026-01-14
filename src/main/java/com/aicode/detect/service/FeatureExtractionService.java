package com.aicode.detect.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FeatureExtractionService {

    public Map<String, Object> extractFeatures(String code) {
        Map<String, Object> features = new HashMap<>();
        
        // 1. Calculate Comment Density (AI Fingerprint)
        int totalLines = code.split("\n").length;
        int commentLines = countOccurrences(code, "//") + countOccurrences(code, "/*");
        double commentRatio = (double) commentLines / totalLines;

        // 2. Identify "AI-Style" Variable Names
        // Look for very long descriptive names (e.g., calculateUserTotalBalance)
        int longVarNames = countPattern(code, "[a-z]+([A-Z][a-z]+){3,}"); 

        // 3. Logic Density (Ratio of keywords to lines)
        int keywords = countPattern(code, "\\b(public|private|static|if|else|for|while|return|class)\\b");
        double logicDensity = (double) keywords / totalLines;

        features.put("commentRatio", commentRatio);
        features.put("complexityScore", logicDensity);
        features.put("perfectNamingCount", longVarNames);
        features.put("isIndentationPerfect", !code.contains("\t") && code.contains("    "));

        return features;
    }

    private int countOccurrences(String text, String match) {
        return text.split(Pattern.quote(match), -1).length - 1;
    }

    private int countPattern(String text, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(text);
        int count = 0;
        while (matcher.find()) count++;
        return count;
    }
}

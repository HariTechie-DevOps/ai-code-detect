package com.aicode.detect.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class LanguageParserService {

    public Map<String, Object> getCodeMetrics(String code) {
        Map<String, Object> metrics = new HashMap<>();
        
        // Real-time calculation: Count lines to detect "AI Verbosity"
        String[] lines = code.split("\n");
        metrics.put("lineCount", lines.length);
        
        // Real-time calculation: Character-to-line ratio
        // AI code is often very 'wide' with long variable names
        metrics.put("characterDensity", code.length() / Math.max(1, lines.length));

        return metrics;
    }
}

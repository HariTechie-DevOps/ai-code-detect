package com.aicode.detect.service;

import com.aicode.detect.model.AnalysisResult;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class ScoringService {

    public AnalysisResult calculateScore(Map<String, Object> features) {
        AnalysisResult result = new AnalysisResult();
        double baseScore = 0.0;

        // ACCURACY LOGIC:
        // If AI-style phrases are in comments, it's a huge red flag
        int phrases = (int) features.get("aiPhrasesFound");
        baseScore += (phrases * 0.25);

        // If variable names are over 15 characters long multiple times
        int longVars = (int) features.get("longVariableCount");
        if (longVars > 3) baseScore += 0.3;

        // If the code is perfectly structured with packages/imports
        if ((boolean) features.get("hasProperStructure")) baseScore += 0.15;

        // Final Result Mapping
        double finalProb = Math.min(baseScore, 0.99); // Max 99%
        result.setAiProbability(finalProb);
        
        // Dynamic Response Generation
        String status = (finalProb > 0.7) ? "Likely AI" : (finalProb > 0.4) ? "Suspicious" : "Likely Human";
        result.setConfidence(status);
        result.setExplanation("Detected " + phrases + " AI-typical phrases and " + longVars + " complex variable patterns.");

        result.setFeatures(features);
        return result;
    }
}

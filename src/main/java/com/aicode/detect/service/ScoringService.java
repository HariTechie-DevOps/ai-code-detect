package com.aicode.detect.service;

import com.aicode.detect.model.AnalysisResult;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ScoringService {

    public AnalysisResult calculateScore(Map<String, Object> features, double mlScore) {
        AnalysisResult result = new AnalysisResult();

        // Simple weighted combination
        double ruleScore = 0.2 + 0.25; // example, calculate based on features
        double finalScore = 0.7 * ruleScore + 0.3 * mlScore;

        result.setAiProbability(finalScore);
        result.setConfidence(finalScore > 0.6 ? "Medium-High" : "Low");
        result.setFeatures(features);
        result.setExplanation("Analysis based on Phase-1 rules and optional ML Phase-2 scoring.");

        return result;
    }
}

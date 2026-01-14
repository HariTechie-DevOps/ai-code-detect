package com.aicode.detect.model;

import java.util.Map;

public class AnalysisResult {
    private double aiProbability;
    private String confidence;
    private Map<String, Object> features;
    private String explanation;

    // Getters and setters
    public double getAiProbability() { return aiProbability; }
    public void setAiProbability(double aiProbability) { this.aiProbability = aiProbability; }

    public String getConfidence() { return confidence; }
    public void setConfidence(String confidence) { this.confidence = confidence; }

    public Map<String, Object> getFeatures() { return features; }
    public void setFeatures(Map<String, Object> features) { this.features = features; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
}

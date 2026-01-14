package com.aicode.detect.model;

import jakarta.persistence.*;
import java.util.Map;

@Entity
@Table(name = "analysis_results")
public class AnalysisResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Added for Database tracking

    private double aiProbability;
    private String confidence;
    
    @Transient // We use @Transient because Maps are hard to store directly in one SQL column
    private Map<String, Object> features;
    
    @Column(length = 1000) // Allows for long, detailed explanations
    private String explanation;

    // Standard Getters and Setters...
}

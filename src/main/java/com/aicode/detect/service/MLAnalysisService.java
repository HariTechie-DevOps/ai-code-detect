package com.aicode.detect.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MLAnalysisService {

    public double getMLScore(Map<String, Object> features) {
        // Optional Phase-2 ML score
        return 0.15; // Example score
    }
}

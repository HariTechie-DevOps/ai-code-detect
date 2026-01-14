package com.aicode.detect.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FeatureExtractionService {

    public Map<String, Object> extractFeatures(Object ast) {
        Map<String, Object> features = new HashMap<>();
        // Phase-1 rules
        features.put("problemSolvingOrder", "Simple Incremental");
        features.put("logicDensity", 0.25);
        features.put("abstractionJump", "None");
        features.put("imperfections", "Minor inefficiencies detected");
        features.put("templateReuse", "Low");
        return features;
    }
}

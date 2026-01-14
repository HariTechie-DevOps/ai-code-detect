package com.aicode.detect.controller;

import com.aicode.detect.model.CodeSubmission;
import com.aicode.detect.model.AnalysisResult;
import com.aicode.detect.service.FeatureExtractionService;
import com.aicode.detect.service.MLAnalysisService;
import com.aicode.detect.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CodeAnalysisController {

    @Autowired
    private FeatureExtractionService featureService;

    @Autowired
    private MLAnalysisService mlService;

    @Autowired
    private ScoringService scoringService;

    @PostMapping("/analyze")
    public AnalysisResult analyzeCode(@RequestBody CodeSubmission submission) {
        // 1. REAL-TIME EXTRACTION
        // We now pass the ACTUAL code text (submission.getCode()) 
        // instead of an empty AST object.
        Map<String, Object> features = featureService.extractFeatures(submission.getCode());

        // 2. ML EVALUATION
        // The ML service now has real data (like aiPhrasesFound) to look at.
        double mlScore = mlService.getMLScore(features);

        // 3. DYNAMIC SCORING
        // The scoring service calculates a unique result based on the code provided.
        AnalysisResult result = scoringService.calculateScore(features, mlScore);

        return result;
    }
}

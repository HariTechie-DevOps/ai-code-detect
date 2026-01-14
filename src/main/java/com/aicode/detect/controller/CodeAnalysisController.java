package com.aicode.detect.controller;

import com.aicode.detect.model.CodeSubmission;
import com.aicode.detect.model.AnalysisResult;
import com.aicode.detect.service.FeatureExtractionService;
import com.aicode.detect.service.MLAnalysisService;
import com.aicode.detect.service.LanguageParserService;
import com.aicode.detect.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CodeAnalysisController {

    @Autowired
    private LanguageParserService parserService;

    @Autowired
    private FeatureExtractionService featureService;

    @Autowired
    private MLAnalysisService mlService;

    @Autowired
    private ScoringService scoringService;

    @PostMapping("/analyze")
    public AnalysisResult analyzeCode(@RequestBody CodeSubmission submission) {
        // 1. Parse code to language-neutral AST
        var ast = parserService.parseCode(submission.getCode(), submission.getLanguage());

        // 2. Extract Phase-1 features
        var features = featureService.extractFeatures(ast);

        // 3. Optional Phase-2 ML analysis
        double mlScore = mlService.getMLScore(features);

        // 4. Phase-1.5 scoring
        AnalysisResult result = scoringService.calculateScore(features, mlScore);

        // 5. Return final JSON
        return result;
    }
}

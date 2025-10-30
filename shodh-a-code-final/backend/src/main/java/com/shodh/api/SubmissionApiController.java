package com.shodh.api;

import org.springframework.web.bind.annotation.*;
import com.shodh.model.*;
import com.shodh.repo.*;
import com.shodh.service.SubmissionService;
import org.springframework.beans.factory.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionApiController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private SubmissionRepo submissionRepo;

    @PostMapping
    public Map<String, Object> submit(@RequestBody Map<String, Object> body) {
        Submission s = new Submission();
        s.setUsername((String) body.getOrDefault("username", "demo"));
        s.setLanguage((String) body.getOrDefault("language", "java"));
        s.setSourceCode((String) body.getOrDefault("sourceCode", ""));
        Submission saved = submissionService.enqueue(s);

        Map<String, Object> response = new HashMap<>();
        response.put("submissionId", saved.getSubmissionUuid());
        return response;
    }

    @GetMapping("/{submissionId}")
    public Map<String, Object> status(@PathVariable String submissionId) {
        Optional<Submission> optional = submissionRepo.findBySubmissionUuid(submissionId);

        if (optional.isPresent()) {
            Submission s = optional.get();
            Map<String, Object> result = new HashMap<>();
            result.put("submissionId", s.getSubmissionUuid());
            result.put("status", s.getStatus());
            result.put("score", s.getScore());
            result.put("createdAt", s.getCreatedAt());
            return result;
        } else {
            Map<String, Object> notFound = new HashMap<>();
            notFound.put("submissionId", submissionId);
            notFound.put("status", "NOT_FOUND");
            return notFound;
        }
    }
}
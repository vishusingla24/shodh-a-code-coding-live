package com.shodh.api;

import org.springframework.web.bind.annotation.*;
import com.shodh.repo.ContestRepo;
import com.shodh.model.Contest;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@RestController
@RequestMapping("/api/contests")
public class ContestController {

    @Autowired
    private ContestRepo contestRepo;

    @GetMapping("/{contestId}")
    public Map<String, Object> get(@PathVariable String contestId) {
        Optional<Contest> optional = contestRepo.findByContestId(contestId);
        Map<String, Object> result = new HashMap<>();

        if (optional.isPresent()) {
            Contest c = optional.get();
            result.put("id", c.getId());
            result.put("name", c.getName());
            result.put("contestId", c.getContestId());
        } else {
            result.put("error", "not_found");
        }

        return result;
    }
}



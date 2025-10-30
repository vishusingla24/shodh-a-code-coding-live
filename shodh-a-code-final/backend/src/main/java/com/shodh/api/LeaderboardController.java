
package com.shodh.api;
import org.springframework.web.bind.annotation.*;
import com.shodh.repo.*;
import com.shodh.model.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/contests")
public class LeaderboardController {
  @Autowired SubmissionRepo submissionRepo;
  @GetMapping("/{contestId}/leaderboard")
  public List<Map<String,Object>> lb(@PathVariable String contestId){
    // For demo: return last 10 submissions simple aggregation
    List<Submission> all = submissionRepo.findAll();
    Map<String,Integer> solved = new HashMap<>();
    for(Submission s: all){
      solved.put(s.getUsername(), Math.max(solved.getOrDefault(s.getUsername(),0), s.getScore()==null?0:s.getScore()));
    }
    List<Map<String,Object>> out = new ArrayList<>();
    for(Map.Entry<String,Integer> e: solved.entrySet()){
      out.add(Map.of("username", e.getKey(), "solved", e.getValue()));
    }
    return out;
  }
}


package com.shodh.service;
import org.springframework.stereotype.Service;
import com.shodh.model.*;
import com.shodh.repo.*;
import org.springframework.beans.factory.annotation.*;
import jakarta.annotation.PostConstruct;
import java.util.concurrent.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;

@Service
public class SubmissionService {
  @Autowired SubmissionRepo submissionRepo;
  @Autowired TestCaseRepo testCaseRepo;
  @Value("${JUDGE_IMAGE:shodh-judge:latest}") String judgeImage;

  private BlockingQueue<Submission> queue = new LinkedBlockingQueue<>();
  private ExecutorService worker = Executors.newSingleThreadExecutor();

  @PostConstruct
  public void start() {
    worker.submit(() -> {
      while(true) {
        try {
          Submission s = queue.take();
          process(s);
        } catch(Exception e){ e.printStackTrace(); }
      }
    });
  }

  public Submission enqueue(Submission s) {
    s.setSubmissionUuid(UUID.randomUUID().toString());
    s.setStatus("PENDING"); s.setCreatedAt(java.time.Instant.now());
    submissionRepo.save(s);
    queue.add(s);
    return s;
  }

  private void process(Submission s) {
    try {
      s.setStatus("RUNNING"); submissionRepo.save(s);
      // For demo: lookup testcases for a default problem id 1
      List<TestCase> cases = testCaseRepo.findByProblemId(1L);
      int passed = 0;
      for(TestCase tc: cases) {
        Path tmp = Files.createTempDirectory("sub-");
        Files.writeString(tmp.resolve("Solution.java"), s.getSourceCode());
        if(tc.getStdin()!=null) Files.writeString(tmp.resolve("input.txt"), tc.getStdin());
        List<String> cmd = Arrays.asList("docker","run","--rm","--network","none","--memory","256m","--cpus","0.5","-v", tmp.toAbsolutePath()+":/workspace", judgeImage);
        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.redirectErrorStream(true);
        Process p = pb.start();
        StringBuilder out = new StringBuilder();
        try(BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))){
          String line; while((line=r.readLine())!=null) out.append(line).append("\n");
        }
        p.waitFor(10, java.util.concurrent.TimeUnit.SECONDS);
        int code = p.exitValue();
        String output = out.toString().trim();
        if(code==0 && output.equals(tc.getStdout().trim())) passed++;
        // cleanup
        try { Files.walk(tmp).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete); } catch(Exception ex){}
      }
      s.setScore(passed);
      s.setStatus(passed==cases.size()?"ACCEPTED":"WRONG_ANSWER");
      submissionRepo.save(s);
    } catch(Exception e){ e.printStackTrace(); s.setStatus("ERROR"); submissionRepo.save(s); }
  }
}

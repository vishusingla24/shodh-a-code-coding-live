
package com.shodh.repo;
import com.shodh.model.TestCase;
import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface TestCaseRepo extends JpaRepository<TestCase, Long> {
  List<TestCase> findByProblemId(Long problemId);
}

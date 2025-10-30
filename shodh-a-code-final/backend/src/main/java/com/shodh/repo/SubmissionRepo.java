
package com.shodh.repo;
import com.shodh.model.Submission;
import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface SubmissionRepo extends JpaRepository<Submission, Long> {
  Optional<Submission> findBySubmissionUuid(String uuid);
}

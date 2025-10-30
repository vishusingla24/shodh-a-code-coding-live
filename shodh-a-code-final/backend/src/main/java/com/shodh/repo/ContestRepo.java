
package com.shodh.repo;
import com.shodh.model.Contest;
import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface ContestRepo extends JpaRepository<Contest, Long> {
  Optional<Contest> findByContestId(String contestId);
}

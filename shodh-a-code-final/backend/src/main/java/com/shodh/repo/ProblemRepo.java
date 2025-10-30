
package com.shodh.repo;
import com.shodh.model.Problem;
import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface ProblemRepo extends JpaRepository<Problem, Long> {
  Optional<Problem> findByCode(String code);
}

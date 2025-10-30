
package com.shodh.model;
import jakarta.persistence.*;
@Entity
@Table(name="testcase")
public class TestCase {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  @ManyToOne private Problem problem;
  @Column(columnDefinition="text") private String stdin;
  @Column(columnDefinition="text") private String stdout;
  private boolean isSample;
  // getters/setters
  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public Problem getProblem(){return problem;}
  public void setProblem(Problem p){this.problem=p;}
  public String getStdin(){return stdin;}
  public void setStdin(String stdin){this.stdin=stdin;}
  public String getStdout(){return stdout;}
  public void setStdout(String stdout){this.stdout=stdout;}
  public boolean getIsSample(){return isSample;}
  public void setIsSample(boolean s){this.isSample=s;}
}
